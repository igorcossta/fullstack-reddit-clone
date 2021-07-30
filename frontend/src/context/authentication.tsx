/* eslint-disable no-console */
import React, { createContext, useCallback, useContext, useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { server } from '../service/server';
import { SigninPayload } from './authentication.type';
import { useNotification } from './notification';

interface ContextProps {
  loading: boolean;
  signed: boolean;
  principal: UserProps | null;
  SignIn(payload: SigninPayload): Promise<void>;
  SignOut(): void;
}

interface UserProps {
  firstName: string;
  lastName: string;
  username: string; // email
  locked: string;
  enabled: string;
  token: string;
  refreshToken: string;
}

const Context = createContext<ContextProps>({} as ContextProps);

const AuthProvider: React.FC = ({ children }) => {
  const [user, setUser] = useState<UserProps | null>(null);
  const [loading, setLoading] = useState(false);
  const { addNotification } = useNotification();
  const history = useHistory();

  // verificar se existe um usuario salvo no localStorage
  useEffect(() => {
    setLoading(true);
    const guy = window.localStorage.getItem('user');
    if (guy) {
      setUser(JSON.parse(guy));
    }
    setLoading(false);
  }, []);

  // funcao para efetuar o login
  const SignIn = useCallback(
    async (payload: SigninPayload) => {
      setLoading(true);
      // envia uma requisicao para o servidor
      // com os dados de usuario e senha
      await server
        .post('/api/signin', payload)
        .then((res) => {
          const { data } = res;

          window.localStorage.setItem('user', JSON.stringify(data));
          setUser(data);

          // assim que o usuario for autenticado, o mesmo sera
          // redirecionado para o dashboard
          history.push('/dashboard');
        })
        // enviar uma notificao caso o usuario e senha
        // sejam invalidos
        .catch(() => {
          addNotification({
            title: 'Erro',
            description: 'Aconteceu um erro na autenticacao',
            type: 'danger',
          });
        })
        .finally(() => setLoading(false));
    },
    [addNotification, history],
  );

  // funcao para efetuar logout
  const SignOut = useCallback(() => {
    const guy = window.localStorage.getItem('user');
    if (guy) {
      window.localStorage.removeItem('user');
      setUser(null);
      history.push('/');
    } else {
      console.log('voce ja esta deslogado');
    }
  }, [history]);

  return (
    <Context.Provider value={{ signed: !!user, principal: user, SignIn, SignOut, loading }}>
      {children}
    </Context.Provider>
  );
};

function useAuth(): ContextProps {
  const context = useContext(Context);
  if (!context) {
    throw new Error('useAuth must be used within an authentication provider');
  }
  return context;
}

export { useAuth, AuthProvider };
