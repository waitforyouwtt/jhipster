import { Moment } from 'moment';

export interface IBankInfo {
  id?: number;
  bankId?: string;
  userId?: string;
  account?: string;
  accountName?: string;
  bankName?: string;
  bankBranchName?: string;
  amount?: number;
  createTime?: Moment;
  updateTime?: Moment;
}

export const defaultValue: Readonly<IBankInfo> = {};
