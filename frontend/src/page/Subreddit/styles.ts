import styled from 'styled-components';

export const Container = styled.div`
  display: grid;
  gap: 20px;
  grid-template-columns: 1fr 300px;
  grid-template-areas: 'Posts banner';

  @media only screen and (max-width: 768px) {
    display: flex;
    flex-direction: column;
  }
`;

export const Banner = styled.div`
  grid-area: banner;

  > div {
    align-items: center;
    background: var(--blue);
    box-shadow: var(--box-shadow);
    color: var(--white);
    display: flex;
    flex-direction: column;
    height: 200px;
    justify-content: center;
    overflow: hidden;

    h3 {
      text-transform: uppercase;
    }

    img {
      height: 50px;
      width: 50px;
    }
  }

  button {
    margin-top: 15px;
    width: 100%;
  }
`;

export const Posts = styled.div`
  grid-area: Posts;
  overflow: hidden;
`;
