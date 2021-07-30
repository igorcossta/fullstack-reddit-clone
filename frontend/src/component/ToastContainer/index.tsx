import React from 'react';

import { NotifyProps } from '../../context/notification';
import { Container } from './styles';
import Toast from './Toast';

interface Props {
  toasts: NotifyProps[];
}

const ToastContainer: React.FC<Props> = ({ toasts }) => {
  return (
    <Container>
      {toasts.map((i) => (
        <Toast key={i.id} toast={i} />
      ))}
    </Container>
  );
};

export default ToastContainer;
