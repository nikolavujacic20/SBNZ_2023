package com.project.cinema.dto;

import java.util.Set;


public class RegisteredUserDTO {
	private Set<TicketsDTO> tickets;
	

	public RegisteredUserDTO(Set<TicketsDTO> tickets) {
		super();
		this.tickets = tickets;
	}

	public RegisteredUserDTO() {
		super();
	}

	public Set<TicketsDTO> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketsDTO> tickets) {
		this.tickets = tickets;
	}
	
}
