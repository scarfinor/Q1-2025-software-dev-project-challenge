import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "../css/SignIn.css";

const SignIn = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState({});
  const history = useHistory();

  const handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = {};
    if (!username) newErrors.username = "Username is required!";
    if (!password) newErrors.password = "Password is required!";
    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      console.log("Form Submitted", { username, password });
      history.push("/home");
    }
  };

  return (
    <div className="page-container">
      <div className="auth-content">
        <h2>Sign In to Finance First</h2>
        <form onSubmit={handleSubmit} className="form-container">
          <div className="form-group">
            <label>Username</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter your username"
            />
            {errors.username && <p className="error">{errors.username}</p>}
          </div>

          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password"
            />
            {errors.password && <p className="error">{errors.password}</p>}
          </div>

          <button type="submit">Sign In</button>
        </form>
      </div>
    </div>
  );
};

export default SignIn;
