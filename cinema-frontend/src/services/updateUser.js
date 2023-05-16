import axios from 'axios';
import { BASE_URL } from '../constants/constants';

export const updateUser = async (userId, updatedUser) => {
  try {
    const response = await axios.put(`${BASE_URL}/users/${userId}`, updatedUser);
    const updatedUserData = response.data;
    console.log('User updated:', updatedUserData);
  } catch (error) {
    console.log('Error updating user:', error);
  }
};