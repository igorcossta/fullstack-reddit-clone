import React, { useCallback, useRef } from 'react';
import { useHistory, useParams } from 'react-router-dom';

import { FormHandles } from '@unform/core';
import { Form } from '@unform/web';
import * as Yup from 'yup';

import { PostPayLoad } from '../../../@types/subreddit.type';
import { RedditAPI } from '../../../axios/reddit.api';
import { Input, Toastr } from '../../../component';
import getValidationErrors from '../../../utils/getValidationErrors';
import { Container, Wrapper } from './styles';

const CreatePost: React.FC = () => {
  const formRef = useRef<FormHandles>(null);
  const history = useHistory();
  const { subredditName } = useParams<{ subredditName: string }>();

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

        const newPayLoad = { ...payload, subredditName: `${subredditName}`, url: `/r/${subredditName}/p/IDHERE` };
        console.log(newPayLoad);

        RedditAPI.post('/api/post', newPayLoad)
          .then(() => {
            Toastr.success('Post created!');
            history.replace({ pathname: `/r/${subredditName}`, state: {} });
          })
          .catch(() => {
            Toastr.danger('Error when trying to create a new post. Try later!');
          });
      } catch (err) {
        const errors = getValidationErrors(err);
        formRef.current?.setErrors(errors);
      }
    },
    [history, subredditName],
  );

  return (
    <Wrapper>
      <Container>
        <h3>Create new Post for {subredditName}</h3>
        <Form ref={formRef} onSubmit={handleSubmit}>
          <Input name="postName" type="text" placeholder="Post name" />
          <Input name="description" type="text" placeholder="Post description" />
          <button type="submit">Create</button>
        </Form>
      </Container>
    </Wrapper>
  );
};

export default CreatePost;
