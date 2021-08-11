import React, { InputHTMLAttributes, useEffect, useRef, useState, useCallback } from 'react';
import { FiAlertCircle } from 'react-icons/fi';
import ReactTooltip from 'react-tooltip';

import { useField } from '@unform/core';

import { Container } from './styles';

interface Props extends InputHTMLAttributes<HTMLInputElement> {
  name: string;
}

const Input: React.FC<Props> = ({ name, ...rest }) => {
  const [isFill, setFill] = useState(false);
  const [isFocus, setFocus] = useState(false);
  const { fieldName, error, registerField } = useField(name);
  const inputRef = useRef<HTMLInputElement>(null);

  const handleFocus = useCallback(() => {
    setFocus(true);
  }, []);

  const handleBlur = useCallback(() => {
    setFocus(false);
    setFill(!!inputRef.current?.value);
  }, []);

  useEffect(() => {
    registerField({
      name: fieldName,
      ref: inputRef.current,
      path: 'value',
    });
  }, [fieldName, registerField]);

  return (
    <Container isFocus={isFocus} isFill={isFill} isError={!!error}>
      <input onFocus={handleFocus} onBlur={handleBlur} ref={inputRef} {...rest} placeholder={rest.placeholder} />
      {error && (
        <>
          <FiAlertCircle data-tip={error} size={16} />
          <ReactTooltip multiline />
        </>
      )}
    </Container>
  );
};

export default Input;
