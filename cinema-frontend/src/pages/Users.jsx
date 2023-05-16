import React from 'react';

import UsersOverview from '../components/UsersOverview';
import img1 from '../cinema_images/icon.png';

const users = [
  {
    id: 1,
    name: 'John Doe',
    email: 'john@example.com',
    role: 'Admin',
    imageUrl: img1,
  },
  {
    id: 2,
    name: 'Jane Smith',
    email: 'jane@example.com',
    role: 'Registered',
    imageUrl: img1,
  },
  
];

function Users () {
    return (
        <div>
          <h2>User Overview</h2>
          <UsersOverview users={users} />
        </div>
      );
};

export default Users;