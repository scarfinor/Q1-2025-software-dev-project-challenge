import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import {
  IoIosLogOut,
  IoIosLogIn,
  IoIosAddCircleOutline,
  IoIosHome,
  IoIosMenu,
} from "react-icons/io";
import { FaTimes } from "react-icons/fa";
import "../css/Header.css";

const Header = () => {
  const [menuOpen, setMenuOpen] = useState(false);

  const toggleMenu = () => setMenuOpen(!menuOpen);

  return (
    <nav>
      <div className="div-header">
        <div className="div-logo">
          <NavLink to="/">
            <IoIosHome />
            Home
          </NavLink>
        </div>
        <div className="hamburger-menu" onClick={toggleMenu}>
          <IoIosMenu />
        </div>
        <div className="div-link">
          <NavLink to="/signup">
            <IoIosAddCircleOutline />
            Sign Up
          </NavLink>
          <NavLink to="/signin">
            <IoIosLogIn />
            Sign In
          </NavLink>
          <NavLink to="/signin">
            <IoIosLogOut />
            Log Out
          </NavLink>
        </div>
      </div>
      <div className={`side-menu ${menuOpen ? "open" : ""}`}>
        <div className="menu-close" onClick={toggleMenu}>
          <FaTimes />
        </div>
        <div className="side-menu-container">
          <NavLink to="/signup" onClick={toggleMenu}>
            <IoIosAddCircleOutline />
            Sign Up
          </NavLink>
          <NavLink to="/signin" onClick={toggleMenu}>
            <IoIosLogIn />
            Sign In
          </NavLink>
          <NavLink to="/signin" onClick={toggleMenu}>
            <IoIosLogOut />
            Log Out
          </NavLink>
        </div>
      </div>
    </nav>
  );
};

export default Header;
