import React, { useCallback, useEffect, useState } from 'react';
import { FaArrowCircleUp } from 'react-icons/fa';
import { ToastContainer, Slide } from 'react-toastify';

import WebFont from 'webfontloader';

import Root from '../assets/style/Root';
import { AuthProvider } from '../context/account';

const Providers: React.FC = ({ children }) => {
  const [showScroll, setShowScroll] = useState(false);

  const scrollTop = useCallback(() => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }, []);

  const checkScrollTop = useCallback(() => {
    if (!showScroll && window.pageYOffset > 201) {
      setShowScroll(true);
    } else if (showScroll && window.pageYOffset < 200) {
      setShowScroll(false);
    }
  }, [showScroll]);

  useEffect(() => {
    window.addEventListener('scroll', checkScrollTop);
    WebFont.load({
      google: {
        families: ['Noto Sans'],
      },
    });
  }, [checkScrollTop]);

  return (
    <>
      <Root />
      <ToastContainer transition={Slide} />
      {showScroll && (
        <FaArrowCircleUp size={38} fill="var(--secondary-color)" className="scrollTop" onClick={() => scrollTop()} />
      )}
      <AuthProvider>{children}</AuthProvider>
    </>
  );
};

export default Providers;
