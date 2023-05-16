package com.project.cinema.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cinema.dto.MovieDTO;
import com.project.cinema.model.Movie;
import com.project.cinema.model.Tickets;
import com.project.cinema.repository.MovieRepository;
import com.project.cinema.repository.TicketsRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private KieService kieService;
	
	public List<Movie> findAll(){
		return repository.findAll();
	}
	
	public Movie findById(Long id) {
		Movie movie = repository.findById(id).orElse(null);
		return movie;
	}
	
	public List<Tickets> findTicketsByDate(Date forDate, Long movieId)
	{
		List<Tickets> tickets = ticketsRepository.findByDate(movieId, forDate);
		return tickets;
	}

	public Movie create(MovieDTO movie) {
		// TODO Auto-generated method stub
		Movie sr = new Movie();

		sr.setName(movie.getName());
		sr.setDescription(movie.getDescription());
		sr.setBasicTicketPrice(movie.getBasicTicketPrice());
		sr.setStartTime(movie.getStartTime());
		sr.setEndTime(movie.getEndTime());
		sr.setCapacity(movie.getCapacity());
		try {
			sr = repository.save(sr);
		}
		catch(Exception e){
			return null;
		}
		
		return sr;
	}

	public Movie update(MovieDTO movie) {
		Movie sr = repository.findById(movie.getId()).orElse(null);
		if(sr != null) {
			sr.setName(movie.getName());
			sr.setDescription(movie.getDescription());
			sr.setBasicTicketPrice(movie.getBasicTicketPrice());
			sr.setStartTime(movie.getStartTime());
			sr.setEndTime(movie.getEndTime());
			sr.setCapacity(movie.getCapacity());
			try {
				sr = repository.save(sr);
			}
			catch(Exception e){
				return null;
			}
			return sr;
		}
		return null;
	}

	public Movie delete(Long id) {
		Movie existing = repository.findById(id).orElse(null);
		if(existing != null) {
			return repository.save(existing);
		}
		return null;
	}
	
	public List<Movie> findByName(String name){
		List<Movie> movies = findAll();
		List<Movie> retVal = new ArrayList<>();
		movies.forEach(kieService.getRuleSession()::insert);
		QueryResults res = kieService.getRuleSession().getQueryResults("findMovieByName", name);
	    for(QueryResultsRow row: res) {
	    	Movie movie = (Movie)row.get("movie");
	    	retVal.add(movie);
	    }
	    kieService.disposeRuleSession();
	    return retVal;
	     
	}
}
