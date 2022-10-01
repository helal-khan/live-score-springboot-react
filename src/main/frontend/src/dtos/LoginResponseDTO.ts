import UserDTO from "./UserDTO";

export interface LoginResponseDTO {
  token: string;
  expiresAt: string;
  user: UserDTO;
}
