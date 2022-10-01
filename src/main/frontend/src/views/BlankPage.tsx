import React from "react";
import { useAuthContext } from "../contexts/AuthContext";
import Page from "../components/Page";

const BlankPage = () => {
  const { loggedInUser } = useAuthContext();
  return (
    <Page title="Home">
      <h1
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "80vh",
          fontSize: "300%",
          color: "#3290FF",
        }}
      >
        <b className="is-desktop">
          Hello, {loggedInUser?.fullName} - Welcome to the Live Score
        </b>
        <b className="is-mobile">
          Welcome, <br />
          {loggedInUser?.fullName}
        </b>
      </h1>
    </Page>
  );
};
export default BlankPage;
