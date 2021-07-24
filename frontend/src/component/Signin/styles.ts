import styled from 'styled-components';

export const Container = styled.div`
  padding: 24px;

  h1 {
    font-size: 18px;
    line-height: 22px;
  }

  p {
    font-size: 12px;
    line-height: 18px;
    margin-bottom: 48px;
    margin-top: 8px;
  }

  a {
    text-decoration: none;
    background-color: transparent;
    color: #0079d3;

    &:hover {
      color: #3394dc;
    }
  }

  .buttonText {
    font-size: 12px;
    line-height: 18px;
    margin-top: 8px;
  }

  .forgot {
    margin-bottom: 20px;
  }

  button {
    background: #0079d3;
    border-radius: 999px;
    border: none;
    color: #fff;
    font-size: 14px;
    font-weight: 700;
    height: 40px;
    padding: 0 16px;

    margin-top: 12px;
    max-width: 392px;
    min-height: 35px;
    min-width: 155px;

    transition: color 0.01s ease-in, text-indent 0.25s ease-in, opacity 0.25s ease-in;
    cursor: pointer;

    &:hover {
      background: #3394dc;
    }
  }
`;
