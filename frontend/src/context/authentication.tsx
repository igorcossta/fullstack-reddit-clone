/* eslint-disable no-console */
import React, { createContext, useCallback, useContext, useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { server } from '../service/server';
import { SigninPayload } from './authentication.type';
import { useNotification } from './notification';

interface ContextProps {
  loading: boolean;
  signed: boolean;
  SignIn(payload: SigninPayload): Promise<void>;
  SignOut(): void;
  user: UserProps | null;
}

interface UserProps {
  firstName: string;
  lastName: string;
  username: string; // email
}

const Context = createContext<ContextProps>({} as ContextProps);

const AuthProvider: React.FC = ({ children }) => {
  const [loading, setLoading] = useState(false);
  const [signed, setSigned] = useState(false);
  const [user, setUser] = useState<UserProps | null>(null);
  const { addNotification } = useNotification();
  const history = useHistory();

  useEffect(() => {
    setLoading(true);
    const usuario = window.localStorage.getItem('user');
    const token = window.localStorage.getItem('token');
    if (usuario && token) {
      setUser(JSON.parse(usuario));
      setSigned(true);
    }
    setLoading(false);
  }, []);

  const SignIn = useCallback(
    async (payload: SigninPayload) => {
      setLoading(true);
      await server
        .post('/api/signin', payload)
        .then(({ data, headers }) => {
          setUser(data);
          setSigned(true);

          window.localStorage.setItem('user', JSON.stringify(data));
          window.localStorage.setItem('token', headers.authorization);

          history.push('/dashboard');
        })
        .catch((err) => {
          if (err.response) {
            const { error, message } = err.response.data;
            addNotification({
              title: `${error}`,
              description: `${message}`,
              type: 'danger',
            });
          } else {
            addNotification({
              title: 'Internal error',
              description: 'Contact an administrator',
              type: 'danger',
            });
          }
        })
        .finally(() => setLoading(false));
    },
    [addNotification, history],
  );

  const SignOut = useCallback(() => {
    window.localStorage.clear();
    setUser(null);
    setSigned(false);
    history.push('/');
  }, [history]);

  return <Context.Provider value={{ loading, signed, SignIn, SignOut, user }}>{children}</Context.Provider>;
};

function useAuth(): ContextProps {
  const context = useContext(Context);
  if (!context) {
    throw new Error('useAuth must be used within an authentication provider');
  }
  return context;
}

export { useAuth, AuthProvider };
