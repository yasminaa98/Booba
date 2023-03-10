import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Navbar.css";
import Dropdown from "../Dropdown/Dropdown";
import img from '../../Asserts/Logos/building.png';
import edu from '../../Asserts/Logos/education.png';
import imm from '../../Asserts/Logos/imagination.png';
import art from '../../Asserts/Logos/art.png';
import house from '../../Asserts/Logos/house.png';
import Dropdowneducational from "../Dropdowneducational/Dropdowneducational";
import Dropdownimm from "../Dropdownimm/Dropdownimm";
import Dropdownart from "../Dropdownart/Dropdownart";
import Dropdownhouse from "../Dropdownhouse/Dropdownhouse";

function Navbar() {
  const [dropdown, setDropdown] = useState(false);
  const [dropdowned, setDropdowned] = useState(false);
  const [dropdownimm, setDropdownimm] = useState(false);
  const [dropdownart, setDropdownart] = useState(false);
  const [dropdownhouse, setDropdownhouse] = useState(false);

  const navItems = [
    {
      id: 1,
      title: "Building",
      path: "./",
      cName: "nav-item",
      image:img,
    },
    {
      id: 2,
      title: "Educational",
      path: "./services",
      cName: "nav-item",
      image:edu,
    },
    {
      id: 3,
      title: "Imagination",
      path: "./products",
      cName: "nav-item",
      image:imm,
    },
    {
      id: 4,
      title: "Arts and Crafts",
      path: "./contactus",
      cName: "nav-item",
      image:art,
    },
    {
      id: 5,
      title: "Active Play",
      path: "./contactus",
      cName: "nav-item",
      image:house,
    },



   
  ];
  const Listt=navItems.map((item) => 
  {
    if (item.title === "Building") {
      return (
       
       
        <li 
          key={item.id}
          onMouseEnter={() => setDropdown(true)}
          onMouseLeave={() => setDropdown(false)}
          className={item.cName}
        >  
           <img src={item.image} style={{display: 'flex',height: '60px',width:'90px'}}/>
          <Link to={item.path}>{item.title}</Link>
          {dropdown && <Dropdown />}
        </li>
      );
    }

    else  
    
    if (item.title === "Educational") {
     return (
       
        <li 
          key={item.id}
          onMouseEnter={() => setDropdowned(true)}
          onMouseLeave={() => setDropdowned(false)}
          className={item.cName}
        >  
           <img src={item.image} style={{display: 'flex',height: '60px',width:'90px'}}/>
          <Link to={item.path}>{item.title}</Link>
          {dropdowned && <Dropdowneducational />}
        </li>
      );
    }
     else 
      if (item.title === "Imagination") {
      return(
        <li 
          key={item.id}
          onMouseEnter={() => setDropdownimm(true)}
          onMouseLeave={() => setDropdownimm(false)}
          className={item.cName}
        >  
           <img src={item.image} style={{display: 'flex',height: '60px',width:'90px'}}/>
          <Link to={item.path}>{item.title}</Link>
          {dropdownimm && <Dropdownimm />}
        </li>
      );
    }

        else 
        if (item.id ===4 ) {
            return (
              <li 
                key={item.id}
                onMouseEnter={() => setDropdownart(true)}
                onMouseLeave={() => setDropdownart(false)}
                className={item.cName}
              >  
                <img src={item.image} style={{display: 'flex',height: '60px',width:'90px'}}/>
                <Link to={item.path}>{item.title}</Link>
                {dropdownart && <Dropdownart />}
              </li>
            );
          }
          else 
          if (item.id ===5 ) {
              return (
                <li 
                  key={item.id}
                  onMouseEnter={() => setDropdownhouse(true)}
                  onMouseLeave={() => setDropdownhouse(false)}
                  className={item.cName}
                >  
                  <img src={item.image} style={{display: 'flex',height: '60px',width:'90px'}}/>
                  <Link to={item.path}>{item.title}</Link>
                  {dropdownhouse && <Dropdownhouse />}
                </li>
              );
            }

         
  })

  return (
    <>
      <nav className="navbar" sx={{ display: { xs: 'none', md: 'flex' } }}>
      
        <ul className="nav-items">
        {Listt}
        </ul>
      
      </nav>
    </>
  );
}


export default Navbar;


