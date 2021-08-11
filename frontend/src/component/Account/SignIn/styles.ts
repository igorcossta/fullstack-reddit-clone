import styled from 'styled-components';

export const Container = styled.div`
  position: absolute;
  left: 0;
  width: 100%;
  padding: 50px;
  transition: 0.5s;

  h3 {
    color: #333;
    font-weight: 500;
    font-size: 1.5em;
    margin-bottom: 20px;
  }

  form {
    display: flex;
    flex-direction: column;
    width: 100%;

    button {
      background: #03a9f4;
      border: none;
      color: #fff;
      cursor: pointer;
      font-weight: 500;
      margin-bottom: 10px;
      margin-top: 10px;
      max-width: 100px;
      padding: 10px;
    }
  }

  a {
    color: #333;
    text-decoration: none;
  }
`;
