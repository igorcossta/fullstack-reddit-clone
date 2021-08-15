import styled, { css } from 'styled-components';

interface Props {
  design?: 'signIn' | 'signUp' | 'SignOut' | 'white';
}

export const Container = styled.button<Props>`
  background: transparent;
  border-radius: 20px;
  border: 1px solid hsl(205.7, 100%, 32.9%);
  color: hsl(205.6, 100%, 41.4%);
  cursor: pointer;
  font-size: 14px;
  font-weight: 700;
  padding: 8px 16px;

  & + button {
    margin-left: 5px;
  }

  ${(props) =>
    props.design === 'signUp' &&
    css`
      background: hsl(205.6, 100%, 41.4%);
      border: 1px solid hsl(205.6, 100%, 41.4%);
      color: white;
    `}

  ${(props) =>
    props.design === 'white' &&
    css`
      background: var(--white);
      border-radius: 0;
      border: none;
      color: var(--black);
      cursor: pointer;
      font-size: 16px;
      font-weight: 500;
      padding: 10px 20px;
    `}
`;
