import React, { useState, useEffect } from 'react';
import getAllTickets from '../services/getAllTickets';
import './Tickets.css';

const Tickets = () => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    //
    const fetchTickets = async () => {
      try {
        const response = await getAllTickets();
        setTickets(response);
        console.log(response[0].id);
      } catch (error) {
        console.error('Error fetching movies:', error);
      }

    };

    fetchTickets();
  }, []);

  return (
    <div className="tickets-container">

     
      
     
        <table className="tickets-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Movie</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
          {tickets.map(item => (
          <tr key={item.id}>
            <td>{item.id}</td>
            <td>{item.movie.name}</td>
            <td>{item.bill}</td>
          </tr>
             
             ))}
          </tbody>
        </table>
      
    </div>
  );
};

export default Tickets;