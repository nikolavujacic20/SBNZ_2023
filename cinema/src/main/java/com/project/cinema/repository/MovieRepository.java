package com.project.cinema.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	
	List<Movie> findAll();
	
	Optional<Movie> findById(Long id);	
	
}
