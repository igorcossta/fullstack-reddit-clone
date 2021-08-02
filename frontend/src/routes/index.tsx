import React, { useState, useCallback } from 'react';
import { FaArrowCircleUp } from 'react-icons/fa';
import { Route, Switch } from 'react-router-dom';

import { useAuth } from '../context/authentication';
import { Dashboard, Home, Login } from '../page';
import PrivateRoute from './protect.routes';

const Routes: React.FC = () => {
  const [showScroll, setShowScroll] = useState(false);
  const { signed } = useAuth();

  const checkScrollTop = () => {
    if (!showScroll && window.pageYOffset > 201) {
      setShowScroll(true);
    } else if (showScroll && window.pageYOffset < 200) {
      setShowScroll(false);
    }
  };

  const scrollTop = useCallback(() => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }, []);

  window.addEventListener('scroll', checkScrollTop);

  return (
    <>
      {showScroll && (
        <FaArrowCircleUp size={38} fill="var(--secondary-color)" className="scrollTop" onClick={() => scrollTop()} />
      )}
      <Switch>
        <Route exact path="/" component={Home} />
        {!signed && <Route exact path="/login" component={Login} />}
        <PrivateRoute exact path="/dashboard" component={Dashboard} />
        <Route path="*" component={() => <h6>not found</h6>} />
      </Switch>
    </>
  );
};

export default Routes;
