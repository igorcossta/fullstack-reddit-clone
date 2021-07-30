/* eslint-disable @typescript-eslint/explicit-module-boundary-types */
import { useState } from 'react';

const useModal = () => {
  const [isHidden, setState] = useState(false);

  function toggle() {
    setState(!isHidden);
  }

  return { isHidden, toggle };
};

export default useModal;
