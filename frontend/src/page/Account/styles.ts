import styled, { css } from 'styled-components';

interface Props {
  formState?: string;
}

export const Container = styled.div<Props>`
  background: #03a9f4;
  bottom: 0;
  left: 0;
  position: absolute;
  right: 0;
  top: 50px;
  transition: 0.5s;

  ${(props) =>
    props.formState === 'signUp' &&
    css`
      background: #f43648;
    `}
`;

export const Background = styled.div`
  height: 500px;
  margin: 20px auto;
  position: relative;
  width: 800px;

  @media only screen and (max-width: 803px) {
    width: 100%;
  }
`;

export const Window = styled.div`
  align-items: center;
  background: rgba(255, 255, 255, 0.2);
  box-shadow: 0 5px 45px rgba(0, 0, 0, 0.15);
  display: flex;
  height: 420px;
  justify-content: center;
  position: absolute;
  top: 40px;
  width: 100%;
`;

export const Box = styled.div`
  align-items: center;
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: center;
  position: relative;
  width: 50%;

  h2 {
    color: #fff;
    font-size: 1.2em;
    font-weight: 500;
    margin-bottom: 10px;
  }
`;

export const FormBox = styled.div<Props>`
  align-items: center;
  background: #fff;
  box-shadow: 0 5px 45px rgba(0, 0, 0, 0.25);
  display: flex;
  height: 100%;
  justify-content: center;
  left: 0;
  position: absolute;
  top: 0;
  transition: 0.5s ease-in-out;
  width: 50%;
  z-index: 99;

  h2 {
    color: #fff;
    font-size: 1.2em;
    font-weight: 500;
    margin-bottom: 10px;
  }

  @media only screen and (max-width: 803px) {
    & {
      width: 100%;
      ${(props) =>
        props.formState === 'signUp' &&
        css`
          left: 0;
        `}
    }
  }

  ${(props) =>
    props.formState === 'signUp' &&
    css`
      left: 50%;
    `}
`;
