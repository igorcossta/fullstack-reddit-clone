import React from 'react';
import { BrowserRouter } from 'react-router-dom';

import Root from './assets/style/Root';
import Routes from './routes/index';

const App: React.FC = () => (
  <BrowserRouter>
    <Routes />
    <Root />
  </BrowserRouter>
);

export default App;
