/* eslint-disable func-names */
import { axios } from './axios.config';

const RedditAPI = axios.create({
  withCredentials: true,
});

RedditAPI.interceptors.request.use(
  function (config) {
    const conf = config;
    console.info('RedditAPI ', 'before request sent');
    return conf;
  },
  function (error) {
    return Promise.reject(error);
  },
);

RedditAPI.interceptors.response.use(
  function (response) {
    console.info('RedditAPI ', 'successful request');
    return response;
  },
  function (error) {
    console.info('RedditAPI ', 'error request');
    return Promise.reject(error);
  },
);

export { RedditAPI };
