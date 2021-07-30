import React, { createContext, useContext, useCallback, useState } from 'react';

import { v4 as uuidv4 } from 'uuid';

import { ToastContainer } from '../component';

interface ContextProps {
  addNotification(notify: Omit<NotifyProps, 'id'>): void;
  removeNotification(id: string): void;
}

export interface NotifyProps {
  id: string;
  type: 'success' | 'danger' | 'info';
  description?: string;
  title: string;
}

const Context = createContext<ContextProps>({} as ContextProps);

const NotifyProvider: React.FC = ({ children }) => {
  const [notifications, setNotify] = useState<NotifyProps[]>([]);

  // add new notification
  const addNotification = useCallback(({ title, description, type }: Omit<NotifyProps, 'id'>) => {
    const id = uuidv4();
    const notify = {
      id,
      title,
      description,
      type,
    };
    setNotify((state) => [...state, notify]);
  }, []);
  // remove some notification
  const removeNotification = useCallback((id: string) => {
    setNotify((state) => state.filter((i) => i.id !== id));
  }, []);

  return (
    <Context.Provider value={{ addNotification, removeNotification }}>
      {children}
      <ToastContainer toasts={notifications} />
    </Context.Provider>
  );
};

function useNotification(): ContextProps {
  const context = useContext(Context);
  if (!context) {
    throw new Error('useNotification must be used within as notification provider');
  }
  return context;
}

export { useNotification, NotifyProvider };
