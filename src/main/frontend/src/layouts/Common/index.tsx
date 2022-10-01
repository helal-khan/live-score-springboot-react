import React, { Suspense } from "react";
import { Link, useLocation } from "react-router-dom";
import {
  Avatar,
  BackTop,
  Button,
  Drawer,
  Dropdown,
  Layout,
  Menu,
  Spin,
} from "antd";
import {
  UserOutlined,
  LogoutOutlined,
  MenuUnfoldOutlined,
  GlobalOutlined,
  FileOutlined,
} from "@ant-design/icons";
import RenderRoutes from "../../components/RenderRoutes";
import { useAuthContext } from "../../contexts/AuthContext";
import { isMobile } from "react-device-detect";
import AppRouteDTO from "../../dtos/AppRouteDTO";
import LOGO from "../../assets/images/logo.png";

const CommonLayout = ({ route }: { route: AppRouteDTO }) => {
  const { logout, loggedInUser } = useAuthContext();
  const location = useLocation();
  const [collapsed] = React.useState<boolean>(false);
  const [userMenuVisible, setUserMenuVisible] = React.useState<boolean>(false);
  const [menuVisible, setMenuVisible] = React.useState<boolean>(false);

  const handleUserMenu = (flag: boolean) => {
    setUserMenuVisible(flag);
  };
  const closeMenu = () => {
    setMenuVisible(false);
  };

  const userMenu = (
    <Menu style={{ marginTop: isMobile ? 13 : 12 }}>
      <Menu.Item key="menu3" onClick={logout}>
        <LogoutOutlined style={{ marginRight: "5px" }} /> Sign Out
      </Menu.Item>
    </Menu>
  );

  const mainMenu = (
    <Menu
      selectedKeys={[location.pathname]}
      theme="light"
      className="menu"
      mode="inline"
    >
      <Menu.Item icon={<GlobalOutlined />} key={"/"} onClick={closeMenu}>
        <Link to="/">Live Score</Link>
      </Menu.Item>

      <Menu.Item
        icon={<FileOutlined />}
        key={"/blank-page"}
        onClick={closeMenu}
      >
        <Link to="/blank-page">Blank Page</Link>
      </Menu.Item>
    </Menu>
  );

  return (
    <React.Fragment>
      <Layout style={{ minHeight: "100vh" }}>
        <Layout.Header className="header">
          {!isMobile && (
            <Link to="/" key="k1" className="company-logo">
              <img src={LOGO} alt="logo" className="image_full" />
            </Link>
          )}
          <nav className="navbar" key="k2">
            <Button
              className="menu-btn"
              type="text"
              icon={
                <MenuUnfoldOutlined
                  style={{ fontSize: "20px", fontWeight: "bold" }}
                />
              }
              onClick={() => setMenuVisible(true)}
            />
            <Drawer
              title={<img src={LOGO} alt="logo" />}
              placement="left"
              closable={false}
              onClose={() => setMenuVisible(false)}
              visible={menuVisible}
            >
              {mainMenu}
            </Drawer>
          </nav>
          <nav
            className="navbar"
            key="k3"
            style={{ padding: 4, marginTop: isMobile ? "4px" : "2px" }}
          >
            <Dropdown
              overlay={userMenu}
              onVisibleChange={handleUserMenu}
              visible={userMenuVisible}
              key="d2"
            >
              <a
                className="ant-dropdown-link"
                href="#"
                onClick={(e) => e.preventDefault()}
                key="a"
                style={{ padding: 4 }}
              >
                <Avatar
                  style={{
                    backgroundColor: "#3f51b5",
                  }}
                  icon={
                    <UserOutlined
                      style={{ fontSize: "22px", color: "#fff", padding: 2 }}
                    />
                  }
                />
                <span className="user-name" key="b">
                  {loggedInUser?.fullName}
                </span>
              </a>
            </Dropdown>{" "}
          </nav>
        </Layout.Header>

        <Layout>
          <Layout.Sider
            collapsed={collapsed}
            width="260"
            style={{
              overflow: "auto",
              height: "93.5vh",
              left: 0,
              top: 0,
              bottom: 0,
            }}
            breakpoint="lg"
          >
            {mainMenu}
          </Layout.Sider>

          <Layout.Content className="content">
            <BackTop />
            <Suspense fallback={<Spin />}>
              <RenderRoutes routes={route.routes} />
            </Suspense>
          </Layout.Content>
        </Layout>
      </Layout>
    </React.Fragment>
  );
};
export default CommonLayout;
