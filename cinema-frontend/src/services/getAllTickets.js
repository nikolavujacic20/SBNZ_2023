import axios from 'axios';
import { BASE_URL } from '../constants/constants';

const getAllTickets = async () => {
  const authToken = localStorage.getItem('token');
  
  const headers = {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${authToken}`
  };

  try {
    const response = await axios.get(`${BASE_URL}/api/tickets/my-tickets`, { headers: headers});
    return response.data;
  } catch (error) {
    throw new Error(error.message);
  }
};

export default getAllTickets;