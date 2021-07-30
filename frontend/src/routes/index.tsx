import React, { useState, useCallback } from 'react';
import { FaArrowCircleUp } from 'react-icons/fa';

import { useAuth } from '../context/authentication';
import AppRoutes from './app.routes';
import AuthRoutes from './auth.routes';

const Routes: React.FC = () => {
  const { signed } = useAuth();
  const [showScroll, setShowScroll] = useState(false);

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
      {signed ? <AppRoutes /> : <AuthRoutes />}
    </>
  );
};

export default Routes;
