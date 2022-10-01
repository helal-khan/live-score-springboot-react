import React from "react";

export default function NewTabLink({
  to,
  label,
}: {
  to: string;
  label: string;
}) {
  return (
    <a href={to} target="_blank" rel="noopener noreferrer">
      {label}
    </a>
  );
}
