import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Dropdownart.css";

function Dropdownart() {
  const serviceDropdownart = [
    {
      id: 1,
      title: " Drawing Sets",
      path: "./drawingsets",
      cName: "submenu-item",
    },
    {
      id: 2,
      title: "Painting Kits",
      path: "./paintingkits",
      cName: "submenu-item",
    },
    {
      id: 3,
      title: "Bead Jewelry Kits",
      path: "./beadjewelrykits",
      cName: "submenu-item",
    },
    {
      id: 4,
      title: "Clay and Dough Sets",
      path: "./clayanddoughsets",
      cName: "submenu-item",
    },
    {
      id: 5,
      title: "Sewing Machines",
      path: "./sewingmachines",
      cName: "submenu-item",
    },
    
  ];


  const [dropdown, setDropdown] = useState(false);
  const List = serviceDropdownart.map((item) => {
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
        className={dropdown ? "services-submenuart clicked" : "services-submenuart"}
        onClick={() => setDropdown(!dropdown)}
      >
        {List}
      </ul>
    </>
  );
}

export default Dropdownart;
