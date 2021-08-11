import React from 'react';

import { useAuth } from '../../context/account';
import { Container } from './styles';

const Dashboard: React.FC = () => {
  const { user } = useAuth();
  return (
    <Container>
      <h1>Dashboard {user?.username}</h1>
    </Container>
  );
};

export default Dashboard;
