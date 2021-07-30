import { MdClose } from 'react-icons/md';

import styled from 'styled-components';

export const Background = styled.main`
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  height: 100vh;
  left: 0;
  overflow-x: hidden;
  overflow-y: auto;
  position: fixed;
  top: 0;
  width: 100vw;
  z-index: 5;
`;

export const Container = styled.div`
  background: white;
  border-radius: 3px;
  display: grid;
  flex: 1;
  grid-template-columns: 156px 1fr;
  margin: 0.5rem auto;
  max-height: 850px;
  max-width: 850px;
  position: relative;

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
`;
