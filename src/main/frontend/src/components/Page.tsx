import React from "react";

export default function Page({
  children,
  title,
}: {
  children: React.ReactNode;
  title: string;
}) {
  document.title = "Live Score: " + title;
  return <div id={title}>{children}</div>;
}
