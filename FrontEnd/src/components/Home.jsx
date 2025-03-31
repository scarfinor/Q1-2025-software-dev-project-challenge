import React from "react";
import { useHistory } from "react-router-dom";
import "../css/Home.css";

const Home = () => {
  const history = useHistory();

  return (
    <div className="home-content">
      <div className="home-card">
        <h2>Welcome to Finance First</h2>
        <p className="intro-text">
          If you don't have an account, sign up below.
        </p>
        <button
          className="action-button sign-up-btn"
          onClick={() => history.push("/signup")}
        >
          Sign Up
        </button>
        <p className="intro-text">Already signed up? Sign in below.</p>
        <button
          className="action-button sign-in-btn"
          onClick={() => history.push("/signin")}
        >
          Sign In
        </button>
      </div>
    </div>
  );
};

export default Home;
