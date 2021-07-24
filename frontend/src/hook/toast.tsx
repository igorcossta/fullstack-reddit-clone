import React, { createContext, useCallback, useContext, useState } from 'react';

import { v4 as uuidv4 } from 'uuid';

import Toast from '../component/Toast';
import { ContextProps, ToastProps } from '../service/toast.type';

const Context = createContext<ContextProps>({} as ContextProps);

const ToastProvider: React.FC = ({ children }) => {
  const [messages, setMessages] = useState<ToastProps[]>([]);

  const addToast = useCallback(({ title, description, type }: Omit<ToastProps, 'id'>) => {
    const id = uuidv4();
    const toast = {
      id,
      title,
      description,
      type,
    };

    setMessages((state) => [...state, toast]);
  }, []);

  const removeToast = useCallback((id: string) => {
    setMessages((state) => state.filter((message) => message.id !== id));
  }, []);

  return (
    <Context.Provider
      value={{
        addToast,
        removeToast,
      }}
    >
      {children}
      <Toast variable={messages} />
    </Context.Provider>
  );
};

function useToast(): ContextProps {
  const context = useContext(Context);

  if (!context) {
    throw new Error('useToast must be used within an toast provider');
  }

  return context;
}

export { ToastProvider, useToast };
