package com.project.cinema.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDateTime;


@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(unique = false, nullable = false)
	private String description;
	
	@Column(unique = false, nullable = false)
	private double basicTicketPrice;
	
	@Column(unique = false, nullable = false)
	private LocalDateTime startTime;
	
	@Column(unique = false, nullable = false)
	private LocalDateTime endTime;
	
	@Column(unique = false, nullable = false)
	private double capacity;

	public Movie() {
		super();
	}

	public Movie(Long id, String name, String description,
		 	double basicTicketPrice, LocalDateTime startTime, LocalDateTime endTime, double capacity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.startTime = startTime;
		this.endTime = endTime;
		this.basicTicketPrice = basicTicketPrice;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public double getCapacity() {
		return capacity;
	}


	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getBasicTicketPrice() {
		return basicTicketPrice;
	}

	public void setBasicTicketPrice(double basicTicketPrice) {
		this.basicTicketPrice = basicTicketPrice;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}
