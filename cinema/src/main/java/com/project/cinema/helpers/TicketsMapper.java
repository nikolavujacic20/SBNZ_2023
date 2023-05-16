package com.project.cinema.helpers;

import java.util.HashSet;
import java.util.Set;

import com.project.cinema.dto.TicketUserDTO;
import com.project.cinema.dto.TicketsDTO;
import com.project.cinema.model.Tickets;
import com.project.cinema.model.UserType;

public class TicketsMapper {

	public static TicketsDTO toDto (Tickets entity) {
		Set<TicketUserDTO> users = new HashSet<TicketUserDTO>();
		users.add(new TicketUserDTO(UserType.CHILD, entity.getNumOfChildren(), 0.0));
		users.add(new TicketUserDTO(UserType.ADULT, entity.getNumOfAdult(), 0.0));
		users.add(new TicketUserDTO(UserType.SENIOR, entity.getNumOfSenior(), 0.0));

		
		return new TicketsDTO(entity.getId(), MovieMapper.toDto(entity.getMovie()),
				entity.getTypeTicket(),entity.getUsingPeriod(), entity.getInitialPrice(),
				users, entity.getBill());
	}
	
	public static Tickets toEntity (TicketsDTO dto) {
		int numOfChildren = 0;
		int numOfAdult = 0;
		int numOfSenior = 0;
		
		for(TicketUserDTO t: dto.getTicketUsers()) {
			
			switch(t.getUserType()) {
				case CHILD:
					numOfChildren = t.getCount();
					break;
				case ADULT:
					numOfAdult = t.getCount();
					break;
				case SENIOR:
					numOfSenior = t.getCount();
					break;
			}
		}
		
		
		
		return new Tickets(dto.getId(), MovieMapper.toEntity(dto.getMovie()),
				dto.getTypeTicket(),dto.getUsingPeriod(), dto.getInitialPrice(),
				numOfChildren, numOfAdult, numOfSenior, dto.getBill());
	}
}
