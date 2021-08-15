import styled from 'styled-components';

export const Container = styled.div`
  display: flex;
  justify-content: center;
  min-height: calc(100vh - 50px);

  @media only screen and (max-width: 768px) {
    align-items: center;
    flex-direction: column;
    justify-content: start;
  }
`;

export const Main = styled.main`
  padding: 20px;
  width: 968px;

  @media only screen and (max-width: 768px) {
    padding: 0 20px 0 20px;
    width: 100%;
  }
`;
