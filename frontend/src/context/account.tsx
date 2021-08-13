import React, { createContext, useCallback, useContext, useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

import { ContextProps, SigninPayload, SignupPayload, UserProps } from '../@types/account.type';
import { AuthAPI } from '../axios/auth.api';
import { handleError, handleSuccess } from '../service/account.service';
import { storage } from '../service/storage.manager';

const Context = createContext<ContextProps>({} as ContextProps);

const AuthProvider: React.FC = ({ children }) => {
  const [loading, setLoading] = useState(false);
  const [signed, setSigned] = useState(false);
  const [user, setUser] = useState<UserProps | null>(null);
  const history = useHistory();

  useEffect(() => {
    setLoading(true);
    const usuario = storage.getUser();
    const token = storage.getToken();
    if (usuario && token) {
      setUser(usuario);
      setSigned(true);
    }
    setLoading(false);
  }, []);

  const ConfirmToken = useCallback(
    async (token: string) => {
      setLoading(true);
      await AuthAPI.get(`/api/register/confirm?token=${token}`)
        .then((res) => {
          handleSuccess(res, 'confirm');
          history.replace({ pathname: '/', state: {} });
        })
        .catch((error) => handleError(error))
        .finally(() => setLoading(false));
    },
    [history],
  );

  const SignUp = useCallback(
    async (payload: SignupPayload) => {
      setLoading(true);
      await AuthAPI.post('/api/register', payload)
        .then((res) => {
          handleSuccess(res, 'signup');
          history.replace({ pathname: '/', state: {} });
        })
        .catch((error) => handleError(error))
        .finally(() => setLoading(false));
    },
    [history],
  );

  const SignIn = useCallback(
    async (payload: SigninPayload) => {
      setLoading(true);
      await AuthAPI.post('/api/signin', payload)
        .then((res) => {
          handleSuccess(res, 'signin');
          setSigned(true);
          setUser(res.data);
          history.replace({ pathname: '/', state: {} });
        })
        .catch((error) => handleError(error))
        .finally(() => setLoading(false));
    },
    [history],
  );

  const SignOut = useCallback(() => {
    storage.clearData();
    setSigned(false);
    setUser(null);
    history.replace({ pathname: '/', state: {} });
  }, [history]);

  return (
    <Context.Provider value={{ loading, signed, ConfirmToken, SignIn, SignOut, SignUp, user }}>
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
