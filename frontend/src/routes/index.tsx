import React from 'react';
import { Route, Switch } from 'react-router-dom';

import { Account } from '../component';
import { Home, CrSubreddit } from '../page';
import PrivateRoute from './protect.routes';

const Routes: React.FC = () => {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/account" component={Account} />
      <PrivateRoute exact path="/account/crSubreddit" component={CrSubreddit} />
      <Route path="*" component={() => <h6>not found</h6>} />
    </Switch>
  );
};

export default Routes;
