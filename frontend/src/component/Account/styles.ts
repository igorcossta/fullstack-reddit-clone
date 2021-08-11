import styled, { css } from 'styled-components';

interface Props {
  formState: string;
}

export const Wrapper = styled.main<Props>`
  align-items: center;
  background: #03a9f4;
  display: flex;
  height: calc(100vh - 50px);
  justify-content: center;
  transition: 0.5s;

  ${(props) =>
    props.formState === 'signUp' &&
    css`
      background: #f43648;
    `}
`;

export const Container = styled.section<Props>`
  height: 500px;
  margin: 20px;
  position: relative;
  width: 800px;

  .blueBg {
    align-items: center;
    background: rgba(255, 255, 255, 0.2);
    box-shadow: 0 5px 45px rgba(0, 0, 0, 0.15);
    display: flex;
    height: 420px;
    justify-content: center;
    position: absolute;
    top: 40px;
    width: 100%;

    .box {
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

      button {
        background: #fff;
        border: none;
        color: #333;
        cursor: pointer;
        font-size: 16px;
        font-weight: 500;
        padding: 10px 20px;
      }
    }
  }

  .formBx {
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

    .sm-screen {
      text-transform: uppercase;
      background: none;
      border: none;
      cursor: pointer;
      display: none;
      font-size: 14px;
      padding: 12px;
    }

    @media only screen and (max-width: 768px) {
      & {
        width: 100%;
        ${(props) =>
          props.formState === 'signUp' &&
          css`
            left: 0;
          `}
      }

      .blueBg {
        display: none;
      }

      .sm-screen {
        bottom: 12px;
        display: block;
        position: absolute;
        right: 12px;
      }
    }

    ${(props) =>
      props.formState === 'signUp' &&
      css`
        left: 50%;
      `}
  }
`;
