import React from 'react';
import { Route, Switch, useLocation } from 'react-router-dom';

import Home from '../page/Home';
import Login from '../page/Login';

const NoMatch: React.FC = () => {
  const { pathname } = useLocation();
  return (
    <h3>
      not match for <code>{pathname}</code>
    </h3>
  );
};

const AuthRoutes: React.FC = () => {
  return (
    // <BrowserRouter>
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/login" component={Login} />
      <Route path="*" component={NoMatch} />
    </Switch>
    // </BrowserRouter>
  );
};

export default AuthRoutes;
