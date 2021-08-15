import styled from 'styled-components';

export const Container = styled.header`
  align-items: center;
  box-shadow: var(--box-shadow);
  display: flex;
  height: 50px;
  justify-content: space-between;
  width: 100%;
  padding: 0 20px;

  .hamburguer {
    color: var(--black);
    cursor: pointer;
    display: none;
    flex-shrink: 0;
    font-size: 32px;
  }

  @media only screen and (max-width: 400px) {
    .hamburguer {
      display: block;
    }
  }
`;

export const Logo = styled.div`
  align-items: center;
  display: flex;
  flex-shrink: 0;
  height: 100%;
  width: fit-content;
  grid-gap: 8px;

  svg {
    font-size: 36px;
  }

  a {
    color: var(--black);
    font-size: 18px;
    text-decoration: none;
    text-transform: uppercase;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }
`;

export const Buttons = styled.div`
  align-items: center;
  display: flex;
  justify-content: flex-end;
  width: 50%;

  img {
    flex-shrink: 0;
    font-size: 12px;
    height: 36px;
    margin-right: 20px;
    width: 36px;
  }

  @media only screen and (max-width: 400px) {
    display: none;
  }
`;
