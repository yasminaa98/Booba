import * as React from "react";
import Singleproduct from "../Singleproduct/Singleproduct";
import "./Content.css";
const Content = ({ list }) => (
  <div className="productContainer">
    {list.map((item) => {
      return <Singleproduct item={item} key={item.id} />;
    })}
  </div>
);

export default Content;
