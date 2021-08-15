import styled from 'styled-components';

export const Wrapper = styled.main`
  display: flex;
  justify-content: center;
  min-height: calc(100vh - 50px);
`;

export const Container = styled.section`
  width: 968px;
  padding: 20px 20px 0 20px;

  .subreddit__bg {
    align-items: center;
    background: var(--blue);
    color: var(--white);
    display: flex;
    height: 100px;
    justify-content: center;
    flex-direction: column;
    width: 100%;
    margin-bottom: 25px;
  }

  .subreddit__content {
    display: grid;
    grid-template-columns: 1fr 300px;
    gap: 25px;
  }

  .subreddit__tools {
    display: flex;
    justify-content: center;
    height: 500px;

    a {
      background-color: var(--blue);
      color: var(--white);
      font-weight: 500;
      height: fit-content;
      padding: 14px;
      text-decoration: none;
      width: 50%;
    }
  }
`;
