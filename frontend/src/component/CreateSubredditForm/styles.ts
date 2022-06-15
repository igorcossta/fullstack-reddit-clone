import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  background-color: white;

  svg {
    cursor: pointer;
    flex-shrink: 0;
    font-size: 26px;
    position: absolute;
    right: 12px;
    top: 12px;
  }

  h3 {
    color: black;
    padding-bottom: 10px;
    text-align: center;
  }

  form {
    margin: 0 auto 20px auto;
    width: 300px;

    button {
      margin-top: 10px;
      width: 100%;
    }
  }
`;
