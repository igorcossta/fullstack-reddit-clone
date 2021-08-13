import { axios } from './axios.config';

const AuthAPI = axios.create({
  withCredentials: true,
});

export { AuthAPI };
