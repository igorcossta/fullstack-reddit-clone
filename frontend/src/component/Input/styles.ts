import styled, { css } from 'styled-components';

interface Props {
  isError: boolean;
  isFill: boolean;
  isFocus: boolean;
}

export const Container = styled.fieldset<Props>`
  align-items: center;
  display: flex;

  border: 1px solid;
  border-color: var(--secondary);
  outline: none;
  width: 100%;

  & + fieldset {
    margin-top: 8px;
  }

  svg {
    margin-left: 16px;
    color: var(--danger);
  }

  ${(props) =>
    props.isFocus &&
    css`
      border-color: var(--primary);
    `}

  ${(props) =>
    props.isFill &&
    css`
      border-color: var(--primary);
    `}

      ${(props) =>
    props.isError &&
    css`
      border-color: var(--danger);
    `}

  input {
    background: transparent;
    border: 0;
    padding: 10px;
    outline: 0;

    &::placeholder {
      color: var(--secondary);
    }
  }
`;
