import React from 'react';
import { Route, Switch, useLocation } from 'react-router-dom';

import { AuthProvider } from '../hook/Authentication';
import Home from '../page/Home';
import SignIn from '../page/SignIn';

const NoMatch: React.FC = () => {
  const { pathname } = useLocation();
  return (
    <h3>
      not match for <code>{pathname}</code>
    </h3>
  );
};

const Routes: React.FC = () => (
  <AuthProvider>
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/signin" component={SignIn} />
      <Route path="*" component={NoMatch} />
    </Switch>
  </AuthProvider>
);

export default Routes;
