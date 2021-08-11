/* eslint-disable func-names */
import { axios } from './axios.config';
import { storage } from './storage.manager';

const AuthAPI = axios.create({
  withCredentials: true,
});

AuthAPI.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    const conf = config;
    // verificar se caminho precisa de autorizacao, caso precise enviar o bearer token
    const token = storage.getToken();
    if (token) {
      conf.headers.Authorization = `Bearer ${token}`;
    }

    return conf;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  },
);

export { AuthAPI };
