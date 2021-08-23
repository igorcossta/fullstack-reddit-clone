/* eslint-disable func-names */
import axios from 'axios';

import { environment } from '../environment/environment';
import { storage } from '../service/storage.manager';

axios.defaults.baseURL = environment.backendUrl;
axios.defaults.timeout = environment.requestTimeout;
axios.defaults.withCredentials = environment.withCredentials;

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
