import React from 'react';

import { Container } from './styles';

interface Props {
  type?: 'button' | 'submit' | 'reset';
  design?: 'signIn' | 'signUp' | 'SignOut' | 'white';
  onClick?: () => void;
}

const Button: React.FC<Props> = ({ children, type, design, onClick }) => {
  return (
    <Container type={type || 'button'} design={design} onClick={onClick}>
      {children}
    </Container>
  );
};

export default Button;
