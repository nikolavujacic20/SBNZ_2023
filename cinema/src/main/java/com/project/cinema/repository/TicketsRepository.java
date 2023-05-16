package com.project.cinema.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Tickets;

@Repository
public interface TicketsRepository extends JpaRepository<Tickets, Long>{
	
	@Query(value = "SELECT * FROM TICKETS WHERE MOVIE_ID = ?1 AND USING_START <= ?2 AND USING_END >= ?2", nativeQuery = true)
	List<Tickets> findByDate(Long movieId, Date forDate);


}
