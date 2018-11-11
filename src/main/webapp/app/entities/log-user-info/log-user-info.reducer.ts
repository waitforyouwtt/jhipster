import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILogUserInfo, defaultValue } from 'app/shared/model/log-user-info.model';

export const ACTION_TYPES = {
  FETCH_LOGUSERINFO_LIST: 'logUserInfo/FETCH_LOGUSERINFO_LIST',
  FETCH_LOGUSERINFO: 'logUserInfo/FETCH_LOGUSERINFO',
  CREATE_LOGUSERINFO: 'logUserInfo/CREATE_LOGUSERINFO',
  UPDATE_LOGUSERINFO: 'logUserInfo/UPDATE_LOGUSERINFO',
  DELETE_LOGUSERINFO: 'logUserInfo/DELETE_LOGUSERINFO',
  RESET: 'logUserInfo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILogUserInfo>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LogUserInfoState = Readonly<typeof initialState>;

// Reducer

export default (state: LogUserInfoState = initialState, action): LogUserInfoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LOGUSERINFO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOGUSERINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOGUSERINFO):
    case REQUEST(ACTION_TYPES.UPDATE_LOGUSERINFO):
    case REQUEST(ACTION_TYPES.DELETE_LOGUSERINFO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LOGUSERINFO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOGUSERINFO):
    case FAILURE(ACTION_TYPES.CREATE_LOGUSERINFO):
    case FAILURE(ACTION_TYPES.UPDATE_LOGUSERINFO):
    case FAILURE(ACTION_TYPES.DELETE_LOGUSERINFO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOGUSERINFO_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOGUSERINFO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOGUSERINFO):
    case SUCCESS(ACTION_TYPES.UPDATE_LOGUSERINFO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOGUSERINFO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/log-user-infos';

// Actions

export const getEntities: ICrudGetAllAction<ILogUserInfo> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOGUSERINFO_LIST,
    payload: axios.get<ILogUserInfo>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILogUserInfo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOGUSERINFO,
    payload: axios.get<ILogUserInfo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILogUserInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOGUSERINFO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILogUserInfo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOGUSERINFO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILogUserInfo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOGUSERINFO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
