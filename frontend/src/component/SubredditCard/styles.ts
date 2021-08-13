import styled from 'styled-components';

export const Container = styled.div`
  border: 0.5px solid rgba(0, 0, 0, 0.04);
  width: 85%;
  position: relative;

  & + div {
    margin-top: 35px;
  }

  &:hover {
    border: 0.5px solid rgba(0, 0, 0, 0.1);
    cursor: pointer;
  }

  h1 {
    padding-bottom: 12px;
  }

  p {
    color: var(--gray-dark);
    overflow: hidden;
    text-overflow: ellipsis;
    padding-bottom: 12px;
  }

  span {
    color: var(--gray);
    font-size: 12px;
    strong {
      color: black;
    }
  }

  a {
    text-decoration: none;
    color: var(--blue);
    position: absolute;
    right: 12px;
    top: 0;
    &:hover {
      text-decoration: underline;
    }
  }
`;
