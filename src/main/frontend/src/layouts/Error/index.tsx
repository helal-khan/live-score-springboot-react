import React, { Suspense } from "react";
import { BackTop, Spin } from "antd";
import AppRouteDTO from "../../dtos/AppRouteDTO";
import RenderRoutes from "../../components/RenderRoutes";

const ErrorLayout = ({ route }: { route: AppRouteDTO }) => {
  return (
    <div style={{ minHeight: "100vh" }}>
      <BackTop />
      <Suspense fallback={<Spin />}>
        <RenderRoutes routes={route.routes} />
      </Suspense>
    </div>
  );
};
export default ErrorLayout;
