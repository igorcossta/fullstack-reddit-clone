import React, { InputHTMLAttributes, useEffect, useRef, useState, useCallback } from 'react';
import { FiAlertCircle } from 'react-icons/fi';

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
      <input
        onFocus={handleFocus}
        onBlur={handleBlur}
        ref={inputRef}
        {...rest}
        placeholder={error || rest.placeholder}
      />
      {error && <FiAlertCircle size={16} />}
    </Container>
  );
};

export default Input;
