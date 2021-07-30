import React, { useCallback, useEffect } from 'react';
import { FiAlertTriangle, FiInfo, FiCheckCircle } from 'react-icons/fi';

import { NotifyProps, useNotification } from '../../../context/notification';
import { Container, Description, Title, Icon } from './styles';

interface Props {
  toast: NotifyProps;
}

const icons = {
  success: <FiCheckCircle size={20} />,
  danger: <FiAlertTriangle size={20} />,
  info: <FiInfo size={20} />,
};

const Toast: React.FC<Props> = ({ toast }) => {
  const { removeNotification } = useNotification();

  useEffect(() => {
    const timer = setTimeout(() => {
      removeNotification(toast.id);
    }, 3000);
    return () => {
      clearTimeout(timer);
    };
  }, [toast.id, removeNotification]);

  const dropToast = useCallback(
    (id: string) => {
      removeNotification(id);
    },
    [removeNotification],
  );

  return (
    <Container type={toast.type} onClick={() => dropToast(toast.id)}>
      <Icon>{icons[toast.type || 'info']}</Icon>
      <div>
        <Title>{toast.title}</Title>
        <Description>{toast.description}</Description>
      </div>
    </Container>
  );
};

export default Toast;
