import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LogUserInfo from './log-user-info';
import LogUserInfoDetail from './log-user-info-detail';
import LogUserInfoUpdate from './log-user-info-update';
import LogUserInfoDeleteDialog from './log-user-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LogUserInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LogUserInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LogUserInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={LogUserInfo} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LogUserInfoDeleteDialog} />
  </>
);

export default Routes;
