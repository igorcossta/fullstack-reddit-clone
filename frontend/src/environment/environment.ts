import { config } from './config.template';

/**
 * Environment variables for development mode.
 */
export const environment: any = {
  ...config,
  backendUrl: 'http://localhost:8080',
  frontendUrl: 'http://localhost:3000',
  production: false,
  requestTimeout: 5000,
  withCredentials: true,
};
