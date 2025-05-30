import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import "../css/SignUp.css";

const SignUp = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [verifiedPassword, setVerifiedPassword] = useState("");
  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [role, setRole] = useState("");
  const [errors, setErrors] = useState({});
  const history = useHistory();

  const handleSubmit = (e) => {
    e.preventDefault();

    const newRole = "ROLE_USER";
    setRole(newRole);
    console.log(role);

    const newErrors = {};
    if (!username) newErrors.username = "Username is required!";
    if (!email) newErrors.email = "Email is required!";
    if (password !== verifiedPassword)
      newErrors.password = "Passwords don't match!";
    setErrors(newErrors);

    if (Object.keys(newErrors).length === 0) {
      console.log("Form Submitted", {
        username,
        email,
        password,
        role: newRole,
      });
      history.push("/signin");
    }
  };

  return (
    <div className="page-container">
      <div className="auth-content">
        <h2>Create Finance First Account</h2>
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
            <label>First Name</label>
            <input
              type="text"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              placeholder="Enter your First Name"
            />
            {errors.firstName && <p className="error">{errors.firstName}</p>}
          </div>
          <div className="form-group">
            <label>Last Name</label>
            <input
              type="text"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              placeholder="Enter your Last Name"
            />
            {errors.lastName && <p className="error">{errors.lastName}</p>}
          </div>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Enter your email"
            />
            {errors.email && <p className="error">{errors.email}</p>}
          </div>
          <div className="form-group">
            <label>Password</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password"
            />
          </div>
          <div className="form-group">
            <label>Confirm Password</label>
            <input
              type="password"
              value={verifiedPassword}
              onChange={(e) => setVerifiedPassword(e.target.value)}
              placeholder="Confirm your password"
            />
            {errors.password && <p className="error">{errors.password}</p>}
          </div>
          <button type="submit">Sign Up</button>
        </form>
      </div>
    </div>
  );
};

export default SignUp;
