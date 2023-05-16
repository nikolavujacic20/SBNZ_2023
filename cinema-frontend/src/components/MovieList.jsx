import React from 'react';
import './MovieList.css';

const MovieList = ({ movies, onBuyTickets }) => {
  return (
    <div className="movie-list-container">
      <div className="movie-list">
        {movies.map((movie) => (
          <div key={movie.id} className="movie-item">
            <div className="movie-image">
              <img src={movie.imageUrl} alt={movie.name} />
            </div>
            <div className="movie-details">
              <div className="movie-text">
                <div className="movie-info">
                  <h3 className="movie-title">{movie.name}</h3>
                  <p>Release Year: {movie.releaseYear}</p>
                  <p>Genre: {movie.genre}</p>
                </div>
                <p className="movie-description">{movie.description}</p>
              </div>
              <div className="movie-actions">
                <button
                  className="buy-tickets-button"
                  onClick={() => onBuyTickets(movie)}
                >
                  Buy Tickets
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MovieList;