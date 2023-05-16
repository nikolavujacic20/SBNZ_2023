package com.project.cinema.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieService {

	private final KieContainer kieContainer;
	
	private KieSession ruleSession;
	private KieSession cepSession;
	private KieSession insuranceRuleSession;
	
	@Autowired
	public KieService(KieContainer kieContainer) {
		super();
		this.kieContainer = kieContainer;
	}
	
	public KieSession getRuleSession() {
		if(this.ruleSession == null) {
			this.ruleSession = kieContainer.newKieSession("tickets-session");
		}
		return this.ruleSession;
	}
	
	public KieSession getInsuranceRuleSession() {
		if(this.insuranceRuleSession == null) {
			this.insuranceRuleSession = kieContainer.newKieSession("insurance-session");
		}
		return this.insuranceRuleSession;
	}
	
	public KieSession getCepSession()
	{
		if(this.cepSession == null) {
			this.cepSession = kieContainer.newKieSession("cepSession");
		}
		return this.cepSession;
	}
	public void disposeRuleSession() {
		this.ruleSession.dispose();
        this.ruleSession = null;
	}
	
	public void disposeInsuranceRuleSession() {
		this.insuranceRuleSession.dispose();
        this.insuranceRuleSession = null;
	}
	
	public void disposeCepSession() {
		this.cepSession.dispose();
        this.cepSession = null;
	}
}
