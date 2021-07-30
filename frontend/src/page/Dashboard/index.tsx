import React from 'react';

import { Modal } from '../../component';
import { useAuth } from '../../context/authentication';
import useModal from '../../hook/window';
import { Container } from './styles';

const Dashboard: React.FC = () => {
  const { principal } = useAuth();
  const { isHidden, toggle } = useModal();
  return (
    <Container>
      <h1>Dashboard {principal?.username}</h1>
      <button type="button" onClick={() => toggle()}>
        MODAL
      </button>

      <Modal isHidden={isHidden} toggle={toggle}>
        <h1>Hi, im modal</h1>
      </Modal>
    </Container>
  );
};

export default Dashboard;
