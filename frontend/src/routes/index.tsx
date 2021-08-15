import React from 'react';
import { Route, Switch } from 'react-router-dom';

import { Home, Subreddit, Dashboard, Account } from '../page';
import PrivateRoute from './protect.routes';

const Routes: React.FC = () => {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/account" component={Account} />
      <Route exact path="/r/:subredditName" component={Subreddit} />
      <PrivateRoute exact path="/dashboard" component={Dashboard} />
      <Route path="*" component={() => <h6>not found</h6>} />
    </Switch>
  );
};

export default Routes;
