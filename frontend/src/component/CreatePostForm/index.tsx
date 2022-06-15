import React, { useCallback, useRef } from 'react';
import { AiOutlineClose } from 'react-icons/ai';
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

interface Props {
  close: () => void;
}

const CreatePostForm: React.FC<Props> = ({ close }) => {
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

        const newPayLoad = { ...payload, subredditName: `${subredditName}`, url: `/r/${subredditName}/p/IDHERE` };

        RedditAPI.post('/api/post', newPayLoad)
          .then(() => {
            Toastr.success('Post created!');
            history.replace({ pathname: '/dashboard', state: {} });
          })
          .catch(() => {
            Toastr.danger('Error when trying to create a new post. Try later!');
          })
          .finally(() => close());
      } catch (err) {
        const errors = getValidationErrors(err);
        formRef.current?.setErrors(errors);
      }
    },
    [history, subredditName, close],
  );

  return (
    <Container>
      <h3>Create new Post for {subredditName}</h3>
      <AiOutlineClose onClick={close} />
      <Form ref={formRef} onSubmit={handleSubmit}>
        <Input name="postName" type="text" placeholder="Post name" />
        <Input name="description" type="text" placeholder="Post description" />
        <Button type="submit">Create</Button>
      </Form>
    </Container>
  );
};

export default CreatePostForm;
