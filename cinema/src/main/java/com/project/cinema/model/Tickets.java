package com.project.cinema.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id", nullable = true)
	private Movie movie;

	@Column(unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private TypeTicket typeTicket; // porodicna, grupna, pojedinacna
	
	@Column(unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private UsingPeriod usingPeriod; // dnevna, poludnevna, nocna
	
	@Column(unique = false, nullable = true)
	private double initialPrice;
	
	@Column(unique = false, nullable = true)
	private int numOfChildren;
	
	@Column(unique = false, nullable = true)
	private int numOfAdult;
	
	@Column(unique = false, nullable = true)
	private int numOfSenior;
	
	@Column(unique = false, nullable = false)
	private double bill;
	

	
	
	public Tickets(Long id) {
		super();
		this.id = id;
	}

	public Tickets(Long id, Movie movie, TypeTicket typeTicket, UsingPeriod usingPeriod,
				double initialPrice, int numOfChildren, int numOfAdult, int numOfSenior,
				double bill) {
		super();
		this.id = id;
		this.movie = movie;
		this.typeTicket = typeTicket;
		this.usingPeriod = usingPeriod;
		this.initialPrice = initialPrice;
		this.numOfChildren = numOfChildren;
		this.numOfAdult = numOfAdult;
		this.numOfSenior = numOfSenior;
		this.bill = bill;
	}

	public Tickets() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
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

	public int getNumOfChildren() {
		return numOfChildren;
	}

	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}

	public int getNumOfAdult() {
		return numOfAdult;
	}

	public void setNumOfAdult(int numOfAdult) {
		this.numOfAdult = numOfAdult;
	}

	public int getNumOfSenior() {
		return numOfSenior;
	}

	public void setNumOfSenior(int numOfSenior) {
		this.numOfSenior = numOfSenior;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}
	

}
