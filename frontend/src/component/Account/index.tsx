import React, { useCallback, useState, useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';

import { useAuth } from '../../context/account';
import { Dashboard } from '../../page';
import SignInForm from './SignIn';
import SignUpForm from './SignUp';
import { Wrapper, Container } from './styles';

const Account: React.FC = () => {
  const [formState, setFormState] = useState('signIn');
  const { signed, ConfirmToken } = useAuth();
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
      ConfirmToken(token);
    }
  }, [ConfirmToken, signed]);

  return (
    <>
      {signed ? (
        <Dashboard />
      ) : (
        <Wrapper formState={formState}>
          <Container formState={formState}>
            <div className="blueBg">
              <div className="box">
                <h2>Already have an account ?</h2>
                <button type="button" onClick={signInBox}>
                  Sign In
                </button>
              </div>

              <div className="box">
                <h2>Don&apos;t have an account ?</h2>
                <button type="button" onClick={signUpBox}>
                  Sign Up
                </button>
              </div>
            </div>
            <div className="formBx">
              {formState === 'signIn' ? (
                <>
                  <button type="button" className="sm-screen" onClick={signUpBox}>
                    Sign Up
                  </button>
                  <SignInForm />
                </>
              ) : (
                <>
                  <button type="button" className="sm-screen" onClick={signInBox}>
                    Sign In
                  </button>
                  <SignUpForm />
                </>
              )}
            </div>
          </Container>
        </Wrapper>
      )}
    </>
  );
};

export default Account;
