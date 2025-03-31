import React from "react";
import { NavLink } from "react-router-dom";
import { IoIosCash } from "react-icons/io";
import "../css/Footer.css";

const Footer = () => {
  return (
    <footer>
      <div className="footer-container">
        <NavLink to="/dashboard" className="footer-dashboard">
          <IoIosCash />
          Dashboard
        </NavLink>
      </div>
    </footer>
  );
};

export default Footer;
