import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Reward } from '../models/reward.model';

export interface ApiResponse<T> {
  data: T;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class RewardService {
  private apiUrl = environment.accountApiUrl;

  constructor(private http: HttpClient) {}

  /**
   * Get total reward points for a user
   */
  getTotalRewardsForUser(accountId: string): Observable<ApiResponse<number>> {
    return this.http.get<ApiResponse<number>>(
      `${this.apiUrl}/secure/rewards/${accountId}/total`
    );
  }

  /**
   * Get all active rewards for a user
   */
  getActiveRewards(accountId: string): Observable<ApiResponse<Reward[]>> {
    return this.http.get<ApiResponse<Reward[]>>(
      `${this.apiUrl}/secure/rewards/${accountId}/active`
    );
  }

  /**
   * Get all rewards (active and inactive) for a user
   */
  getAllRewards(accountId: string): Observable<ApiResponse<Reward[]>> {
    return this.http.get<ApiResponse<Reward[]>>(
      `${this.apiUrl}/secure/rewards/${accountId}/all`
    );
  }

  /**
   * Get reward for a specific transaction
   */
  getRewardByTransactionId(transactionId: string): Observable<ApiResponse<Reward>> {
    return this.http.get<ApiResponse<Reward>>(
      `${this.apiUrl}/secure/rewards/transaction/${transactionId}`
    );
  }

  /**
   * Deactivate a reward
   */
  deactivateReward(rewardId: number): Observable<ApiResponse<Reward>> {
    return this.http.put<ApiResponse<Reward>>(
      `${this.apiUrl}/secure/rewards/${rewardId}/deactivate`,
      {}
    );
  }

  /**
   * Check if a reward exists for a transaction
   */
  rewardExistsForTransaction(transactionId: string): Observable<ApiResponse<boolean>> {
    return this.http.get<ApiResponse<boolean>>(
      `${this.apiUrl}/secure/rewards/transaction/${transactionId}/exists`
    );
  }
}
