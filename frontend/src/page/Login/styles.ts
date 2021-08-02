import styled from 'styled-components';

export const Container = styled.main`
  color: hsl(240, 1.9%, 10.4%);
  font-size: 12px;
  height: 100%;
  line-height: 18px;
  margin: 40px auto 0 auto;
  width: 50%;

  button {
    color: hsl(205.6, 70.7%, 53.1%);
    background: transparent;
    border: transparent;
    &:hover {
      color: hsl(205.6, 100%, 41.4%);
      cursor: pointer;
    }
  }

  h1 {
    font-size: 18px;
    line-height: 22px;
  }

  form {
    display: flex;
    flex-direction: column;
    button {
      background: var(--secondary-color);
      border-radius: 20px;
      color: hsl(0, 0%, 100%);
      font-family: 'NotoSans', sans-serif;
      font-size: 14px;
      font-weight: 700;
      height: 40px;
      margin-top: 16px;
      padding: 0 16px;
      width: 260px;
      &:hover {
        background: hsl(205.6, 70.7%, 53.1%);
        color: hsl(0, 0%, 100%);
      }
    }
  }

  div {
    // div -> titulo e acordos
    &:first-child {
      margin-bottom: 48px;
      h1 {
        margin-bottom: 8px;
      }
    }

    // div -> recuperar conta ou criar uma nova
    &:last-child {
      margin-top: 8px;
      display: flex;
      flex-direction: column;

      // botao de Sign Up
      span {
        &:last-child {
          margin-top: 28px;
          button {
            font-weight: 600;
            letter-spacing: 0.5px;
            line-height: 24px;
            text-transform: uppercase;
            color: hsl(205.6, 100%, 41.4%);
          }
        }
      }
    }
  } // fim das divs
`;
