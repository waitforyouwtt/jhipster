import { Moment } from 'moment';

export const enum Sex {
  MAN = 'MAN',
  WOMAN = 'WOMAN'
}

export const enum Ascription {
  SHENGTI = 'SHENGTI',
  YAOGUANG = 'YAOGUANG',
  JIANGJIA = 'JIANGJIA',
  YAOZU = 'YAOZU',
  SANXIU = 'SANXIU'
}

export interface IUserInfo {
  id?: number;
  userId?: string;
  userName?: string;
  nickName?: string;
  sex?: Sex;
  ascription?: Ascription;
  address?: string;
  email?: string;
  phoneNumber?: string;
  birthday?: Moment;
  createTime?: Moment;
  updateTime?: Moment;
}

export const defaultValue: Readonly<IUserInfo> = {};
