import styled from 'styled-components';

export const Container = styled.div`
  border: 0.5px solid rgba(0, 0, 0, 0.04);
  position: relative;

  & + div {
    margin-top: 25px;
  }

  &:hover {
    border: 0.5px solid rgba(0, 0, 0, 0.1);
    cursor: pointer;
  }

  span {
    text-transform: uppercase;
  }

  a {
    color: var(--blue);
    padding: 0 10px 0 10px;
    position: absolute;
    right: 0;
    text-decoration: none;
    top: 0;
  }

  @media only screen and (max-width: 400px) {
    a {
      padding: 0;
      position: relative;
    }
  }
`;
