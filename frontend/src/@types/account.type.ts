interface SigninPayload {
  password: string;
  username: string;
}

interface SignupPayload {
  email: string;
  firstName: string;
  lastName: string;
  password: string;
}

interface UserProps {
  firstName: string;
  lastName: string;
  username: string; // email
}

interface ContextProps {
  loading: boolean;
  signed: boolean;
  confirmToken(token: string): void;
  signIn(payload: SigninPayload): Promise<void>;
  signOut(): void;
  signUp(payload: SignupPayload): Promise<void>;
  user: UserProps | null;
}

export type { SigninPayload, SignupPayload, UserProps, ContextProps };
