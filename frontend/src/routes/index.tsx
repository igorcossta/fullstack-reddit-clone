import React from 'react';
import { Route, Switch } from 'react-router-dom';

import { Account } from '../component';
import { Home, CrSubreddit, Subreddit, CrPost } from '../page';
import PrivateRoute from './protect.routes';

const Routes: React.FC = () => {
  return (
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/account" component={Account} />
      <Route exact path="/r/:subredditName" component={Subreddit} />
      <Route exact path="/r/:subredditName/create-post" component={CrPost} />
      <PrivateRoute exact path="/account/create-subreddit" component={CrSubreddit} />
      <Route path="*" component={() => <h6>not found</h6>} />
    </Switch>
  );
};

export default Routes;
