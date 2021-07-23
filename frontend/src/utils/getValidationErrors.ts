import { ValidationError } from 'yup';

interface Errors {
  [key: string]: string;
}

export default function getValidationErrors(e: ValidationError): Errors {
  const validation: Errors = {};
  e.inner.forEach((error) => {
    if (error.path) {
      validation[error.path] = error.message;
    }
  });
  return validation;
}
