import React, { createContext, useReducer } from "react";
import {
  LiveScoreAction,
  liveScoreInitialState,
  liveScoreReducer,
  LiveScoreState,
} from "./reducer";
import LiveScoreRequestDTO from "../../dtos/LiveScoreRequestDTO";
import Backend from "../../config/Backend";
import { AxiosError, AxiosResponse } from "axios";

const LiveScoreContext = createContext<{
  state: LiveScoreState;
  dispatch: React.Dispatch<LiveScoreAction>;
  getLiveScoreList: (filter: LiveScoreRequestDTO) => Promise<void>;
}>({
  state: liveScoreInitialState,
  dispatch: () => null,
  getLiveScoreList: () => Promise.resolve(),
});
export const useLiveScoreContext = () => React.useContext(LiveScoreContext);

export const LiveScoreProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [state, dispatch] = useReducer(liveScoreReducer, liveScoreInitialState);

  const getLiveScoreList = (data: LiveScoreRequestDTO) => {
    return new Promise<void>((resolve, reject) => {
      dispatch({ type: "SET_LOADING", payload: true });
      Backend.LiveScore.list(data)
        .then((response: AxiosResponse) => {
          dispatch({ type: "SET_LIVE_SCORES", payload: response.data.content });
          dispatch({
            type: "SET_PAGINATION",
            payload: {
              ...state.pagination,
              current: data.pageNumber,
              pageSize: data.pageSize,
              total: response.data.totalElements,
            },
          });
          resolve();
        })
        .catch((error: AxiosError) => reject(error))
        .finally(() => dispatch({ type: "SET_LOADING", payload: false }));
    });
  };

  return (
    <LiveScoreContext.Provider
      value={{
        state,
        dispatch,
        getLiveScoreList,
      }}
    >
      {children}
    </LiveScoreContext.Provider>
  );
};
