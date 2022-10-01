import React from "react";
import { Button, TablePaginationConfig } from "antd";

const paginationConfig: TablePaginationConfig = {
  pageSize: 10,
  current: 1,
  size: "small",
  simple: false,
  showSizeChanger: true,
  pageSizeOptions: ["10", "20", "50", "100"],
  showTotal: (total: number, range: number[]) =>
    `${range[0]}-${range[1]} of ${total} items`,
  itemRender: (
    page: number,
    type: string,
    originalElement: React.ReactNode
  ) => {
    if (type === "prev") {
      return <Button size="small">Previous</Button>;
    }
    if (type === "next") {
      return <Button size="small">Next</Button>;
    }
    return originalElement;
  },
};
export default paginationConfig;
