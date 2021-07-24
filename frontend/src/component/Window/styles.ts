import { MdClose } from 'react-icons/md';

import styled from 'styled-components';

export const Container = styled.main`
  background-color: rgba(0, 0, 0, 0.4);
  height: 100vh;
  left: 0;
  overflow-x: hidden;
  overflow-y: auto;
  position: fixed;
  top: 0;
  width: 100vw;
  z-index: 1000;
`;

export const Window = styled.div`
  background: white;
  border-radius: 3px;
  display: grid;
  grid-template-columns: 156px 1fr;
  height: 90%;
  margin: 0.5rem auto;
  max-height: 850px;
  max-width: 850px;
  position: relative;
  z-index: 100;

  .art {
    background: #fff url('https://www.redditstatic.com/accountmanager/bbb584033aa89e39bad69436c504c9bd.png') no-repeat;
    background-size: cover;
    height: 100%;
    width: 156px;
  }
`;

export const Button = styled(MdClose)`
  cursor: pointer;
  position: absolute;
  top: 8px;
  right: 20px;
  width: 32px;
  height: 32px;
  padding: 0;
  z-index: 109;
`;
