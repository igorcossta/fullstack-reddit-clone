export interface ContextProps {
  addToast(arg: Omit<ToastProps, 'id'>): void;
  removeToast(id: string): void;
}

export interface ToastProps {
  id: string;
  type?: 'success' | 'info' | 'error';
  description?: string;
  title: string;
}
