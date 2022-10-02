import LoginDTO from "../dtos/LoginDTO";
import React from "react";
import UserDTO from "../dtos/UserDTO";
import Backend from "../config/Backend";
import { LoginResponseDTO } from "../dtos/LoginResponseDTO";
import Storage from "../config/Storage";
import Constant from "../config/Constant";
import Notify from "../components/Notify";
import { AxiosError } from "axios";

interface IAuthContext {
  loading: boolean;
  loggedInUser: UserDTO | null;
  errorMessage: string | null;
  login: (data: LoginDTO) => Promise<void>;
  registration: (data: UserDTO) => Promise<UserDTO | null>;
  logout: () => Promise<void>;
}

const AuthContext = React.createContext<IAuthContext>({
  loading: false,
  loggedInUser: null,
  errorMessage: null,
  login: () => Promise.resolve(),
  registration: () => Promise.resolve(null),
  logout: () => Promise.resolve(),
});
export const useAuthContext = () => React.useContext(AuthContext);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  const [loading, setLoading] = React.useState<boolean>(false);
  const [errorMessage] = React.useState<string | null>(null);
  const [loggedInUser, setLoggedInUser] = React.useState<UserDTO | null>(
    Storage.getData(Constant.USER_KEY)
  );

  const login = (data: LoginDTO) => {
    return new Promise<void>((resolve) => {
      setLoading(true);
      setLoggedInUser(null);
      Backend.Auth.login(data)
        .then((response) => {
          const result: LoginResponseDTO = response.data;
          Storage.setData(Constant.TOKEN_KEY, result.token);
          Storage.setData(Constant.USER_KEY, result.user);
          setLoggedInUser(result.user);
          resolve();
        })
        .catch((error: AxiosError) => {
          Notify({
            type: "error",
            message: error.response?.data.message
              ? error.response?.data.errors[0]
              : "Invalid credentials",
          });
        })
        .finally(() => setLoading(false));
    });
  };

  const registration = (data: UserDTO) => {
    return new Promise<UserDTO>((resolve) => {
      setLoading(true);
      Backend.Auth.registration(data)
        .then((response) => {
          resolve(response.data);
        })
        .catch((error: AxiosError) => {
          Notify({
            type: "error",
            message: error.response?.data.message,
            description: error.response?.data.errors.join(","),
          });
        })
        .finally(() => setLoading(false));
    });
  };

  const logout = () => {
    return new Promise<void>((resolve) => {
      Storage.deleteData(Constant.TOKEN_KEY);
      Storage.deleteData(Constant.USER_KEY);
      setLoggedInUser(null);
      resolve();
    });
  };

  return (
    <AuthContext.Provider
      value={{
        loading,
        errorMessage,
        loggedInUser,
        login,
        registration,
        logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};
