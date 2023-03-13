import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Dropdown.css";

function Dropdown() {
  const serviceDropdown = [
    {
      id: 1,
      title: "Blocks",
      path: "./blocks",
      cName: "submenu-item",
    },
    {
      id: 2,
      title: " Magnetic Tiles",
      path: "./magnetictiles",
      cName: "submenu-item",
    },
    {
      id: 3,
      title: " Marble Runs",
      path: "./marbleruns",
      cName: "submenu-item",
    },
    {
      id: 4,
      title: "Construction Sets",
      path: "./constructionsets",
      cName: "submenu-item",
    },
    {
      id: 5,
      title: "Gear Sets",
      path: "./gearsets",
      cName: "submenu-item",
    },
    {
      id: 5,
      title: " Connectors",
      path: "./connectors",
      cName: "submenu-item",
    },
  ];

  const [dropdown, setDropdown] = useState(false);
  const List = serviceDropdown.map((item) => {
    return (
      <li key={item.id}>
        <Link
          to={item.path}
          className={item.cName}
          onClick={() => setDropdown(false)}
        >
          {item.title}
        </Link>
      </li>
    );
  });

  return (
    <>
      <ul
        className={dropdown ? "services-submenu clicked" : "services-submenu"}
        onClick={() => setDropdown(!dropdown)}
      >
        {List}
      </ul>
    </>
  );
}

export default Dropdown;
