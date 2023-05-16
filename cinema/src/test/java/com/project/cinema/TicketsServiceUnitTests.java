package com.project.cinema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.cinema.dto.MovieDTO;
import com.project.cinema.dto.RegisteredUserDTO;
import com.project.cinema.dto.TicketUserDTO;
import com.project.cinema.dto.TicketsDTO;
import com.project.cinema.model.TypeTicket;
import com.project.cinema.model.UserType;
import com.project.cinema.model.UsingPeriod;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketsServiceUnitTests {
	
	private static KieContainer kieContainer;
	private static SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("com.main", "cinema-drools", "0.0.1-SNAPSHOT"));
    }

    
    @Test
    public void testFamilyTicketType() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.SINGLE);

		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 2, 100);
		TicketUserDTO deca = new TicketUserDTO(UserType.CHILD, 3, 100);
		ticketUsers.add(odrasli);
		ticketUsers.add(deca);
		t.setTicketUsers(ticketUsers);
		
		t.setUsingPeriod(UsingPeriod.DAY);
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(TypeTicket.FAMILY, t.getTypeTicket());
		
		kieSession.dispose();
    }
    
    @Test
    public void testGroupTicketType() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.SINGLE);
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 20, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		t.setUsingPeriod(UsingPeriod.DAY);
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(TypeTicket.GROUP, t.getTypeTicket());
		
		kieSession.dispose();
    }
    
	@Test
    public void testHalfDayUsingPeriod() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.SINGLE);
    	t.setUsingPeriod(UsingPeriod.HALFDAY);
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 2, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("using_period").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(65), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
    
	@Test
    public void testNightUsingPeriod() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.SINGLE);
    	t.setUsingPeriod(UsingPeriod.NIGHT);
		
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 2, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("using_period").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(125), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
	
	@Test
    public void testGroupDiscount() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.GROUP);
    	t.setUsingPeriod(UsingPeriod.NIGHT);
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 20, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(90), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void testFamilyDiscount() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.FAMILY);
    	t.setUsingPeriod(UsingPeriod.NIGHT);
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 2, 100);
		TicketUserDTO deca = new TicketUserDTO( UserType.CHILD, 2, 100);
		ticketUsers.add(odrasli);
		ticketUsers.add(deca);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(85), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void testCalculatingBillNoPrivileges() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.SINGLE);
    	t.setUsingPeriod(UsingPeriod.DAY);

		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 2, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(200), t.getBill());
		
		kieSession.dispose();
    }
    
	@Test
    public void testStudentAndLoyalty() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.SINGLE);
    	t.setUsingPeriod(UsingPeriod.DAY);
		
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(UserType.SENIOR, 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		privilege.add("Student 123");
		privilege.add("Loyalty 444");
		
		t.setPrivilege(privilege);
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		/*
		 * studentski popust 10%
		 * loyalty popust 15%
		 * 90+85+300+200
		 * =675
		 * 
		 */
		assertEquals(Double.valueOf(675), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void testStudentFamily() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.FAMILY);
    	t.setUsingPeriod(UsingPeriod.DAY);
		
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(UserType.CHILD, 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		privilege.add("Student 123");
		
		t.setPrivilege(privilege);
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		/*
		 * studentski popust 10%
		 *
		 * 90+400+200
		 * =690
		 * 
		 */
		assertEquals(Double.valueOf(690), t.getBill());
		
		kieSession.dispose();
    }
    
	@Test
    public void testLoyaltyFamily() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket(TypeTicket.FAMILY);
    	t.setUsingPeriod(UsingPeriod.DAY);
  
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(UserType.CHILD, 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		privilege.add("Loyalty 444");
		
		t.setPrivilege(privilege);
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(t);
		
		kieSession.fireAllRules();
		/*
		 * 
		 * loyalty popust 15%
		 * family discount 15%
		 * 7*100
		 * =700
		 * 700-15% = 595
		 * 595 -15% = 505.75
		 */
		assertEquals(Double.valueOf(505.75), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void regularGuest() {
		RegisteredUserDTO regUser =  new RegisteredUserDTO();
		Set<TicketsDTO> userTickets = new HashSet<>();
		MovieDTO movie = new MovieDTO(1L, "Titanic");
		
		// jedna kaarta
		TicketsDTO t = new TicketsDTO();
		t.setMovie(movie);
    	t.setTypeTicket(TypeTicket.FAMILY);
    	t.setUsingPeriod(UsingPeriod.DAY);
		
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(UserType.ADULT, 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(UserType.CHILD, 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		t.setPrivilege(privilege);
		
		//  karta broj dva
		TicketsDTO t2 = new TicketsDTO();
		t2.setPrivilege(new HashSet<String>());
		t2.setMovie(movie);
    	t2.setTypeTicket(TypeTicket.FAMILY);
    	t2.setUsingPeriod(UsingPeriod.DAY);
		
		t2.setBill(0);
		t2.setTicketUsers(ticketUsers);
		
		// karta br tri
		TicketsDTO t3 = new TicketsDTO();
		t3.setMovie(movie);
    	t3.setTypeTicket(TypeTicket.FAMILY);
    	t3.setUsingPeriod(UsingPeriod.DAY);
		
		t3.setBill(0);
		t3.setTicketUsers(ticketUsers);
		
		userTickets.add(t);
		userTickets.add(t2);
		userTickets.add(t3);
		
		regUser.setTickets(userTickets);
		
		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("regular_guest").setFocus();
		kieSession.insert(regUser);
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals("REGULAR_GUEST",  new ArrayList<String>(t.getPrivilege()).get(0));
		
		kieSession.dispose();
	}

	@Test
	public void testBulkChildTicketsDiscount() {
		
		TicketsDTO t = new TicketsDTO();
		t.setBill(1000);

		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO children = new TicketUserDTO(UserType.CHILD, 10, 100);
		ticketUsers.add(children);
		t.setTicketUsers(ticketUsers);

		KieSession kieSession = kieContainer.newKieSession("tickets-session");
		kieSession.getAgenda().getAgendaGroup("bill_type_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();

		// Expected bill: 1000 - 100 (10% of 1000) = 900
		assertEquals(Double.valueOf(900), t.getBill());

		kieSession.dispose();
	}


}
