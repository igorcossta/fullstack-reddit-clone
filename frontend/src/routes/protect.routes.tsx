import React from 'react';
import { Redirect, Route, RouteProps } from 'react-router-dom';

import { storage } from '../service/storage.manager';

interface PrivateRouteProps extends RouteProps {
  // tslint:disable-next-line:no-any
  component: any;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({ component: Component, ...rest }) => {
  const signed = storage.getUser();
  return (
    <Route
      {...rest}
      render={(props) => {
        return signed ? (
          <Component {...props} />
        ) : (
          <Redirect to={{ pathname: '/account', state: { from: props.location } }} />
        );
      }}
    />
  );
};

export default PrivateRoute;
