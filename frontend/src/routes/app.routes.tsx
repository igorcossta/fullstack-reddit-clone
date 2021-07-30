import React from 'react';
import { Route, Switch, useLocation } from 'react-router-dom';

import Dashboard from '../page/Dashboard';
import Home from '../page/Home';

const NoMatch: React.FC = () => {
  const { pathname } = useLocation();
  return (
    <h3>
      not match for <code>{pathname}</code>
    </h3>
  );
};

const AppRoutes: React.FC = () => {
  return (
    // <BrowserRouter>
    <Switch>
      <Route exact path="/" component={Home} />
      <Route exact path="/dashboard" component={Dashboard} />
      <Route path="*" component={NoMatch} />
    </Switch>
    // </BrowserRouter>
  );
};

export default AppRoutes;
