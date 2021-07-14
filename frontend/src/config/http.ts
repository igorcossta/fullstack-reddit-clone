import axios from "axios";

export const subreddit = axios.create({
  baseURL: 'http://localhost:3002/subreddit',
});

export const post = axios.create({
  baseURL: 'http://localhost:3001/post',
});

export const user = axios.create({
  baseURL: 'http://localhost:3002/user',
});