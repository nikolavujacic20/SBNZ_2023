import axios from 'axios';
import { BASE_URL } from '../constants/constants';

const addTicket = async (ticket) => {

    const authToken = localStorage.getItem('token');
    console.log(authToken);
    const headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authToken}`
        
    };


    try {
        const response = await axios.post(`${BASE_URL}/api/tickets/`, ticket, { headers: headers});
        return response.data;
    } catch (error) {
        throw new Error(error.message);
    }
};

export default addTicket;