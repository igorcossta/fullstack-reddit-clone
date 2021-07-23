import React, { useCallback, useContext, useRef } from 'react';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import Input from '../../component/Input';
import { AuthContext } from '../../hook/Authentication';
import api from '../../service/http';
import { UsernameAndPasswordRequest } from '../../service/type';
import getValidationErrors from '../../utils/getValidationErrors';
import { Container } from './styles';

const SignIn: React.FC = () => {
  const formRef = useRef<FormHandles>(null);
  const { signIn, isLoaded } = useContext(AuthContext);

  const handleSubmit = useCallback(
    async (data: UsernameAndPasswordRequest) => {
      try {
        formRef.current?.setErrors({});

        const schema = Yup.object().shape({
          username: Yup.string().required('enter your email').email('enter a valid email'),
          password: Yup.string().required('enter your password'),
        });

        await schema.validate(data, {
          abortEarly: false,
        });

        signIn(data) ? console.log('authenticated: @redirect') : alert('internal server error: @redirect-error-page');
      } catch (e) {
        const errors = getValidationErrors(e);
        formRef.current?.setErrors(errors);
      }
    },
    [signIn],
  );

  async function clickHere() {
    console.debug('POST SUBREDDIT:');

    let accessTokenObj = localStorage.getItem('@token:reddit');
    if (accessTokenObj) {
      accessTokenObj = JSON.parse(accessTokenObj);
    }

    try {
      const res = await api.post(
        '/api/subreddit/',
        {
          name: 'djasudasuodhasuidhasuidhasuidhasuidhaisuhd',
          description: 'djahsui9dhasuhdasyuhdiashdiashduiashduiasidhasuidh',
        },
        {
          headers: {
            Authorization: `Bearer ${accessTokenObj}`,
          },
        },
      );
      console.log(res);
    } catch (e) {
      if (e.response.status === 403) {
        alert('you must to be authenticated');
      }
    }
  }

  async function clickHere2() {
    console.debug('GET SUBREDDIT:');
    try {
      const res = await api.get('/api/subreddit/');
      console.log(res);
    } catch (e) {
      console.log(e);
    }
  }

  if (isLoaded) {
    return <h4>carregando...</h4>;
  }

  return (
    <Container>
      <button type="button" onClick={clickHere}>
        post subreddit
      </button>
      <button type="button" onClick={clickHere2}>
        get subreddit
      </button>
      <h3>Sign in page</h3>
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="username" type="email" placeholder="type your email" />
        <Input name="password" type="password" placeholder="type your password" />
        <button type="submit">Sign In</button>
      </Form>
    </Container>
  );
};

export default SignIn;
