import React from 'react';
import ReactDOM from 'react-dom';

import { Background, Container, Button } from './styles';

const root = document.getElementById('window') as HTMLElement;

interface Props {
  isHidden: boolean;
  toggle(): void;
}

const Modal: React.FC<Props> = ({ isHidden, toggle, children }) =>
  isHidden
    ? ReactDOM.createPortal(
        <Background>
          <Container>
            <div className="art" />
            {children}
            <Button onClick={toggle} />
          </Container>
        </Background>,
        root,
      )
    : null;

export default Modal;
