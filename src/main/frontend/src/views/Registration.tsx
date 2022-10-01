import React from "react";
import { Link, useHistory } from "react-router-dom";
import { Button, Card, Col, Form, Input, Layout, Row, Typography } from "antd";
import "../assets/styles/pages/login.css";
import { useAuthContext } from "../contexts/AuthContext";
import Constant from "../config/Constant";
import Storage from "../config/Storage";
import Page from "../components/Page";
import Notify from "../components/Notify";
import UserDTO from "../dtos/UserDTO";

const Registration = () => {
  const history = useHistory();
  const { loggedInUser, registration } = useAuthContext();

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

  const onFinish = (data: UserDTO) => {
    registration(data).then((response: UserDTO | null) => {
      Notify({
        type: "error",
        message: response?.fullName + ": Registration Successful",
      });
      Storage.deleteData(Constant.REDIRECT_URL_KEY);
      history.replace(Constant.AUTH_URL);
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
                <Form layout="vertical" onFinish={onFinish} autoComplete="off">
                  <Form.Item
                    hasFeedback
                    name="fullName"
                    label="FullName"
                    rules={[{ required: true, message: "FullName Required" }]}
                  >
                    <Input allowClear placeholder="Full Name" />
                  </Form.Item>

                  <Form.Item
                    hasFeedback
                    name="username"
                    label="Username"
                    rules={[{ required: true, message: "Username Required" }]}
                  >
                    <Input allowClear placeholder="Username" />
                  </Form.Item>

                  <Form.Item
                    hasFeedback
                    name="password"
                    label="Password"
                    rules={[
                      { required: true, message: "Password Required" },
                      {
                        min: 6,
                        message: "Password must be minimum 6 characters.",
                      },
                    ]}
                  >
                    <Input.Password allowClear placeholder="Password" />
                  </Form.Item>

                  <Form.Item
                    hasFeedback
                    name="confirmPassword"
                    label="Confirm password"
                    rules={[
                      {
                        required: true,
                        message: "Confirm password Required",
                      },
                      ({ getFieldValue }) => ({
                        validator(_, value) {
                          if (!value || getFieldValue("password") === value) {
                            return Promise.resolve();
                          }
                          return Promise.reject("Confirm password mismatch");
                        },
                      }),
                    ]}
                  >
                    <Input.Password allowClear placeholder="Confirm Password" />
                  </Form.Item>

                  <Form.Item
                    hasFeedback
                    name="email"
                    label="Email"
                    rules={[
                      {
                        type: "email",
                        message: "Please enter a valid email!",
                      },
                      { required: true, message: "Email Required" },
                    ]}
                  >
                    <Input allowClear placeholder="Email" />
                  </Form.Item>

                  <Row gutter={[24, 0]}>
                    <Col span={12}>
                      <Form.Item label=" ">
                        <Button htmlType="reset" block>
                          Reset
                        </Button>
                      </Form.Item>
                    </Col>
                    <Col span={12}>
                      <Form.Item label=" ">
                        <Button block type="primary" htmlType="submit">
                          Sign Up
                        </Button>
                      </Form.Item>
                    </Col>
                  </Row>

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
