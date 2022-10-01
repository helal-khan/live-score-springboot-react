import { TablePaginationConfig } from "antd";
import LiveScoreResponseDTO from "../../dtos/LiveScoreResponseDTO";
import LiveScoreRequestDTO from "../../dtos/LiveScoreRequestDTO";
import paginationConfig from "../../config/paginationConfig";

export type LiveScoreState = {
  liveScore: LiveScoreResponseDTO[];
  filter: LiveScoreRequestDTO | null;
  pagination: TablePaginationConfig;
  loading: boolean;
};

export const liveScoreInitialState: LiveScoreState = {
  liveScore: [],
  filter: { pageNumber: 1, pageSize: 10 },
  pagination: paginationConfig,
  loading: false,
};

export type LiveScoreAction =
  | { type: "SET_LIVE_SCORES"; payload: LiveScoreResponseDTO[] }
  | { type: "SET_FILTER"; payload: LiveScoreRequestDTO }
  | { type: "SET_PAGINATION"; payload: TablePaginationConfig }
  | { type: "SET_LOADING"; payload: boolean };

export const liveScoreReducer = (
  state: LiveScoreState,
  action: LiveScoreAction
) => {
  switch (action.type) {
    case "SET_LIVE_SCORES":
      return { ...state, liveScore: action.payload };
    case "SET_FILTER":
      return { ...state, filter: action.payload };
    case "SET_PAGINATION":
      return { ...state, pagination: action.payload };
    case "SET_LOADING":
      return { ...state, loading: action.payload };
    default:
      return state;
  }
};
