import React from "react";
import { Redirect, Route, RouteComponentProps, Switch } from "react-router-dom";
import Storage from "../config/Storage";
import { useAuthContext } from "../contexts/AuthContext";
import Constant from "../config/Constant";
import AppRouteDTO from "../dtos/AppRouteDTO";

type RenderRoutesProps = {
  routes?: AppRouteDTO[];
  switchProps?: object;
  additionalComponentProps?: object;
};

const RenderRoutes = ({
  routes,
  switchProps = {},
  additionalComponentProps = {},
}: RenderRoutesProps) => {
  const { loggedInUser } = useAuthContext();
  return routes ? (
    <Switch {...switchProps}>
      {routes.map((route: AppRouteDTO, i: number) => (
        <Route
          key={route.key || i}
          path={route.path}
          exact={route.exact}
          strict={route.strict}
          render={(props: RouteComponentProps) => {
            if (
              !route.restricted ||
              loggedInUser ||
              route.path === Constant.AUTH_URL
            ) {
              // @ts-ignore
              return (
                <route.component
                  {...props}
                  {...additionalComponentProps}
                  route={route}
                />
              );
            }
            Storage.setData(Constant.REDIRECT_URL_KEY, props.location.pathname);
            return <Redirect to={Constant.AUTH_URL} />;
          }}
        />
      ))}
    </Switch>
  ) : null;
};
export default RenderRoutes;
