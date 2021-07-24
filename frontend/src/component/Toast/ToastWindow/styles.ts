import styled, { css } from 'styled-components';

interface Props {
  type?: 'success' | 'info' | 'error';
}

const variations = {
  info: css`
    background: #2196f3;
  `,
  success: css`
    background: #4caf50;
  `,
  error: css`
    background: #f44336;
  `,
};

export const Container = styled.div<Props>`
  ${(props) => variations[props.type || 'info']}
  color: white;

  box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.2);
  display: flex;
  margin-top: 5px;
  padding: 16px;
  position: relative;
  width: 360px;

  button {
    position: absolute;
    top: 17px;
    right: 16px;
    background: transparent;
    border: none;
    color: white;

    &:hover {
      cursor: pointer;
    }
  }
`;
