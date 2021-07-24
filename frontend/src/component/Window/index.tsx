import React from 'react';
import ReactDOM from 'react-dom';

import { Container, Window as Win, Button } from './styles';

const root = document.getElementById('window') as HTMLElement;

interface Props {
  isShowing: boolean;
  hide(): void;
}

const Window: React.FC<Props> = ({ isShowing, hide, children }) =>
  isShowing
    ? ReactDOM.createPortal(
        <Container>
          <Win>
            <div className="art" />
            <>{children}</>
            <Button onClick={hide} />
          </Win>
        </Container>,
        root,
      )
    : null;

export default Window;
