import styled from 'styled-components';

export const Container = styled.main`
  align-items: center;
  display: flex;
  flex-wrap: wrap;
  height: calc(100vh - 50px);
  justify-content: center;
  overflow: hidden;
`;

export const Content = styled.section`
  background: white;
  border-radius: 10px;
  box-shadow: 0px 0px 10px 5px rgba(0, 0, 0, 0.1);
  padding: 77px 55px 33px;
  text-align: center;
  width: 390px;

  h1 {
    padding-bottom: 50px;
    font-weight: 700;
    font-size: 20px;
  }

  p {
    color: var(--secondary);
    a {
      color: var(--gray-dark);
      text-decoration: none;
    }
  }

  button {
    background: var(--primary);
    border-radius: 16px;
    border: transparent;
    color: white;
    font-size: 16px;
    font-weight: 700;
    height: 40px;
    margin-bottom: 115px;
    margin-top: 8px;
    width: 270px;
    &:hover {
      cursor: pointer;
      opacity: 0.9;
    }
  }
  @media only screen and (max-width: 768px) {
    height: 100%;
    width: 100%;
    border-top: 1px solid;
    border-top-right-radius: 0;
    border-top-left-radius: 0;
    border-top-color: hsl(340, 88.2%, 96.7%);
  }
`;
