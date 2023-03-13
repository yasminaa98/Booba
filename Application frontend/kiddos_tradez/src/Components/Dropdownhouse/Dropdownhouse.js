import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Dropdownhouse.css";

function Dropdownhouse() {
  const serviceDropdownhouse = [
    {
      id: 1,
      title: "Trampolines",
      path: "./trampolines",
      cName: "submenu-item",
    },
    {
      id: 2,
      title: "Playhouses",
      path: "./Playhouses",
      cName: "submenu-item",
    },
    {
      id: 3,
      title: "Slidess",
      path: "./slidess",
      cName: "submenu-item",
    },
    {
      id: 4,
      title: "Swings",
      path: "./swings",
      cName: "submenu-item",
    },
    {
      id: 5,
      title: "Bikes",
      path: "./bikes",
      cName: "submenu-item",
    },
    {
      id: 6,
      title: "Scooters",
      path: "./scooters",
      cName: "submenu-item",
    },
    
  ];




  const [dropdown, setDropdown] = useState(false);
  const List = serviceDropdownhouse.map((item) => {
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
        className={dropdown ? "services-submenuhouse clicked" : "services-submenuhouse"}
        onClick={() => setDropdown(!dropdown)}
      >
        {List}
      </ul>
    </>
  );
}

export default Dropdownhouse;
