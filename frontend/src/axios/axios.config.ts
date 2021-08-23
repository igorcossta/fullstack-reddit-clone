/* eslint-disable func-names */
import axios from 'axios';

import { storage } from '../service/storage.manager';

const BASE_URL = 'http://localhost:8080';
axios.defaults.baseURL = BASE_URL;
axios.defaults.timeout = 5000;

axios.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    const conf = config;

    const { method } = conf;
    if (method === 'post' || method === 'put' || method === 'delete') {
      const token = storage.getToken();
      if (token) conf.headers.Authorization = `Bearer ${token}`;
    }

    return conf;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  },
);

export { axios };
