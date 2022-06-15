import React, { useCallback, useRef } from 'react';
import { AiOutlineClose } from 'react-icons/ai';
import { useHistory } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { SubredditPayLoad } from '../../@types/subreddit.type';
import { axios as RedditAPI } from '../../axios/axios.config';

import { Input, Toastr } from '..';

import getValidationErrors from '../../utils/getValidationErrors';
import Button from '../Button';
import { Container } from './styles';

const CreateSubredditForm: React.FC = () => {
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

        RedditAPI.post('/api/subreddit', payload)
          .then(() => {
            Toastr.success('Subreddit created!');
            history.replace({ pathname: '/dashboard', state: {} });
          })
          .catch(() => {
            Toastr.danger('Error when trying to create a new subreddit. Try later!');
          });
      } catch (err) {
        const errors = getValidationErrors(err as Yup.ValidationError);
        formRef.current?.setErrors(errors);
      }
    },
    [history],
  );

  return (
    <Container>
      <h3>Create new Subreddit</h3>
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="name" type="text" placeholder="Subreddit name" />
        <Input name="description" type="text" placeholder="Subreddit description" />
        <Button type="submit">Create</Button>
      </Form>
    </Container>
  );
};

export default CreateSubredditForm;
