import React from "react";
import { Link, useHistory } from "react-router-dom";
import { Button, Card, Col, Form, Input, Layout, Row, Typography } from "antd";
import "../assets/styles/pages/login.css";
import { useAuthContext } from "../contexts/AuthContext";
import Constant from "../config/Constant";
import Storage from "../config/Storage";
import LoginDTO from "../dtos/LoginDTO";
import Page from "../components/Page";
import Notify from "../components/Notify";

const Registration = () => {
  const history = useHistory();
  const { loggedInUser, login } = useAuthContext();

  React.useEffect(() => {
    if (loggedInUser) {
      const redirectURL = Storage.getData(Constant.REDIRECT_URL_KEY);
      Storage.deleteData(Constant.REDIRECT_URL_KEY);
      history.replace(redirectURL || Constant.HOME_URL);
    } else {
      const invalidSession: boolean = Storage.getData(
        Constant.INVALID_SESSION_KEY
      );
      if (invalidSession) {
        Storage.deleteData(Constant.INVALID_SESSION_KEY);
        Notify({ type: "error", message: "Invalid session" });
      }
    }
    // eslint-disable-next-line
  }, []);

  const onFinish = (data: LoginDTO) => {
    login(data).then(() => {
      const redirectURL = Storage.getData(Constant.REDIRECT_URL_KEY);
      Storage.deleteData(Constant.REDIRECT_URL_KEY);
      history.replace(redirectURL || Constant.HOME_URL);
    });
  };

  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Layout.Content>
        <Page title="Login">
          <Row align="middle" style={{ minHeight: "100vh" }}>
            <Col
              xs={{ span: 24 }}
              sm={{ span: 12, offset: 6 }}
              lg={{ span: 10, offset: 7 }}
              xl={{ span: 8, offset: 8 }}
              xxl={{ span: 6, offset: 9 }}
            >
              <Card bordered className="auth-card">
                <span style={{ textAlign: "center", paddingBottom: "20px" }}>
                  <h1>Sign Up</h1>
                </span>
                <Form
                  initialValues={{ rememberMe: false }}
                  onFinish={onFinish}
                  autoComplete="off"
                >
                  <Form.Item
                    name="username"
                    rules={[{ required: true, message: "Username Required" }]}
                  >
                    <Input allowClear placeholder="Full Name" />
                  </Form.Item>

                  <Form.Item
                    name="username"
                    rules={[{ required: true, message: "Username Required" }]}
                  >
                    <Input allowClear placeholder="Username" />
                  </Form.Item>

                  <Form.Item
                    name="password"
                    rules={[{ required: true, message: "Password Required" }]}
                  >
                    <Input.Password allowClear placeholder="Password" />
                  </Form.Item>

                  <Form.Item
                    name="password"
                    rules={[{ required: true, message: "Password Required" }]}
                  >
                    <Input.Password allowClear placeholder="Confirm Password" />
                  </Form.Item>

                  <Form.Item
                    name="username"
                    rules={[{ required: true, message: "Username Required" }]}
                  >
                    <Input allowClear placeholder="Email" />
                  </Form.Item>

                  <Form.Item className="buttonContainer">
                    <Button type="primary" block htmlType="submit">
                      Sign Up
                    </Button>
                  </Form.Item>
                  <Typography
                    style={{ textAlign: "center", marginTop: "15px" }}
                  >
                    <Link to="/login">Back to Sign In</Link>
                  </Typography>
                </Form>
              </Card>
            </Col>
          </Row>
        </Page>
      </Layout.Content>
    </Layout>
  );
};
export default Registration;
