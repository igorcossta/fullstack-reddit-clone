import React, { useCallback, useRef } from 'react';
import { Link } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { Input } from '../..';
import { useAuth } from '../../../context/account';
import { SigninPayload } from '../../../context/account.type';
import getValidationErrors from '../../../utils/getValidationErrors';
import { Container } from './styles';

const SignInForm: React.FC = () => {
  const formRef = useRef<FormHandles>(null);
  const { SignIn } = useAuth();

  const handleSubmit = useCallback(
    async (payload: SigninPayload) => {
      try {
        formRef.current?.setErrors({});
        const schema = Yup.object().shape({
          username: Yup.string().required('email is required').email('enter a valid email'),
          password: Yup.string()
            .required('password is required')
            .matches(
              /^.*(?=.{8,20})((?=.*[!@#$%&]){1})(?=.*\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$/,
              'Password must contain at least one digit<br /> one lowercase and uppercase Latin character<br /> one special character<br /> and length of at least 8 characters<br /> and a maximum of 20 characters.',
            ),
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
      <h3>Sign In</h3>
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="username" type="email" placeholder="john@doe.com" />
        <Input name="password" type="password" placeholder="**********" />
        <button type="submit">Sign In</button>
      </Form>
      <Link to="/">Forgot your password ?</Link>
    </Container>
  );
};

export default SignInForm;
