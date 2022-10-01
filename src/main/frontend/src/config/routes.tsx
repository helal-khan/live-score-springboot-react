import React, { lazy } from "react";
import { Redirect } from "react-router-dom";
import AppRouteDTO from "../dtos/AppRouteDTO";
import CommonLayout from "../layouts/Common";
import ErrorLayout from "../layouts/Error";
import Login from "../views/Login";
import Registration from "../views/Registration";

export const routes: AppRouteDTO[] = [
  {
    path: "/login",
    exact: true,
    component: Login,
  },
  {
    path: "/registration",
    exact: true,
    component: Registration,
  },
  {
    path: "/error",
    component: ErrorLayout,
    routes: [
      {
        path: "/error/401",
        exact: true,
        component: lazy(() => import("../views/Error/Error401")),
      },
      {
        path: "/error/403",
        exact: true,
        component: lazy(() => import("../views/Error/Error403")),
      },
      {
        path: "/error/404",
        exact: true,
        component: lazy(() => import("../views/Error/Error404")),
      },
      {
        path: "/error/500",
        exact: true,
        component: lazy(() => import("../views/Error/Error500")),
      },
      {
        component: () => <Redirect to="/error/404" />,
      },
    ],
  },
  {
    path: "*",
    component: CommonLayout,
    restricted: true,
    routes: [
      {
        path: "/",
        exact: true,
        component: lazy(() => import("../views/LiveScore")),
      },
      {
        path: "/blank-page",
        exact: true,
        component: lazy(() => import("../views/BlankPage")),
      },
      {
        component: () => <Redirect to="/error/404" />,
      },
    ],
  },
];
export default routes;
