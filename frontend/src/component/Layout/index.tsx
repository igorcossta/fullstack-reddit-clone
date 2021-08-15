import React from 'react';

import { Container, Main } from './styles';

const Layout: React.FC = ({ children }) => {
  return (
    <Container>
      <Main>{children}</Main>
    </Container>
  );
};

export default Layout;
