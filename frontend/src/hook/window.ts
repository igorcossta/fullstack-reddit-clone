import { useState } from 'react';

const useWindow = () => {
  const [isShowing, setIsShowing] = useState(false);

  function toggle() {
    setIsShowing(!isShowing);
  }

  return { isShowing, toggle };
};

export default useWindow;
