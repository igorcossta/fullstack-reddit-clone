import React, { useCallback, useRef } from 'react';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { useAuth } from '../../hook/auth';
import { useToast } from '../../hook/toast';
import { SignInPayload } from '../../service/auth.type';
import getValidationErrors from '../../utils/getValidationErrors';
import Input from '../Input';
import Window from '../Window';
import { Container } from './styles';

interface Props {
  variable: boolean;
  func(): void;
}

const Signin: React.FC<Props> = ({ variable, func }) => {
  const formRef = useRef<FormHandles>(null);
  const { signIn } = useAuth();
  const { addToast } = useToast();

  const submit = useCallback(
    async (data: SignInPayload) => {
      try {
        formRef.current?.setErrors({});

        const schema = Yup.object().shape({
          username: Yup.string().required('enter your email').email('enter a valid email'),
          password: Yup.string().required('enter your password'),
        });

        await schema.validate(data, {
          abortEarly: false,
        });

        await signIn(data);
      } catch (e) {
        if (e instanceof Yup.ValidationError) {
          const errors = getValidationErrors(e);
          formRef.current?.setErrors(errors);
        } else {
          addToast({
            title: 'Login Error!',
            description: 'maybe your credentials is invalid',
            type: 'error',
          });
        }
      }
    },
    [signIn, addToast],
  );

  return (
    <Window isShowing={variable} hide={func}>
      <Container>
        <h1>Login</h1>
        <p>
          By continuing, you agree to our <a href="/">User Agreement</a> and <a href="/">Privacy Policy</a>.
        </p>

        <Form ref={formRef} onSubmit={submit}>
          <Input name="username" type="text" placeholder="username" />
          <Input name="password" type="password" placeholder="password" />
          <button type="submit">Log In</button>
        </Form>

        <div className="forgot buttonText">
          <span>Forgot your </span>
          <a href="/">username </a>
          <span>or </span>
          <a href="/">password</a>
          <span>?</span>
        </div>

        <div className="buttonText">
          <span>New to Reddit? </span>
          <a href="/"> SIGN UP</a>
        </div>
      </Container>
    </Window>
  );
};

export default Signin;
