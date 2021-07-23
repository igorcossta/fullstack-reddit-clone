import styled from 'styled-components';

export const Container = styled.div`
  column-gap: 20px;
  display: grid;
  grid-template-areas: 'post side';
  grid-template-columns: 2fr 1fr;
  margin: 0 auto;
  max-width: 968px;
`;

export const Content = styled.div`
  grid-area: post;
`;

export const Side = styled.div`
  /* border: 1px solid red; */
  grid-area: side;
`;
