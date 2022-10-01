type ConstantType = {
  TOKEN_KEY: string;
  USER_KEY: string;
  INVALID_SESSION_KEY: string;
  REDIRECT_URL_KEY: string;
  HOME_URL: string;
  AUTH_URL: string;
  NOTIFICATION_DURATION: number;
  DATA_FETCH_INTERVAL: number;
};
const Constant: ConstantType = {
  TOKEN_KEY: "freeway-token",
  USER_KEY: "freeway-user",
  INVALID_SESSION_KEY: "freeway-invalid_session",
  REDIRECT_URL_KEY: "freeway-redirect-url",
  HOME_URL: "/",
  AUTH_URL: "/login",
  NOTIFICATION_DURATION: 5,
  DATA_FETCH_INTERVAL: 1000 * 60 * 5,
};
export default Constant;
