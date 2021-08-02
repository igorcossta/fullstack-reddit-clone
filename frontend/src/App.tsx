import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';

import WebFont from 'webfontloader';

import Root from './assets/style/Root';
import { Header } from './component';
import { AuthProvider } from './context/authentication';
import { NotifyProvider } from './context/notification';
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
      <NotifyProvider>
        <AuthProvider>
          <Header />
          <Routes />
        </AuthProvider>
      </NotifyProvider>
      <Root />
    </BrowserRouter>
  );
};

export default App;
