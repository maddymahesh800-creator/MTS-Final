import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { catchError, forkJoin, of } from 'rxjs';

import { RewardService } from '../../core/services/reward.service';
import { AuthService } from '../../core/auth/auth.service';
import { Account } from '../../core/models/account.model';
import { Reward } from '../../core/models/reward.model';

@Component({
  selector: 'app-rewards',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './rewards.html',
  styleUrls: ['./rewards.css']
})
export class RewardsComponent implements OnInit {
  user!: Account;
  totalRewards: number = 0;
  activeRewards: Reward[] = [];
  allRewards: Reward[] = [];
  filteredRewards: Reward[] = [];
  selectedTab: 'active' | 'all' = 'active';
  loading: boolean = false;
  error: string = '';

  constructor(
    public router: Router,
    private rewardService: RewardService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const storedUser = localStorage.getItem('loggedInUser');

    if (!storedUser) {
      this.router.navigate(['/login']);
      return;
    }

    this.user = JSON.parse(storedUser);
    this.loadRewards();
  }

  loadRewards(): void {
    this.loading = true;
    this.error = '';

    const total$ = this.rewardService.getTotalRewardsForUser(this.user.id).pipe(
      catchError((err) => {
        console.error('Error loading total rewards:', err);
        this.error = 'Failed to load total rewards';
        return of({ data: 0, message: 'error' });
      })
    );

    const active$ = this.rewardService.getActiveRewards(this.user.id).pipe(
      catchError((err) => {
        console.error('Error loading active rewards:', err);
        return of({ data: [], message: 'error' });
      })
    );

    const all$ = this.rewardService.getAllRewards(this.user.id).pipe(
      catchError((err) => {
        console.error('Error loading all rewards:', err);
        return of({ data: [], message: 'error' });
      })
    );

    forkJoin({ total: total$, active: active$, all: all$ }).subscribe({
      next: (results) => {
        this.totalRewards = results.total?.data || 0;
        this.activeRewards = results.active?.data || [];
        this.allRewards = results.all?.data || [];
        this.filteredRewards = this.selectedTab === 'active' ? this.activeRewards : this.allRewards;
        this.loading = false;
      },
      error: (err) => {
        console.error('Failed to load rewards set:', err);
        this.error = 'Failed to load rewards';
        this.loading = false;
      }
    });
  }

  switchTab(tab: 'active' | 'all'): void {
    this.selectedTab = tab;
    this.filteredRewards = tab === 'active' ? this.activeRewards : this.allRewards;
  }

  deactivateReward(reward: Reward): void {
    if (confirm(`Are you sure you want to deactivate this reward (${reward.points} points)?`)) {
      this.rewardService.deactivateReward(reward.id)
        .pipe(
          catchError((err) => {
            console.error('Error deactivating reward:', err);
            this.error = 'Failed to deactivate reward';
            return of({ data: null, message: 'error' });
          })
        )
        .subscribe({
          next: () => {
            // Reload rewards after deactivation
            this.loadRewards();
          }
        });
    }
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }

  formatDate(date: string): string {
    return new Date(date).toLocaleDateString('en-IN', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }
}
