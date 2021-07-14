import styled from 'styled-components';

export const MT = styled.div`
  margin-top: 10%;
`;

export const Container = styled.div`
  /* min-height: calc(100vh - 51px); */
  /* border: 1px solid red; */
  margin-top: 49px;
`;

export const Body = styled.div`
  /* border: 1px solid; */
  display: grid;
  column-gap: 20px;
  grid-template-areas: 'post side';
  grid-template-columns: 2fr 1fr;
  margin: 0 auto;
  width: 968px;
`;

export const Content = styled.div`
  /* border: 1px solid; */
  grid-area: post;
`;

export const Side = styled.div`
  /* border: 1px solid; */
  grid-area: side;
`;
