import React, { useState, useEffect } from 'react';
import './Home.css';
import MovieList from '../components/MovieList';
import getAllMovies from '../services/getAllMovies';
import Modal from 'react-modal';
import createTicket from '../services/createTicket';
import addTicket from '../services/addTicket';

function Home() {
  const [movies, setMovies] = useState([]);
  const [movie, setMovie] = useState([]);
  const [ticket, setTicket] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [showModal1, setShowModal1] = useState(false);
  const [ticketValues, setTicketValues] = useState({
    children: 0,
    adults: 0,
    seniors: 0,
    student: 0,
    loyalty: 0
  });

  const toggleModal1 = async () => {
    setShowModal1(!showModal1);



  };

  const addTickets = async () => {

    try {
       await addTicket(ticket);
      
      setShowModal1(!showModal1);
    } catch (error) {
      console.error('Error fetching movies:', error);
    }


  }

  const toggleModal = (movie) => {
    setShowModal(!showModal);
    setMovie(movie);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getAllMovies();
        setMovies(response);
      } catch (error) {
        console.error('Error fetching movies:', error);
      }
    };

    fetchData();
  }, []);

  const handleBuyTickets = async () => {

    setShowModal(true);
    console.log(movie.capacity);


    try {



      const ticket = {
        movie: {
          id: movie.id,
          name: movie.name,
          description: movie.description,
          capacity: movie.capacity,
          start_time: movie.start_time,
          end_time: movie.end_time,
          basic_ticket_price: movie.basic_ticket_price

        },
        usingPeriod: "DAY",
        typeTicket: "SINGLE",
        ticketUsers: [
          {
            userType: "ADULT",
            count: ticketValues.adults,
            singleTicketPrice: 500.00,
          },
          {
            userType: "CHILD",
            count: ticketValues.children,
            singleTicketPrice: 500.00,
          },
          {
            userType: "SENIOR",
            count: ticketValues.adults,
            singleTicketPrice: 500.00,
          }

        ],

        initialPrice: 0,
        privilege: []
      };

      console.log(ticket);
      const price = await createTicket(ticket);
      console.log(price);
      localStorage.setItem('bill', price.bill);
      setTicket(price);

      setShowModal(true);
      toggleModal1();
    } catch (error) {
      console.error(error);

    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setTicketValues((prevValues) => ({
      ...prevValues,
      [name]: value
    }));
  };


  Modal.setAppElement('#root');
  return (
    <div className="cinema-homepage">
      <MovieList movies={movies} onBuyTickets={toggleModal} />

      <Modal
        isOpen={showModal}
        onRequestClose={toggleModal}
        contentLabel="Modal"
        className="custom-modal"
      >
        <h2 className="modal-title">Tickets</h2>
        <div className="modal-content">
          <div className="number-fields">
            <div className="field">
              <label>Children:</label>
              <input type="number" name="children" value={ticketValues.children} onChange={handleInputChange} />
            </div>
            <div className="field">
              <label>Adults:</label>
              <input type="number" name="adults" value={ticketValues.adults} onChange={handleInputChange} />
            </div>
            <div className="field">
              <label>Seniors:</label>
              <input type="number" name="seniors" value={ticketValues.seniors} onChange={handleInputChange} />
            </div>
            <div className="field">
              <label>Student:</label>
              <input type="number" name="student" value={ticketValues.student} onChange={handleInputChange} />
            </div>
            <div className="field">
              <label>Loyalty:</label>
              <input type="number" name="loyalty" value={ticketValues.loyalty} onChange={handleInputChange} />
            </div>
          </div>
          <div className="movie-label">This is <strong>{movie.name}</strong></div>
          <div className="movie-label">Genre: <strong>{movie.description}</strong></div>
          <div className="button-container">
            <button className="buy-button" onClick={() => handleBuyTickets()}>
              Buy Tickets
            </button>
            <button className="close-button-red" onClick={toggleModal}>
              Close Modal
            </button>
          </div>
        </div>
      </Modal>

      <Modal
        isOpen={showModal1}
        onRequestClose={toggleModal1}
        contentLabel="Modal"
        className="custom-modal"
      >
        <h2>Your ticket price is:</h2>
        <p><strong>{localStorage.getItem('bill')}</strong></p>

        <div className="button-container">
          <button className="buy-button" onClick={() => addTickets()}>
            Approve
          </button>
          <button className="close-button-red" onClick={toggleModal1}>
            Cancel
          </button>
        </div>

      </Modal>
    </div>
  );
};

export default Home;