import * as React from "react";
import Checkbox from "@mui/material/Checkbox";
import Crousell from "./Components/Crousell/Crousell";

export default function Test() {
  return (
    <div
      style={{
        overflow: "auto",
        display: "flex",
        height: "260px",
        marginTop: "300px",
        backgroundColor: "rgba(102, 6, 166, 0.653)",
        boxShadow: "0 8px 32px rgba(31,28,135,0.37) ",
        borderRadius: "10px",
      }}
    >
      <Crousell />
    </div>
  );
}
