import React from 'react';

import { Header } from './component';
import Routes from './routes';
import Providers from './utils/provider';

const App: React.FC = () => {
  return (
    <Providers>
      <Header />
      <Routes />
    </Providers>
  );
};

export default App;
