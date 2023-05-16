import axios from 'axios';
import { BASE_URL } from '../constants/constants';


const userLogin = async (username, password) => {
  const headers = {
    'Content-Type': 'application/json',
  };

  try {
    const response = await axios.post(`${BASE_URL}/auth/log-in`,{ username, password },{ headers, responseType: 'json' });

    if (response && response.data) {
      return response.data;
    } else {
      throw new Error("Invalid response format");
    }
  } catch (error) {
    console.error(error);
    console.log("Error occurred during login or fetch request");
    throw error; 
  }
};

export default userLogin;