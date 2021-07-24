import React, { useCallback, useEffect } from 'react';
import { FiXCircle } from 'react-icons/fi';

import { useToast } from '../../../hook/toast';
import { ToastProps } from '../../../service/toast.type';
import { Container } from './styles';

interface Props {
  toast: ToastProps;
}

const ToastWindow: React.FC<Props> = ({ toast }) => {
  const { removeToast } = useToast();

  useEffect(() => {
    const timer = setTimeout(() => {
      removeToast(toast.id);
    }, 3000);

    return () => {
      clearTimeout(timer);
    };
  }, [toast.id, removeToast]);

  const dropToast = useCallback(
    (id: string) => {
      removeToast(id);
    },
    [removeToast],
  );

  return (
    <Container type={toast.type}>
      <div>
        <span>{toast.title}</span>
        <p>{toast.description}</p>
      </div>
      <button onClick={() => dropToast(toast.id)} type="button">
        <FiXCircle size={18} />
      </button>
    </Container>
  );
};

export default ToastWindow;
