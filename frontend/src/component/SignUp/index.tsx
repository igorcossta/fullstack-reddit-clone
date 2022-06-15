import React, { useCallback, useRef } from 'react';
import { FiCornerUpLeft } from 'react-icons/fi';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { Button, Input, Toastr } from '..';

import { SignupPayload } from '../../@types/account.type';
import { useAuth } from '../../context/account';
import getValidationErrors from '../../utils/getValidationErrors';
import { Container } from './styles';

interface Props {
  back: () => void;
}

const SignUpForm: React.FC<Props> = ({ back }) => {
  const formRef = useRef<FormHandles>(null);
  const { signUp } = useAuth();

  const handleSubmit = useCallback(async (payload: SignupPayload) => {
    try {
      formRef.current?.setErrors({});
      const schema = Yup.object().shape({
        firstName: Yup.string()
          .min(3, 'first name must be 3 characters')
          .max(30, 'first name cannot be greater than 30 characters')
          .required('first name is required'),
        lastName: Yup.string()
          .min(3, 'last name must be 3 characters')
          .max(30, 'last name cannot be greater than 30 characters')
          .required('last name is required'),
        email: Yup.string().required('email is required').email('enter a valid email'),
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

      await signUp(payload);
      Toastr.info('check your email to activate your account');
    } catch (err) {
      const errors = getValidationErrors(err as Yup.ValidationError);
      formRef.current?.setErrors(errors);
    }
  }, []);

  return (
    <Container>
      <h3>Sign Up</h3>
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="firstName" type="text" placeholder="First Name" />
        <Input name="lastName" type="text" placeholder="Last Name" />
        <Input name="email" type="email" placeholder="john@doe.com" />
        <Input name="password" type="password" placeholder="**********" />
        <Button type="submit">Sign Up</Button>
      </Form>
      <FiCornerUpLeft onClick={back} />
    </Container>
  );
};

export default SignUpForm;
