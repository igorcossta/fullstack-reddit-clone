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
    background-color: whitesmoke;
    color: var(--black);
    font-family: 'Noto Sans', sans-serif;

    @media only screen and (max-width: 768px) {
      h1 {
        font-size: 20px;
      }
      h2 {
        font-size: 18px;
      }
      h3 {
        font-size: 16px;
      }
      h4 {
        font-size: 14px;
      }
      h5 {
        font-size: 12px;
      }
      h6 {
        font-size: 10px;
      }
    }
  }

  :root {
    --secondary-color: hsl(205.6, 100%, 41.4%);
    --background: hsl(210, 19%, 88%);
    
    --black: hsl(0, 0%, 0%);
    --blue: hsl(211.1, 100%, 50%);
    --cyan: hsl(188.2, 77.8%, 40.6%);
    --dark: hsl(210, 10.3%, 22.7%);
    --gray-dark: hsl(210, 10.3%, 22.7%);
    --gray: hsl(208.2, 7.3%, 45.7%);
    --green: hsl(133.7, 61.4%, 40.6%);
    --indigo: hsl(262.8, 89.7%, 50.6%);
    --light: hsl(210, 16.7%, 97.6%);
    --orange-dark: hsl(16.2, 100%, 50%);
    --orange: hsl(27.3, 98.3%, 53.5%);
    --pink: hsl(332.5, 78.7%, 57.6%);
    --purple: hsl(261.3, 50.6%, 50.8%);
    --red: hsl(354.3, 70.5%, 53.5%);
    --teal: hsl(162.2, 72.5%, 45.7%);
    --white: hsl(0, 0%, 100%);
    --yellow: hsl(45, 100%, 51.4%);
    
    --danger: var(--red);
    --info: var(--cyan);
    --primary: var(--blue);
    --secondary: var(--gray);
    --success: var(--green);
    --warning: var(--yellow);

    --breakpoint-xs: 0;
    --breakpoint-sm: 576px;
    --breakpoint-md: 768px;
    --breakpoint-lg: 992px;
    --breakpoint-xl: 1200px;

    --box-shadow: 0px 6px 5px 1px rgba(0, 0, 0, 0.2);
  }

  .scrollTop {
    bottom: 30px;
    position: fixed;
    right: 30px;
    z-index: 9998;
    &:hover {
      cursor: pointer;
    }
  }
`;
