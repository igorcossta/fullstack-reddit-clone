import styled from 'styled-components';

export const Wrapper = styled.main`
  display: flex;
  justify-content: center;
  min-height: calc(100vh - 50px);
  @media only screen and (max-width: 824px) {
    min-height: auto;
  }
`;

export const Container = styled.section`
  display: grid;
  grid-template-columns: 1fr 300px;
  padding: 20px 20px 0 20px;
  width: 968px;

  .tools {
    border: 1px solid rgba(0, 0, 0, 0.1);
    align-items: center;
    display: flex;
    flex-direction: column;
    max-height: 500px;

    .user__info {
      display: flex;
      align-items: center;
      flex-direction: column;
      width: 100%;
      margin-bottom: 100px;

      h1 {
        font-size: 16px;
      }

      h2 {
        font-size: 12px;
      }
    }

    .user__crSubreddit {
      background-color: var(--blue);
      color: var(--white);
      font-weight: 500;
      padding: 14px;
      width: 50%;
      text-decoration: none;
    }

    .user__photo {
      background: var(--blue);
      display: flex;
      justify-content: center;
      width: 100%;
      padding: 10px;
      margin-bottom: 8px;

      img {
        border-radius: 50%;
        width: 50px;
        height: 50px;
      }
    }
  }

  @media only screen and (max-width: 824px) {
    grid-template-columns: 1fr;
    padding: 0;

    .tools {
      height: 200px;
      order: -1;

      .user__info {
        margin-bottom: 25px;
      }
    }

    .subreddit {
      display: flex;
      align-items: center;
      flex-direction: column;
      margin-top: 5px;
    }
  }
`;
