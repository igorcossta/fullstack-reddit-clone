import React, { createContext, useCallback, useEffect, useState, useContext } from 'react';

import { ContextProps, Principal, SignInPayload } from '../service/auth.type';
import api from '../service/http';

const AuthContext = createContext<ContextProps>({} as ContextProps);

const AuthProvider: React.FC = ({ children }) => {
  const [user, setUser] = useState<Principal | null>(null);
  const [load, setLoad] = useState(true);
  const [authenticate, setAuthenticate] = useState(false);

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

  const signIn = useCallback(async (data: SignInPayload): Promise<void> => {
    setLoad(true);

    try {
      const response = await api.post('/api/signin', data);
    } catch (e) {
      throw new Error('Log In error');
    }

    setAuthenticate(true);
    setLoad(false);
  }, []);

  const signOut = useCallback(() => {
    console.error('Sign Out successful');
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

function useAuth(): ContextProps {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error('useAuth must be used within an authentication provider');
  }

  return context;
}

export { AuthProvider, useAuth };
