import React from 'react';

import { Header, Layout } from './component';
import Provider from './context';
import Routes from './routes';

const App: React.FC = () => {
  return (
    <Provider>
      <Header />
      <Layout>
        <Routes />
      </Layout>
    </Provider>
  );
};

export default App;
