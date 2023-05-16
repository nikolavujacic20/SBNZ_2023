package com.project.cinema.dto;

import java.time.LocalDateTime;

public class MovieDTO {

	private Long id;
	private String name;
	private String description;
	private double basicTicketPrice;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private double capacity;
	
	public MovieDTO() {
		super();
	}

	public MovieDTO(Long id, String name, String description, double basicTicketPrice, LocalDateTime startTime,
		LocalDateTime endTime, double capacity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.basicTicketPrice = basicTicketPrice;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
	}

	public MovieDTO(Long id, String name) {
		this.id = id;
		this.name = name;
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

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	

}
