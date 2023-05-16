import React, { useState } from 'react';
import './Profile.css';

const Profile = ({ user }) => {
  const [editing, setEditing] = useState(false);
  const [editedUser, setEditedUser] = useState(user);

  const handleInputChange = (e) => {
    setEditedUser({ ...editedUser, [e.target.name]: e.target.value });
  };

  const handleEditClick = () => {
    setEditing(true);
  };

  const handleSaveClick = () => {
    // Perform save/update logic here
    // For example, you can make an API request to update the user's data

    setEditing(false);
    // Optionally, you can update the original user object with the editedUser data
    // setOriginalUser(editedUser);
  };

  return (
    <div className="profile-container">
      <h2 className="profile-heading">User Profile</h2>
      <div className="profile-info">
        <div className="profile-field">
          <label className="profile-label">Name:</label>
          {editing ? (
            <input
              className="profile-input"
              type="text"
              name="name"
              value={editedUser.name}
              onChange={handleInputChange}
            />
          ) : (
            <span className="profile-value">{user.name}</span>
          )}
        </div>
        <div className="profile-field">
          <label className="profile-label">Email:</label>
          {editing ? (
            <input
              className="profile-input"
              type="email"
              name="email"
              value={editedUser.email}
              onChange={handleInputChange}
            />
          ) : (
            <span className="profile-value">{user.email}</span>
          )}
        </div>
        <div className="profile-field">
          <label className="profile-label">Role:</label>
          {editing ? (
            <input
              className="profile-input"
              type="text"
              name="role"
              value={editedUser.role}
              onChange={handleInputChange}
            />
          ) : (
            <span className="profile-value">{user.role}</span>
          )}
        </div>
        {editing ? (
          <button className="profile-button" onClick={handleSaveClick}>Save</button>
        ) : (
          <button className="profile-button" onClick={handleEditClick}>Edit</button>
        )}
      </div>
    </div>
  );
};

export default Profile;