import { AxiosResponse } from 'axios';
import React, { createContext, useCallback, useContext, useState, useEffect } from 'react';

import { ContextProps, SigninPayload, SignupPayload, UserProps } from '../@types/account.type';
import { axios as AuthAPI } from '../axios/axios.config';
import { storage } from '../service/storage.manager';

const Context = createContext<ContextProps>({} as ContextProps);

const AuthProvider: React.FC = ({ children }) => {
  const [loading, setLoading] = useState(false);
  const [signed, setSigned] = useState(false);
  const [user, setUser] = useState<UserProps | null>(null);

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

  const confirmToken = useCallback(async (token: string) => {
    setLoading(true);

    try {
      const res = await AuthAPI.get(`/api/register/confirm?token=${token}`);
      console.log(res);
    } catch (err) {
      console.log(err);
    }

    setLoading(false);
  }, []);

  const signUp = useCallback(async (payload: SignupPayload) => {
    setLoading(true);

    try {
      const res = await AuthAPI.post('/api/register', payload);
      console.log('check your email to activate your account');
    } catch (err) {
      console.log(err);
    }

    setLoading(false);
  }, []);

  const signIn = useCallback(async (payload: SigninPayload) => {
    setLoading(true);

    try {
      const res = await AuthAPI.post('/api/signin', payload);
      authenticateUser(res);
    } catch (err) {
      console.log(err);
    }

    setLoading(false);
  }, []);

  const authenticateUser = (res: AxiosResponse) => {
    if (!res.headers.authorization) return;

    const {
      headers: { authorization },
      data,
    } = res;

    storage.saveUser(data);
    storage.saveToken(authorization);

    setUser(data);
    setSigned(true);
  };

  const signOut = useCallback(() => {
    storage.clearData();
    setSigned(false);
    setUser(null);
  }, []);

  return (
    <Context.Provider value={{ loading, signed, confirmToken, signIn, signOut, signUp, user }}>
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
