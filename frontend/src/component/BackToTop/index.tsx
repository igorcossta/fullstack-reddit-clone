import React, { useCallback, useState } from 'react';
import { FaArrowCircleUp } from 'react-icons/fa';

const BackToTop: React.FC = () => {
  const [showScroll, setShowScroll] = useState(false);

  const scrollTop = useCallback(() => {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }, []);

  const checkScrollTop = useCallback(() => {
    if (!showScroll && window.pageYOffset > 201) {
      setShowScroll(true);
    } else if (showScroll && window.pageYOffset < 200) {
      setShowScroll(false);
    }
  }, [showScroll]);

  window.addEventListener('scroll', checkScrollTop);

  return (
    <>
      {showScroll && (
        <FaArrowCircleUp size={38} fill="var(--secondary-color)" className="scrollTop" onClick={() => scrollTop()} />
      )}
    </>
  );
};

export default BackToTop;
