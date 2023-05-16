package com.project.cinema.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.cinema.dto.RegisteredUserDTO;
import com.project.cinema.dto.ReservationEvent;
import com.project.cinema.dto.TicketUserDTO;
import com.project.cinema.dto.TicketsDTO;
import com.project.cinema.helpers.TicketsMapper;
import com.project.cinema.model.Movie;
import com.project.cinema.model.RegisteredUser;
import com.project.cinema.model.Tickets;
import com.project.cinema.model.User;
import com.project.cinema.repository.MovieRepository;
import com.project.cinema.repository.TicketsRepository;

@Service
public class TicketsService {
	
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private RegisteredUserService registeredUserService;
	
	@Autowired
	private KieService kieService;
	

	public TicketsDTO getFinalPrice(TicketsDTO tickets) {
		System.out.println("Calculating final price");
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("using_period").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("user_type_discount").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();
		
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("period_discount").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("type_ticket").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();

		kieService.getRuleSession().getAgenda().getAgendaGroup("bill_type_discount").setFocus();
		kieService.getRuleSession().insert(tickets);
		kieService.getRuleSession().fireAllRules();
		
		// preuzimanje usera is konteksta
		RegisteredUser registeredUser;
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = ((User) currentUser.getPrincipal()).getEmail();
        registeredUser = registeredUserService.findByEmail(username);
        
        RegisteredUserDTO regDto = new RegisteredUserDTO();
        regDto.setTickets(toDtoSet(registeredUser.getTickets()));
        
        System.out.println(registeredUser.getTickets().stream().findFirst().get());
        
        kieService.getRuleSession().getAgenda().getAgendaGroup("regular_guest").setFocus();
        kieService.getRuleSession().insert(regDto);
        kieService.getRuleSession().insert(tickets);
        kieService.getRuleSession().fireAllRules();
		
		
		kieService.getRuleSession().getAgenda().getAgendaGroup("bonus").setFocus();
        kieService.getRuleSession().insert(tickets);
        kieService.getRuleSession().fireAllRules();

		kieService.disposeRuleSession();
		
		System.out.println(tickets);
		
		return tickets;
		
	}
	private List<TicketsDTO> toDtoList(List<Tickets> list){
		List<TicketsDTO> retVal = new ArrayList<TicketsDTO>();
		for(Tickets t: list) {
			TicketsDTO dto = TicketsMapper.toDto(t);
			retVal.add(dto);
		}
		return retVal;
	}
	
	private Set<TicketsDTO> toDtoSet(Set<Tickets> list){
		Set<TicketsDTO> retVal = new HashSet<TicketsDTO>();
		for(Tickets t: list) {
			TicketsDTO dto = TicketsMapper.toDto(t);
			retVal.add(dto);
		}
		return retVal;
	}
	
	
	public double calculateOccupacy(Date forDate, Long movieId) {
		int sum = 0;
		List<Tickets> tickets = findTicketsByDate(forDate, movieId);
		for(Tickets t: tickets) {
			sum = sum + t.getNumOfChildren() + t.getNumOfAdult() + t.getNumOfSenior();
		}
		Movie movie = movieRepository.findById(movieId).orElse(null);
		if(movie != null) {
			return 100*sum/movie.getCapacity();
		}
		
		return 0;
	}
	
	public List<Tickets> findTicketsByDate(Date forDate, Long movieId)
	{
		List<Tickets> tickets = ticketsRepository.findByDate(movieId, forDate);
		return tickets;
	}

	public Tickets create(TicketsDTO tickets) {
		
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = ((User) currentUser.getPrincipal()).getEmail();
        
		Tickets t = new Tickets();

		Movie movie = movieRepository.findById(tickets.getMovie().getId()).orElse(null);
		t.setMovie(movie);
		t.setTypeTicket(tickets.getTypeTicket());
		t.setUsingPeriod(tickets.getUsingPeriod());
		t.setInitialPrice(tickets.getInitialPrice());
		for(TicketUserDTO tu: tickets.getTicketUsers()) {
			switch(tu.getUserType()) {
			case CHILD:
				t.setNumOfChildren(tu.getCount());
				break;
			case ADULT:
				t.setNumOfAdult(tu.getCount());
				break;
			case SENIOR:
				t.setNumOfSenior(tu.getCount());
				break;
			}
		}
		t.setBill(tickets.getBill());
		t = ticketsRepository.save(t);
		
		ReservationEvent event = new ReservationEvent(username);
		kieService.getCepSession().insert(event);
		kieService.getCepSession().getAgenda().getAgendaGroup("reservations").setFocus();
		kieService.getCepSession().fireAllRules();
		System.out.println("reservation event");
		
		return t;
	}
	public void delete(Long id) {
		Tickets found = ticketsRepository.findById(id).orElse(null);
		if(found != null) {
			ticketsRepository.delete(found);
		}
		
	}
	public TicketsDTO getById(Long id) {
		Tickets found = ticketsRepository.findById(id).orElse(null);
		if(found != null) {
			return TicketsMapper.toDto(found);
		}
		return null;
	}
	
}
