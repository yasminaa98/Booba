import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Dropdownimm.css";

function Dropdownimm() {
  const serviceDropdownimm = [
    {
      id: 1,
      title: " Dollhouses",
      path: "./dollhouses",
      cName: "submenu-item",
    },
    {
      id: 2,
      title: " Play Kitchen Sets",
      path: "./playkitchensets",
      cName: "submenu-item",
    },
    {
      id: 3,
      title: "Workbenches",
      path: "./workbenches",
      cName: "submenu-item",
    },
    {
      id: 4,
      title: "Doctor Kits",
      path: "./doctorkits",
      cName: "submenu-item",
    },
    {
      id: 5,
      title: "Play Tools",
      path: "./playtools",
      cName: "submenu-item",
    },
    {
      id: 6,
      title: "Teddy bears",
      path: "./teddybears",
      cName: "submenu-item",
    },
    {
      id: 7,
      title: "Other furry creatures",
      path: "./Otherfurrycreatures",
      cName: "submenu-item",
    },
  
  ];
 
  

  const [dropdown, setDropdown] = useState(false);
  const List = serviceDropdownimm.map((item) => {
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
        className={dropdown ? "services-submenuimm clicked" : "services-submenuimm"}
        onClick={() => setDropdown(!dropdown)}
      >
        {List}
      </ul>
    </>
  );
}

export default Dropdownimm;
