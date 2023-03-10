import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Dropdowneducational.css";

function Dropdowneducational() {
  const serviceDropdowned = [
    {
      id: 1,
      title: "Science Kits",
      path: "./sciencekits",
      cName: "submenu-item",
    },
    {
      id: 2,
      title: " Math Games",
      path: "./mathgames",
      cName: "submenu-item",
    },
    {
      id: 3,
      title: "Language Learning Toys",
      path: "./languagelearningtoys",
      cName: "submenu-item",
    },
    {
      id: 4,
      title: "Geography Puzzles",
      path: "./geographypuzzles",
      cName: "submenu-item",
    },
    {
      id: 5,
      title: "Coding Games",
      path: "./codinggames",
      cName: "submenu-item",
    },
  
  ];


  const [dropdown, setDropdown] = useState(false);
  const List = serviceDropdowned.map((item) => {
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
        className={dropdown ? "services-submenued clicked" : "services-submenued"}
        onClick={() => setDropdown(!dropdown)}
      >
        {List}
      </ul>
    </>
  );
}

export default Dropdowneducational;
