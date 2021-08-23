import React from 'react';
import { Slide, ToastContainer } from 'react-toastify';

import Root from '../assets/style/Root';
import { BackToTop } from '../component';
import { AuthProvider } from './account';

const Provider: React.FC = ({ children }) => {
  return (
    <>
      <ToastContainer transition={Slide} />
      <BackToTop />
      <Root />
      <AuthProvider>{children}</AuthProvider>
    </>
  );
};

export default Provider;
