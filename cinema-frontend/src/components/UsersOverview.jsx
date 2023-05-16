import React from 'react';
import UserCard from './UserCard';

function UsersOverview ({ users }) {
  return (
    <div className="user-overview">
      {users.map((user) => (
        <UserCard key={user.id} user={user} />
      ))}
    </div>
  );
};

export default UsersOverview;