import React, { useCallback, useRef } from 'react';
import { useHistory } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { SubredditPayLoad } from '../../../@types/subreddit.type';
import { RedditAPI } from '../../../axios/reddit.api';
import { Input, Toastr } from '../../../component';
import getValidationErrors from '../../../utils/getValidationErrors';
import { Container, Wrapper } from './styles';

const CrSubreddit: React.FC = () => {
  const formRef = useRef<FormHandles>(null);
  const history = useHistory();

  const handleSubmit = useCallback(
    async (payload: SubredditPayLoad) => {
      try {
        formRef.current?.setErrors({});
        const schema = Yup.object().shape({
          name: Yup.string()
            .min(10, 'name must be 10 characters')
            .max(30, 'name cannot be greater than 30 characters')
            .required('name is required'),
          description: Yup.string()
            .min(10, 'name must be 10 characters')
            .max(255, 'description cannot be greater than 255 characters')
            .required('description is required'),
        });

        await schema.validate(payload, {
          abortEarly: false,
        });

        RedditAPI.post('/api/post', payload)
          .then(() => {
            Toastr.success('Subreddit created!');
            history.replace({ pathname: '/account', state: {} });
          })
          .catch(() => {
            Toastr.danger('Error when trying to create a new subreddit. Try later!');
          });
      } catch (err) {
        const errors = getValidationErrors(err);
        formRef.current?.setErrors(errors);
      }
    },
    [history],
  );

  return (
    <Wrapper>
      <Container>
        <h3>Create new Subreddit</h3>
        <Form ref={formRef} onSubmit={handleSubmit}>
          <Input name="name" type="text" placeholder="Subreddit name" />
          <Input name="description" type="text" placeholder="Subreddit description" />
          <button type="submit">Create</button>
        </Form>
      </Container>
    </Wrapper>
  );
};

export default CrSubreddit;
