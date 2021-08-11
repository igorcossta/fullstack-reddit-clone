import axios from 'axios';

const BASE_URL = 'http://localhost:8080';
axios.defaults.baseURL = BASE_URL;
axios.defaults.timeout = 5000;

export { axios };
