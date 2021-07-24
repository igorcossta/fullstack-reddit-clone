export interface ContextProps {
  signIn(data: SignInPayload): Promise<void>;
  signOut(): void;
  principal: Principal | null;
  isAuthenticated: boolean;
  isLoaded: boolean;
}

export interface Principal {
  firstName: string;
  lastName: string;
  email: string;
}

export interface SignInPayload {
  username: string;
  password: string;
}

export type Token = string;
