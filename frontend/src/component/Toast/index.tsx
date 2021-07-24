import React from 'react';

import { ToastProps } from '../../service/toast.type';
import { Container } from './styles';
import ToastWindow from './ToastWindow';

interface Props {
  variable: ToastProps[];
}

const Toast: React.FC<Props> = ({ variable }) => {
  return (
    <Container>
      {variable.map((i) => (
        <ToastWindow key={i.id} toast={i} />
      ))}
    </Container>
  );
};

export default Toast;
