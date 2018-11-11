import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LogUserInfo from './log-user-info';
import UserInfo from './user-info';
import BankInfo from './bank-info';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/log-user-info`} component={LogUserInfo} />
      <ErrorBoundaryRoute path={`${match.url}/user-info`} component={UserInfo} />
      <ErrorBoundaryRoute path={`${match.url}/bank-info`} component={BankInfo} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
