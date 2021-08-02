/* eslint-disable func-names */
import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
axios.defaults.baseURL = BASE_URL;
axios.defaults.timeout = 5000;

const browser = axios.create({
  withCredentials: false,
});

const server = axios.create({
  withCredentials: true,
});

server.interceptors.request.use(
  function (config) {
    const conf = config;
    // Do something before request is sent
    const token = window.localStorage.getItem('token');
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

export { server, browser };
