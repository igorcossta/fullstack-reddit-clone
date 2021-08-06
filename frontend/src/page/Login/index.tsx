import React, { useCallback, useRef } from 'react';
import { Link } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { Input } from '../../component';
import { useAuth } from '../../context/authentication';
import { SigninPayload } from '../../context/authentication.type';
import getValidationErrors from '../../utils/getValidationErrors';
import { Container, Content } from './styles';

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
      <Content>
        <Form ref={formRef} onSubmit={handleSubmit}>
          <h1>Login</h1>
          <Input name="username" placeholder="username" />
          <Input name="password" type="password" placeholder="password" />
          {loading ? (
            <button type="button" disabled>
              Loading
            </button>
          ) : (
            <button type="submit">Log In</button>
          )}
          <p>
            Forgot your password? <Link to="/recovery">Recovery</Link>
          </p>
          <p>
            Don’t have an account? <Link to="/signup">Sign Up</Link>
          </p>
        </Form>
      </Content>
    </Container>
  );
};

export default Login;
