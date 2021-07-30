import React from 'react';
import { FcReddit } from 'react-icons/fc';
import { Link } from 'react-router-dom';

import { useAuth } from '../../context/authentication';
import { SigninPayload } from '../../context/authentication.type';
import { Container, Tools } from './styles';

const object = {
  username: 'pam@edu.br',
  password: '12',
} as SigninPayload;

const Header: React.FC = () => {
  const { signed, SignOut, SignIn } = useAuth();
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
            <img src="https://avatars.githubusercontent.com/u/65612587?v=4" alt="user" />
            <button type="button" onClick={() => SignOut()}>
              Log Out
            </button>
          </>
        ) : (
          // usuario nao autenticado
          <>
            <button type="button" onClick={() => SignIn(object)}>
              Log In
            </button>
            <button type="button">Sign Up</button>
          </>
        )}
      </Tools>
    </Container>
  );
};

export default Header;
