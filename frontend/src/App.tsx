import React from 'react';

import { Header, Layout } from './component';
import Routes from './routes';
import Providers from './utils/provider';

const App: React.FC = () => {
  return (
    <Providers>
      <Header />
      <Layout>
        <Routes />
      </Layout>
    </Providers>
  );
};

export default App;
