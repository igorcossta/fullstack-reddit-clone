// Authentication Context

export type Token = string;

export interface IUser {
  firstName: string;
  lastName: string;
  email: string;
}

export interface IAuthContext {
  signIn(data: UsernameAndPasswordRequest): Promise<boolean>;
  signOut(): void;
  principal: IUser | null;
  isAuthenticated: boolean;
  isLoaded: boolean;
}

// API
export interface UsernameAndPasswordRequest {
  username: string;
  password: string;
}

export interface PostResponse {
  id: number;
  subreddit: string;
  user: string;
  createdAt: string;
  content: string;
  comment: number;
  vote: number;
}
