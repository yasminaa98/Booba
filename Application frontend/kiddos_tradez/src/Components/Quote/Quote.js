import { Box } from "@mui/system";
import React from "react";
import "./Quote.css";
const Quote = () => {
  return (
    <Box
      className="bg"
      style={{
        height: "70px",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <h3 style={{ color: "white" }}>
        KIDDOS TRADEZZ , where imagination has no limits
      </h3>
    </Box>
  );
};

export default Quote;
