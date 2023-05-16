
import {Routes, Route } from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';

import Navbar from './components/Navbar.jsx'
import Tickets from './pages/Tickets';
import Home from './pages/Home.jsx';
import Login from './pages/Login.jsx';
import Profile from './pages/Profile';
import Footer from './components/Footer.jsx';
import Users from './pages/Users';


import movie1 from './cinema_images/1.jpg'
import movie2 from './cinema_images/2.jpg'
import movie3 from './cinema_images/3.jpg'
import movie4 from './cinema_images/4.jpg'

function App() {

  const [role, setRole] = useState(null);

  const handleRoleUpdate = (newRole) => {
    setRole(newRole);
  };


  const movies = [
    {
      id: 1,
      title: 'Movie 1',
      releaseYear: 2022,
      genre: 'Action',
      imageUrl: movie1,
      description: 'This is the description of Movie 1.',
    },
    {
      id: 2,
      title: 'Movie 2',
      releaseYear: 2020,
      genre: 'Drama',
      imageUrl: movie2,
      description: 'This is the description of Movie 1.',
    },
    {
      id: 3,
      title: 'Movie 3',
      releaseYear: 2020,
      genre: 'Drama',
      imageUrl: movie3,
      description: 'This is the description of Movie 1.',
    },
    {
      id: 4,
      title: 'Movie 4',
      releaseYear: 2020,
      genre: 'Drama',
      imageUrl: movie4,
      description: 'This is the description of Movie 1.',
    },
  
  ];

  const user = {
    name: 'John Doe',
    email: 'johndoe@example.com',
    role: 'admin',
  };


  return (
      <div>
        <Navbar  role={role} onRoleUpdate={handleRoleUpdate}/>
   
      <Routes>
        <Route exact path="/" element={ <Home movies={movies}/>} />
        <Route path="/login" element={ <Login onRoleUpdate={handleRoleUpdate}/> } />
       
        <Route path="/profile" element={ <Profile user ={user}/> } />
        <Route path="/users" element={ <Users/> } />
        <Route path="/tickets" element={ <Tickets/> } />
      </Routes>

      <Footer/>


      </div>


  
  );
};

export default App;