import React, { useEffect } from 'react';
import { ToastContainer, Slide } from 'react-toastify';

import WebFont from 'webfontloader';

import Root from '../assets/style/Root';
import { BackToTop } from '../component';
import { AuthProvider } from '../context/account';

const Providers: React.FC = ({ children }) => {
  useEffect(() => {
    WebFont.load({
      google: {
        families: ['Noto Sans'],
      },
    });
  }, []);

  return (
    <>
      <Root />
      <ToastContainer transition={Slide} />
      <BackToTop />
      <AuthProvider>{children}</AuthProvider>
    </>
  );
};

export default Providers;
