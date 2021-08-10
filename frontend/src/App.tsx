import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import { ToastContainer, Slide } from 'react-toastify';

import WebFont from 'webfontloader';

import Root from './assets/style/Root';
import { Header } from './component';
import { AuthProvider } from './context/authentication';
import Routes from './routes';

const App: React.FC = () => {
  useEffect(() => {
    WebFont.load({
      google: {
        families: ['Noto Sans'],
      },
    });
  }, []);

  return (
    <BrowserRouter>
      <AuthProvider>
        <Header />
        <Routes />
      </AuthProvider>
      <Root />
      <ToastContainer transition={Slide} />
    </BrowserRouter>
  );
};

export default App;
