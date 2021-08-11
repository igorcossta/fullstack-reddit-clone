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
  ConfirmToken(token: string): void;
  SignIn(payload: SigninPayload): Promise<void>;
  SignOut(): void;
  SignUp(payload: SignupPayload): Promise<void>;
  user: UserProps | null;
}

export type { SigninPayload, SignupPayload, UserProps, ContextProps };
