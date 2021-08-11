import { AxiosError, AxiosResponse } from 'axios';

import { Toastr } from '../component';
import { storage } from '../service/storage.manager';

function handleError(error: AxiosError): void {
  if (error.response) {
    // The request was made and the server responded with a status code
    // that falls out of the range of 2xx
    const {
      data: { message },
    } = error.response;

    Toastr.danger(`${message}`);
  } else if (error.request) {
    // The request was made but no response was received
    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
    // http.ClientRequest in node.js
    Toastr.danger('Something went wrong. Contact an administrator');
  }

  // Something happened in setting up the request that triggered an Error
  else Toastr.danger('Sorry, try again!');
}

function handleSuccess(res: AxiosResponse, type: string): void {
  if (type === 'confirm') {
    Toastr.success('token confirmed');
    return;
  }

  if (type === 'signup') {
    Toastr.info('check your email to activate your account');
    return;
  }

  if (type === 'signin') {
    const {
      headers: { authorization },
      data,
    } = res;

    storage.saveUser(data);
    storage.saveToken(authorization);
  }
}

export { handleError, handleSuccess };
