import React from "react";
import Carousel from "react-elastic-carousel";
import { Paper, Button, Box } from "@mui/material";
import product_cart from "../../Components/data/product_data";
import { useEffect, useState } from "react";
import Content from "../Content/Content";
import Singleproduct from "../Singleproduct/Singleproduct";
const Crousell = () => {
  const [list, setList] = useState(product_cart);

  return (
    <Carousel sx={{ backgroundcolor: "red" }}>
      {list.map((item) => {
        return <Singleproduct item={item} key={item.id} />;
      })}
    </Carousel>
  );
};

export default Crousell;
