import React from "react";
import { isMobile } from "react-device-detect";
import { Button, Card, Col, Form, Input, Row, Space } from "antd";
import { SearchOutlined, ReloadOutlined } from "@ant-design/icons";
import Notify from "../../components/Notify";
import { useLiveScoreContext } from "./context";
import { AxiosError } from "axios";

export default function LiveScoreFilter() {
  const { state, dispatch, getLiveScoreList } = useLiveScoreContext();

  const fetchData = (data: any) => {
    dispatch({ type: "SET_FILTER", payload: data });
    getLiveScoreList({
      ...data,
      pageNumber: 1,
      pageSize: state.pagination.pageSize || 10,
    }).catch((error: AxiosError) =>
      Notify({ type: "error", message: error.response?.data.message })
    );
  };

  return (
    <Card size="small">
      <Form
        layout="horizontal"
        autoComplete="off"
        className="filterForm"
        initialValues={state.filter!}
        onFinish={(data: any) => fetchData(data)}
      >
        <Row gutter={[24, 0]}>
          <Col xs={24} sm={12}>
            <Form.Item name="title" label={isMobile ? "" : "Search"}>
              <Input
                autoFocus
                placeholder={isMobile ? "Search" : ""}
                allowClear
              />
            </Form.Item>
          </Col>
          <Col xs={24} sm={12}>
            <Space size={22}>
              <Button
                type="primary"
                htmlType="submit"
                icon={<SearchOutlined />}
              >
                Search
              </Button>
              <Button
                onClick={() => fetchData({})}
                type="default"
                htmlType="reset"
                icon={<ReloadOutlined />}
              >
                Reload
              </Button>
            </Space>
          </Col>
        </Row>
      </Form>
    </Card>
  );
}
