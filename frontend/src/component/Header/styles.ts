import styled, { css } from 'styled-components';

interface ToolsProps {
  signed: boolean;
}

export const Container = styled.div`
  align-items: center;
  background: white;
  display: flex;
  justify-content: space-between;
  padding: 6px 16px;
  position: relative;
  width: 100%;

  > a {
    align-items: center;
    color: black;
    display: flex;
    text-decoration: none;

    h1 {
      font-family: 'Roboto', sans-serif;
      font-size: 22px;
      font-weight: 500;
      margin-left: 6px;
    }
  }

  @media only screen and (max-width: 500px) {
    h1 {
      display: none;
    }
  }
`;

export const Tools = styled.div<ToolsProps>`
  align-items: center;
  display: flex;
  max-width: 280px;

  img {
    background: #c1c1c1;
    border-radius: 50%;
    margin-right: 10px;
    height: 32px;
    width: 32px;
    &:hover {
      cursor: pointer;
    }
  }

  button {
    &:nth-child(2) {
      background: hsl(205.6, 100%, 41.4%);
      border: 1px solid hsl(205.6, 100%, 41.4%);
      color: white;
      margin-left: 5px;

      &:hover {
        background: hsla(205.6, 100%, 41.4%, 0.9);
        border: 1px solid hsla(205.6, 100%, 41.4%, 0.9);
      }

      ${(props) =>
        props.signed &&
        css`
          background: transparent;
          border: 1px solid hsl(205.7, 100%, 32.9%);
          color: hsl(205.6, 100%, 41.4%);
          font-size: 12px;
          padding: 4px 16px;
          width: 80px;

          &:hover {
            background: transparent;
            border: 1px solid hsl(205.7, 100%, 32.9%);
          }
        `}
    }

    background: transparent;
    border-radius: 20px;
    border: 1px solid hsl(205.7, 100%, 32.9%);
    color: hsl(205.6, 100%, 41.4%);
    font-size: 14px;
    font-weight: 700;
    padding: 8px 16px;
    width: 100px;
    &:hover {
      cursor: pointer;
    }
  }
`;
