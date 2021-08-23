import styled from 'styled-components';

export const Container = styled.div`
  align-items: center;
  color: var(--gray);
  display: flex;
  flex-direction: column;
  justify-content: center;
  position: relative;

  svg {
    margin-bottom: 25px;
  }

  h1 {
    font-size: 1.1rem;
    margin-bottom: 12px;
  }

  p {
    font-size: 0.85rem;
    padding-bottom: 3px;
  }

  > div {
    align-items: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
`;
