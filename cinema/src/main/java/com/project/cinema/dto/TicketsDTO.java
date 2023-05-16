package com.project.cinema.dto;

import java.util.Set;

import com.project.cinema.model.TypeTicket;
import com.project.cinema.model.UserType;
import com.project.cinema.model.UsingPeriod;


public class TicketsDTO {

	private Long id;
	private MovieDTO movie;
	private TypeTicket typeTicket; // porodicna, grupna, pojedinacna
	private UsingPeriod usingPeriod; // dnevna, poludnevna, nocn
	private double initialPrice;
	private Set<TicketUserDTO> ticketUsers; // TicketUser, count
	private double bill;
	private Set<String> privilege; // student or loyalty
	
	private String regGuest;
	
	
	public TicketsDTO() {
		super();
	}
	
	

	public TicketsDTO(Long id, MovieDTO movie) {
		super();
		this.id = id;
		this.movie = movie;
	}


	public TicketsDTO(Long id, MovieDTO movie,  TypeTicket typeTicket, UsingPeriod usingPeriod,
			double initialPrice, Set<TicketUserDTO> ticketUsers, double bill) {
		super();
		this.id = id;
		this.movie = movie;
		this.typeTicket = typeTicket;
		this.usingPeriod = usingPeriod;
		this.initialPrice = initialPrice;
		this.ticketUsers = ticketUsers;
		this.bill = bill;
	}

	public void addToBill(double price) {
		this.bill = this.bill + price;
	}
	
	public void calculateBill() {
		double bill = 0;
		for(TicketUserDTO tu: this.getTicketUsers()) {
			bill = bill + tu.getSingleTicketPrice()*tu.getCount();
		}
		this.bill = bill;
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MovieDTO getMovie() {
		return movie;
	}

	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}


	public TypeTicket getTypeTicket() {
		return typeTicket;
	}



	public void setTypeTicket(TypeTicket typeTicket) {
		this.typeTicket = typeTicket;
	}



	public UsingPeriod getUsingPeriod() {
		return usingPeriod;
	}



	public void setUsingPeriod(UsingPeriod usingPeriod) {
		this.usingPeriod = usingPeriod;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Set<TicketUserDTO> getTicketUsers() {
		return ticketUsers;
	}

	public void setTicketUsers(Set<TicketUserDTO> ticketUsers) {
		this.ticketUsers = ticketUsers;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}
	
	public Set<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Set<String> privilege) {
		this.privilege = privilege;
	}

	public int getNumberOfUsers() {
		int count = 0;
		for (TicketUserDTO tu: this.ticketUsers) {
			count = count + tu.getCount();
		}
		return count;
	}
	
	public void addDiscount(double percent) {
		this.bill = this.bill*(100-percent)/100;
	}
	
	public void increasePrice(double percent) {
		this.bill = this.bill*(100+percent)/100;
	}
	
	public int getUsersCount(UserType userType) {
		int retVal = 0;
		for(TicketUserDTO tu: this.ticketUsers) {
			if(tu.getUserType().equals(userType))
				retVal = tu.getCount();
		}
		return retVal;
	}
	
	
	public void addPrivilege(String privilege) {
		this.privilege.add(privilege);
	}
	
	
	public boolean isRegularGuest() {
		if(this.privilege == null)
			return false;
		for (String privilege: this.privilege) {
			if(privilege.equals("RegularGuest"))
				return true;
		}
		return false;
	}

	public String getRegGuest() {
		return regGuest;
	}

	public void setRegGuest(String regGuest) {
		this.regGuest = regGuest;
	}


	@Override
	public String toString() {
		String retVal = "bill: " + this.bill + " + users: " + this.ticketUsers;
		return retVal;
	}
	
}
