import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "../css/SignIn.css"; // Make sure styles are linked correctly.

const SignIn = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const handleSignIn = (e) => {
    e.preventDefault();
    console.log("Sign in with", username, password);
    history.push("/Home");
  };

  return (
    <div className="sign-in-form">
      <h2>Finance First Sign In</h2>
      <form onSubmit={handleSignIn}>
        <div className="form-group">
          <label>Username</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter your username"
            required
          />
        </div>
        <div className="form-group">
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter your password"
            required
          />
        </div>
        <button type="submit" className="action-button sign-in-btn">
          Sign In
        </button>
      </form>
    </div>
  );
};

export default SignIn;
