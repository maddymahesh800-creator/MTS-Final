export interface Reward {
  id: number;
  accountId: string;
  points: number;
  transactionId: string;
  transactionAmount: number;
  rewardCreatedAt: string;
  createdAt: string;
  updatedAt: string;
  isActive: boolean;
}
