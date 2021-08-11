import { UserProps } from '../context/account.type';

function clearData(): void {
  window.localStorage.clear();
  window.sessionStorage.clear();
}

function getToken(): string | null {
  const token = window.localStorage.getItem('token');
  return token;
}

function getUser(): UserProps | null {
  const user = window.localStorage.getItem('user');
  if (user) {
    return JSON.parse(user);
  }
  return null;
}

function saveToken(token: string): void {
  window.localStorage.setItem('token', token);
}

function saveUser(user: string): void {
  window.localStorage.setItem('user', JSON.stringify(user));
}

const storage = {
  clearData,
  getToken,
  getUser,
  saveToken,
  saveUser,
};

export { storage };
