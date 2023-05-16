import React, { useState } from 'react';
import './Login.css';
import { useNavigate } from 'react-router-dom';
import userLogin from "../services/userLogin";
import jwtDecode from 'jwt-decode';

function Login({ onRoleUpdate }) {

  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  

  const handleSubmit = async (e) => {
    e.preventDefault();
   
  
    try {
      const response = await userLogin(email, password);
      console.log(response.data);
      const { accessToken} = response;
      localStorage.setItem('token',accessToken);
      const decodedToken = jwtDecode(accessToken);
      const role = decodedToken.role;

      const subject = decodedToken.sub;

      console.log("Decoded role:", role);
     
      console.log("Token subject:", subject);
  
  
      if (role === 'ROLE_ADMIN') {
        onRoleUpdate("admin");
        navigate('/users');
      } else {
        localStorage.setItem('role', 'registered');
        onRoleUpdate("registered");
        navigate('/');
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="login-container">
      <div className="login-content">
        <h2 className="login-title">Login</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Email</label>
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Enter your email"
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
          <div className="form-group">
            <button type="submit" className="btn-primary">
              Login
            </button>
          </div>
          <p className="login-info">
            Don't have an account? <a href="/signup">Sign up</a>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Login;