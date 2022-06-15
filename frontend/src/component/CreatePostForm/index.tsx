import React, { useCallback, useRef } from 'react';
import { useHistory, useParams } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { PostPayLoad } from '../../@types/post.type';
import { axios as RedditAPI } from '../../axios/axios.config';

import { Input, Toastr } from '..';

import getValidationErrors from '../../utils/getValidationErrors';
import Button from '../Button';
import { Container } from './styles';

const CreatePostForm: React.FC = () => {
  const { subredditName } = useParams<{ subredditName: string }>();
  const formRef = useRef<FormHandles>(null);
  const history = useHistory();

  const handleSubmit = useCallback(
    async (payload: PostPayLoad) => {
      try {
        formRef.current?.setErrors({});
        const schema = Yup.object().shape({
          postName: Yup.string()
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

        const newPayLoad = {
          ...payload,
          subredditName: `${subredditName}`,
          url: `http://localhost:3000/r/${subredditName}/p/IDHERE`,
        };

        RedditAPI.post('/api/post', newPayLoad)
          .then(() => {
            Toastr.success('Post created!');
            history.replace({ pathname: '/dashboard', state: {} });
          })
          .catch(() => {
            Toastr.danger('Error when trying to create a new post. Try later!');
          });
      } catch (err) {
        const errors = getValidationErrors(err as Yup.ValidationError);
        formRef.current?.setErrors(errors);
      }
    },
    [history, subredditName],
  );

  return (
    <Container>
      <h3>Create new Post for {subredditName}</h3>
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="postName" type="text" placeholder="Post name" />
        <Input name="description" type="text" placeholder="Post description" />
        <Button type="submit">Create</Button>
      </Form>
    </Container>
  );
};

export default CreatePostForm;
