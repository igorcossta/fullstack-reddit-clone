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
    background-color: var(--background);
    font-family: 'Noto Sans', sans-serif;
  }

  :root {
    --primary-color: hsl(16.2, 100%, 50%);
    --secondary-color: hsl(205.6, 100%, 41.4%);
    --background: hsl(210, 19%, 88%);
  }

  .scrollTop {
    position: fixed;
    z-index: 9998;
    bottom: 30px;
    right: 30px;
    &:hover {
      cursor: pointer;
    }
  }
`;
