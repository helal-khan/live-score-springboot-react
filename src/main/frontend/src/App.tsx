import React from "react";
import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from "axios";
import { BrowserRouter, useLocation } from "react-router-dom";
import "antd/dist/antd.css";
import "./assets/styles/main.css";
import Constant from "./config/Constant";
import Storage from "./config/Storage";
import { AuthProvider } from "./contexts/AuthContext";
import RenderRoutes from "./components/RenderRoutes";
import routes from "./config/routes";
import Notify from "./components/Notify";

//axios config
(() => {
  axios.defaults.timeout = 60 * 1000;
  axios.defaults.headers.common = { Accept: "application/json" };

  axios.interceptors.request.use((requestConfig: AxiosRequestConfig) => {
    const token = Storage.getData(Constant.TOKEN_KEY);
    if (token) {
      requestConfig.headers = {
        ...requestConfig.headers,
        Authorization: "Bearer " + token,
      };
    }
    return requestConfig;
  });
  axios.interceptors.response.use(
    (response: AxiosResponse) => response,
    (error: AxiosError) => {
      if (
        error.response?.status === 401 &&
        !window.location.href.includes(Constant.AUTH_URL)
      ) {
        Storage.deleteData(Constant.TOKEN_KEY);
        Storage.deleteData(Constant.USER_KEY);
        Storage.setData(Constant.INVALID_SESSION_KEY, true);
        Storage.setData(Constant.REDIRECT_URL_KEY, window.location.pathname);
        window.location.href = Constant.AUTH_URL;
      }
      return Promise.reject(error);
    }
  );
})();
//online status
(() => {
  window.addEventListener("online", () =>
    Notify({ type: "success", message: "CONNECTION AVAILABLE NOW" })
  );
  window.addEventListener("offline", () =>
    Notify({ type: "error", message: "NO INTERNET CONNECTION" })
  );
})();

const ScrollToTop = () => {
  const location = useLocation();

  React.useEffect(() => {
    window.scrollTo(0, 0);
  }, [location.pathname]);

  return null;
};

const App = () => (
  <AuthProvider>
    <BrowserRouter>
      <ScrollToTop />
      <RenderRoutes routes={routes} />
    </BrowserRouter>
  </AuthProvider>
);
export default App;
