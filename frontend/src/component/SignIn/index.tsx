import React, { useCallback, useRef } from 'react';
import { FiCornerUpLeft } from 'react-icons/fi';
import { Link, useHistory } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { Input } from '..';

import { SigninPayload } from '../../@types/account.type';
import { useAuth } from '../../context/account';
import getValidationErrors from '../../utils/getValidationErrors';
import Button from '../Button';
import { Container } from './styles';

interface Props {
  back: () => void;
}

const SignInForm: React.FC<Props> = ({ back }) => {
  const formRef = useRef<FormHandles>(null);
  const { signIn } = useAuth();
  const history = useHistory();

  const handleSubmit = useCallback(async (payload: SigninPayload) => {
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

      await signIn(payload);
    } catch (err) {
      const errors = getValidationErrors(err as Yup.ValidationError);
      formRef.current?.setErrors(errors);
    }
  }, []);

  return (
    <Container>
      <h3>Sign In</h3>
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="username" type="email" placeholder="john@doe.com" />
        <Input name="password" type="password" placeholder="**********" />
        <Button type="submit">Sign In</Button>
      </Form>
      <Link to="/">Forgot your password ?</Link>
      <FiCornerUpLeft onClick={back} />
    </Container>
  );
};

export default SignInForm;
