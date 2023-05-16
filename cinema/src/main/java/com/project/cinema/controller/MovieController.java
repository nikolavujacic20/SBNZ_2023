package com.project.cinema.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.dto.MovieDTO;
import com.project.cinema.helpers.MovieMapper;
import com.project.cinema.model.Movie;
import com.project.cinema.service.MovieService;

@RestController
@RequestMapping(value = "/api/movie", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MovieDTO>> getAllMovies(){
		List<Movie> movies = movieService.findAll();
		return new ResponseEntity<>(toDTOList(movies), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('REGISTERED_USER', 'ROLE_ADMIN')")
	public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id){
		Movie movie = movieService.findById(id);
		if(movie == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(MovieMapper.toDto(movie), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/search/{name}" , method = RequestMethod.GET)
	public ResponseEntity<List<MovieDTO>>searchByName(@PathVariable String name){
		List<Movie> movie = movieService.findByName(name);
		if(movie == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(toDTOList(movie), HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MovieDTO> addNew(@RequestBody MovieDTO movie){
		Movie created = movieService.create(movie);
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(MovieMapper.toDto(created), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MovieDTO> editmovie(@RequestBody MovieDTO movie){
		Movie created = movieService.update(movie);
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(MovieMapper.toDto(created), HttpStatus.OK);
	}
	@RequestMapping(value="/delete/{id}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MovieDTO> deletemovie(@PathVariable Long id){
		
		Movie created = movieService.delete(id);
		return new ResponseEntity<>(MovieMapper.toDto(created), HttpStatus.OK);
	}
	private List<MovieDTO> toDTOList(List<Movie> list){
		List<MovieDTO> retVal = new ArrayList<MovieDTO>();
		for(Movie movie: list) {
			MovieDTO dto = MovieMapper.toDto(movie);
			retVal.add(dto);
		}
		return retVal;
	}
}
