import { Moment } from 'moment';

export interface ILogUserInfo {
  id?: number;
  userName?: string;
  nickName?: string;
  email?: string;
  phoneNumber?: string;
  hireDate?: Moment;
}

export const defaultValue: Readonly<ILogUserInfo> = {};
