import React from "react";
import { PageHeader, Space, Table, Tooltip } from "antd";
import { ColumnsType, TablePaginationConfig } from "antd/lib/table/interface";
import { AxiosError } from "axios";
import { LiveScoreProvider, useLiveScoreContext } from "./context";
import Page from "../../components/Page";
import LiveScoreResponseDTO from "../../dtos/LiveScoreResponseDTO";
import LiveScoreFilter from "./filter";
import Notify from "../../components/Notify";
import Constant from "../../config/Constant";
import NewTabLink from "../../components/NewTabLink";

const LiveScoreList = () => {
  const { state, dispatch, getLiveScoreList } = useLiveScoreContext();

  const columns: ColumnsType<LiveScoreResponseDTO> = [
    {
      title: "Score",
      render: (record: LiveScoreResponseDTO) => record.title,
    },
    {
      title: "Source",
      align: "center",
      width: 100,
      render: (row: LiveScoreResponseDTO) => (
        <Space size={10}>
          <Tooltip title="Go to link" placement="top">
            <NewTabLink to={row.link!} label="LINK" />
          </Tooltip>
          <Tooltip title="Go to uri" placement="top">
            <NewTabLink to={row.uri!} label="URI" />
          </Tooltip>
        </Space>
      ),
    },
  ];

  React.useEffect(() => {
    setInterval(
      () =>
        fetchData(
          state.pagination.current || 1,
          state.pagination.pageSize || 10
        ),
      Constant.DATA_FETCH_INTERVAL
    );
    fetchData(state.pagination.current || 1, state.pagination.pageSize || 10);
    // eslint-disable-next-line
  }, []);

  const handleTableChange = (pagination: TablePaginationConfig) => {
    dispatch({ type: "SET_PAGINATION", payload: pagination });
    fetchData(pagination.current || 1, pagination.pageSize || 10);
  };

  const fetchData = (pageNumber: number, pageSize: number) => {
    getLiveScoreList({ pageNumber, pageSize }).catch((error: AxiosError) => {
      Notify({ type: "error", message: error.response?.data.message });
    });
  };

  return (
    <Page title="Live Score">
      <PageHeader title="Live Score" />
      <Space direction="vertical" size={16} style={{ width: "100%" }}>
        <LiveScoreFilter />
        <Table
          size="middle"
          scroll={{ x: "100vh" }}
          dataSource={state.liveScore}
          columns={columns}
          pagination={state.pagination}
          loading={state.loading}
          onChange={handleTableChange}
          rowKey={(record: LiveScoreResponseDTO) => record.id!}
        />
      </Space>
    </Page>
  );
};

const LiveScoreListContainer = () => {
  return (
    <LiveScoreProvider>
      <LiveScoreList />
    </LiveScoreProvider>
  );
};
export default LiveScoreListContainer;
