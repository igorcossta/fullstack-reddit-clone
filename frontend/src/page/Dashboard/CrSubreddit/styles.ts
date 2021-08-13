import styled from 'styled-components';

export const Wrapper = styled.main`
  display: flex;
  justify-content: center;
  min-height: calc(100vh - 50px);
`;

export const Container = styled.section`
  width: 600px;

  h3 {
    padding: 20px 0;
    text-align: center;
  }

  form {
    margin: 0 auto;
    width: 300px;
  }

  button {
    background-color: var(--blue);
    border: none;
    color: var(--white);
    cursor: pointer;
    font-weight: 500;
    margin-left: 25%;
    margin-top: 10px;
    padding: 14px;
    width: 50%;
  }
`;
