package com.project.cinema;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.cinema.dto.LoginEvent;
import com.project.cinema.dto.ReservationEvent;

public class CEPRulesTests {
	


	@Test
    public void testLoginCEPRules() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.main", "cinema-drools", "0.0.1-SNAPSHOT"));

	    KieSession session = kContainer.newKieSession("cepSession");
	    session.getAgenda().getAgendaGroup("login").setFocus();

	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    int firedRules = session.fireAllRules();
        session.dispose();

	    assertEquals(1, firedRules);
    }
	
}
