import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';
import profile from '../cinema_images/profile.png';

const Navbar = ({ role, onRoleUpdate }) => {
  const isUserLoggedIn = role === 'admin' || role === 'registered';
  const navigate = useNavigate();

  const handleLogoutClick = () => {
    onRoleUpdate('');
    navigate('/');
  };

  return (
    <nav className="navbar">
      <div className="navbar-links">
       
        <Link to="/" className="navbar-link"><b>Home</b></Link>
        {isUserLoggedIn && (
          <>
            {role === 'admin' && (
              <Link to="/users" className="navbar-link"><b>Users</b></Link>
            )}
            <Link to="/tickets" className="navbar-link"><b>Tickets</b></Link>
          </>
        )}
      </div>
      <div className="navbar-logout">
        {isUserLoggedIn ? (
          <span className="navbar-link" onClick={handleLogoutClick}>
            Logout
          </span>
        ) : (
          <Link to="/login" className="navbar-link"><b>Login</b></Link>
        )}
      </div>
      {isUserLoggedIn && (
        <Link to="/profile">
          <img src={profile} alt="Profile" style={{ width: 50, height: 50 }} />
        </Link>
      )}
    </nav>
  );
};

export default Navbar;