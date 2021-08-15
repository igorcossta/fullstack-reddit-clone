import styled from 'styled-components';

export const Container = styled.div`
  display: grid;
  grid-gap: 20px;
  grid-template-areas: 'section aside';
  grid-template-columns: 2fr 1fr;

  @media only screen and (max-width: 768px) {
    display: flex;
    flex-direction: column;
  }
`;

export const Section = styled.section`
  grid-area: section;
`;

export const Aside = styled.aside`
  grid-area: aside;
  height: 150px;
  text-align: center;
`;
