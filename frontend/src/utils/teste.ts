import axios from 'axios';

const teste = axios.create({
  baseURL: 'http://localhost:8080',
});

export { teste };
