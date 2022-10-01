import axios from "axios";
import LoginDTO from "../dtos/LoginDTO";
import UserDTO from "../dtos/UserDTO";
import LiveScoreRequestDTO from "../dtos/LiveScoreRequestDTO";

const Backend = {
  Auth: {
    login: (data: LoginDTO) =>
      axios.post(process.env.REACT_APP_LOGIN_API_PATH_POST!, data),
    registration: (data: UserDTO) =>
      axios.post(process.env.REACT_APP_REGISTRATION_API_PATH_POST!, data),
  },

  LiveScore: {
    list: (data: LiveScoreRequestDTO) =>
      axios.post(process.env.REACT_APP_LIVE_SCORE_LIST_API_PATH_POST!, {
        ...data,
        pageNumber: data.pageNumber - 1,
      }),
  },
};
export default Backend;
