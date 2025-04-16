import { User } from "./Usuario.interface";

export interface AuthResponse {
  token: string;
  user:  User;
  roles: string[];
}


