import { useState } from 'react';

export const useToggle = (initial: boolean): [boolean, () => void] => {
  const [state, setState] = useState(initial);

  return [state, () => setState(!state)];
};
