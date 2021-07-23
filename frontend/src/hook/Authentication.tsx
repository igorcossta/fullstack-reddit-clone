import React, { createContext, useCallback, useEffect, useState } from 'react';

import api from '../service/http';
import { IAuthContext, IUser, UsernameAndPasswordRequest } from '../service/type';

export const AuthContext = createContext<IAuthContext>({} as IAuthContext);

export const AuthProvider: React.FC = ({ children }) => {
  const [user, setUser] = useState<IUser | null>(null);
  const [load, setLoad] = useState<boolean>(true);
  const [authenticate, setAuthenticate] = useState<boolean>(false);

  useEffect(() => {
    const storagedUser = window.localStorage.getItem('@user');
    const storagedToken = window.localStorage.getItem('@token');

    if (storagedUser && storagedToken) {
      setUser(JSON.parse(storagedUser));
      setAuthenticate(true);
    } else {
      setAuthenticate(false);
    }

    setLoad(false);
  }, []);

  const signIn = useCallback(async (data: UsernameAndPasswordRequest): Promise<boolean> => {
    console.debug('LOGIN RESPONSE: ');
    setLoad(true);
    console.log(data);

    try {
      const response = await api.post('/api/signin', data);
      console.log(response.data);
      // console.log(response.headers['access-token']);
      // window.localStorage.setItem('@token:reddit', JSON.stringify(response.headers['access-token']));
    } catch (e) {
      alert('algo de errado aconteceu');
    }

    setAuthenticate(true);
    setLoad(false);
    return true;
  }, []);

  const signOut = useCallback(() => {
    setUser(null);
    setAuthenticate(false);
    window.localStorage.removeItem('@user');
    window.localStorage.removeItem('@token');
  }, []);

  return (
    <AuthContext.Provider
      value={{
        signIn,
        signOut,
        principal: user,
        isAuthenticated: authenticate,
        isLoaded: load,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
