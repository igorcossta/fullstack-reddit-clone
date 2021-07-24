import React from 'react';
import { Route, Switch, useLocation } from 'react-router-dom';

import AppProvider from '../hook';
import Home from '../page/Home';

const NoMatch: React.FC = () => {
  const { pathname } = useLocation();
  return (
    <h3>
      not match for <code>{pathname}</code>
    </h3>
  );
};

const Routes: React.FC = () => (
  <AppProvider>
    <Switch>
      <Route exact path="/" component={Home} />
      <Route path="*" component={NoMatch} />
    </Switch>
  </AppProvider>
);

export default Routes;
