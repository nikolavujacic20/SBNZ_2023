import React from 'react';
import './UserCard.css';

const UserCard = ({ user }) => {
  return (
    <div className="user-card">
      <div className="user-image">
        <img src={user.imageUrl} alt="User" />
      </div>
      <div className="user-details">
        <h3 className="user-name">{user.name}</h3>
        <p className="user-email">{user.email}</p>
        <p className="user-role">{user.role}</p>
      </div>
    </div>
  );
};

export default UserCard;