import React, { useCallback, useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';

import { Button, SignInForm, SignUpForm } from '../../component';
import { useAuth } from '../../context/account';
import { Container, Background, Window, Box, FormBox } from './styles';

const Account: React.FC = () => {
  const [formState, setFormState] = useState('signIn');
  const { signed, confirmToken } = useAuth();
  const query = new URLSearchParams(useLocation().search);

  const signUpBox = useCallback(() => {
    setFormState('signUp');
  }, []);

  const signInBox = useCallback(() => {
    setFormState('signIn');
  }, []);

  useEffect(() => {
    const token = query.get('token');
    if (token && !signed) {
      confirmToken(token);
    }
  }, []);

  return (
    <Container formState={formState}>
      <Background>
        <Window>
          <Box>
            <h2>Already have an account ?</h2>
            <Button type="button" design="white" onClick={signInBox}>
              Sign In
            </Button>
          </Box>
          <Box>
            <h2>Don&apos;t have an account ?</h2>
            <Button type="button" design="white" onClick={signUpBox}>
              Sign Up
            </Button>
          </Box>
        </Window>
        <FormBox formState={formState}>
          {formState === 'signIn' ? <SignInForm back={signUpBox} /> : <SignUpForm back={signInBox} />}
        </FormBox>
      </Background>
    </Container>
  );
};

export default Account;
