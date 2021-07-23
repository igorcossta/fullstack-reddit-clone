import { createGlobalStyle } from 'styled-components';

export default createGlobalStyle`
  *,
  *::before,
  *::after {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  body {
    background-color: hsl(210, 19%, 88%);
    font-family: Verdana, Geneva, Tahoma, sans-serif;
  }

  :root {

  }
`;
