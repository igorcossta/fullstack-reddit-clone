import styled from 'styled-components';

export const Container = styled.main`
  column-gap: 20px;
  display: grid;
  grid-template-areas: 'section side';
  grid-template-columns: 2fr 1fr;
  margin: 25px auto 0 auto;
  max-width: 968px;

  @media only screen and (max-width: 500px) {
    display: block;
  }
`;

export const Content = styled.section`
  border: 1px dashed;
  grid-area: section;
`;

export const Side = styled.aside`
  border: 1px dashed;
  grid-area: side;
`;
