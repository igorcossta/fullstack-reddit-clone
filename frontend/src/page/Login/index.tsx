import React, { useCallback, useRef } from 'react';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { Input } from '../../component';
import { useAuth } from '../../context/authentication';
import { SigninPayload } from '../../context/authentication.type';
import getValidationErrors from '../../utils/getValidationErrors';
import { Container } from './styles';

const Login: React.FC = () => {
  const formRef = useRef<FormHandles>(null);
  const { SignIn, loading } = useAuth();

  const handleSubmit = useCallback(
    async (payload: SigninPayload) => {
      try {
        formRef.current?.setErrors({});
        const schema = Yup.object().shape({
          username: Yup.string().required('username is necessary').email('enter a valid email'),
          password: Yup.string().required('password is necessary'),
        });

        await schema.validate(payload, {
          abortEarly: false,
        });

        SignIn(payload);
      } catch (err) {
        const errors = getValidationErrors(err);
        formRef.current?.setErrors(errors);
      }
    },
    [SignIn],
  );

  return (
    <Container>
      <div>
        <h1>Login</h1>
        <span>
          By continuing, you agree to our <button type="button">User Agreement</button> and{' '}
          <button type="button"> Privacy Policy</button>.
        </span>
      </div>

      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="username" placeholder="username" />
        <Input name="password" type="password" placeholder="password" />
        {loading ? (
          <button type="button" disabled>
            Loading
          </button>
        ) : (
          <button type="submit">Log In</button>
        )}
      </Form>

      <div>
        <span>
          Forgot your <button type="button"> username </button> or <button type="button"> password </button> ?
        </span>
        <span>
          New to Reddit? <button type="button"> Sign Up</button>
        </span>
      </div>
    </Container>
  );
};

export default Login;
