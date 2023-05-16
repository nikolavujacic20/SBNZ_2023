import axios from 'axios';
import { BASE_URL } from '../constants/constants';

export const createUser = async (user) => {
  try {
    const response = await axios.post(`${BASE_URL}/users`, user);
    const createdUser = response.data;
    console.log('User created:', createdUser);
  } catch (error) {
    console.log('Error creating user:', error);
  }
};