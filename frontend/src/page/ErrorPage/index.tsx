import React from 'react';
import { HiOutlineEmojiSad } from 'react-icons/hi';

import { Container } from './styles';

const ErrorPage: React.FC = () => {
  return (
    <Container>
      <HiOutlineEmojiSad size={128} />
      <div>
        <h1>Looks like something went wrong!</h1>
        <p>The page you are looking for get an error.</p>
        <p>Try refresh the page or come back later!</p>
      </div>
    </Container>
  );
};

export default ErrorPage;
