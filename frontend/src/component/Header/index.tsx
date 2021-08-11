import React, { useCallback } from 'react';
import { FcReddit } from 'react-icons/fc';
import { Link, useHistory } from 'react-router-dom';

import { useAuth } from '../../context/account';
import { Container, Tools } from './styles';

const Header: React.FC = () => {
  const { signed, SignOut } = useAuth();
  const history = useHistory();

  const account = useCallback(() => {
    history.push('/account');
  }, [history]);

  const signOut = useCallback(() => {
    SignOut();
  }, [SignOut]);

  return (
    <Container>
      <Link to="/">
        <FcReddit size={38} />
        <h1>reddit</h1>
      </Link>

      <Tools signed={signed}>
        {signed ? (
          // usuario autenticado
          <>
            <Link to="/account">
              <img src="https://avatars.githubusercontent.com/u/65612587?v=4" alt="user" />
            </Link>
            <button type="button" onClick={signOut}>
              Log Out
            </button>
          </>
        ) : (
          // usuario nao autenticado
          <>
            <button type="button" onClick={account}>
              Log In
            </button>
            <button type="button" onClick={account}>
              Sign Up
            </button>
          </>
        )}
      </Tools>
    </Container>
  );
};

export default Header;
