/* eslint-disable func-names */
import { storage } from '../service/storage.manager';
import { axios } from './axios.config';

const RedditAPI = axios.create({
  withCredentials: true,
});

RedditAPI.interceptors.request.use(
  function (config) {
    // Do something before request is sent
    const conf = config;

    const { url, method } = conf;
    if (url === '/api/subreddit' && method === 'post') {
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

export { RedditAPI };
