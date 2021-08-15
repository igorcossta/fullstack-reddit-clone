import styled from 'styled-components';

export const Container = styled.div`
  position: absolute;
  left: 0;
  width: 100%;
  padding: 50px;
  transition: 0.5s;

  h3 {
    color: var(--gray-dark);
    font-weight: 500;
    font-size: 1.5em;
    margin-bottom: 20px;
  }

  form {
    display: flex;
    flex-direction: column;
    width: 100%;

    button {
      background: #f43648;
      border-radius: 0;
      border: none;
      color: var(--white);
      margin-top: 10px;
      max-width: 100px;
      padding: 10px;
    }
  }

  svg {
    cursor: pointer;
    display: none;
    flex-shrink: 0;
    float: right;
    font-size: 20px;
  }

  @media only screen and (max-width: 803px) {
    svg {
      display: block;
    }
  }
`;
