package com.project.cinema.helpers;

import com.project.cinema.dto.MovieDTO;
import com.project.cinema.model.Movie;

public class MovieMapper {
	
	public static MovieDTO toDto (Movie entity) {
		return new MovieDTO(entity.getId(), entity.getName(), entity.getDescription(),
		entity.getCapacity(), entity.getStartTime(), entity.getEndTime(), entity.getBasicTicketPrice());
	}

	public static Movie toEntity (MovieDTO dto) {
		return new Movie(dto.getId(), dto.getName(), dto.getDescription(), dto.getBasicTicketPrice(),
		dto.getStartTime(), dto.getEndTime(), dto.getCapacity());
	}
}
