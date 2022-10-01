import React from "react";
import { isMobile } from "react-device-detect";
import { Button, Card, Col, Form, Input, Row } from "antd";
import { SearchOutlined } from "@ant-design/icons";
import Notify from "../../components/Notify";
import { useLiveScoreContext } from "./context";
import { AxiosError } from "axios";

export default function LiveScoreFilter() {
  const { state, dispatch, getLiveScoreList } = useLiveScoreContext();

  return (
    <Card size="small">
      <Form
        layout="horizontal"
        autoComplete="off"
        className="filterForm"
        initialValues={state.filter!}
        onFinish={(data: any) => {
          dispatch({ type: "SET_FILTER", payload: data });
          getLiveScoreList({
            ...data,
            pageNumber: state.pagination.current || 1,
            pageSize: state.pagination.pageSize || 10,
          }).catch((error: AxiosError) =>
            Notify({ type: "error", message: error.response?.data.message })
          );
        }}
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
            <Button type="primary" htmlType="submit" icon={<SearchOutlined />}>
              Search
            </Button>
          </Col>
        </Row>
      </Form>
    </Card>
  );
}
