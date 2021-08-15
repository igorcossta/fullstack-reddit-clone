import React, { useCallback } from 'react';
import { FcReddit } from 'react-icons/fc';
import { FiMenu } from 'react-icons/fi';
import { Link, useHistory } from 'react-router-dom';

import { Button } from '..';

import user from '../../assets/svg/female_avatar.svg';
import { useAuth } from '../../context/account';
import { Container, Logo, Buttons } from './styles';

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
      <Logo>
        <FcReddit />
        <Link to="/">reddit</Link>
      </Logo>
      <Buttons>
        {signed ? (
          <>
            <img src={user} alt="user" />
            <Button type="button" design="SignOut" onClick={signOut}>
              Sign Out
            </Button>
          </>
        ) : (
          <>
            <Button type="button" design="signIn" onClick={account}>
              Sign In
            </Button>
            <Button type="button" design="signUp" onClick={account}>
              Sign Up
            </Button>
          </>
        )}
      </Buttons>
      <FiMenu className="hamburguer" />
    </Container>
  );
};

export default Header;
