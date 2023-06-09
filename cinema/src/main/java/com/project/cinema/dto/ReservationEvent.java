package com.project.cinema.dto;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;



@Role(Role.Type.EVENT)
@Timestamp("executionTime")
public class ReservationEvent implements Serializable {

	private static final long serialVersionUID = 1L;
    private Date executionTime;
    private String username;  
    
	public ReservationEvent() {
		super();
	}

	public ReservationEvent(String username) {
		super();
		this.executionTime = new Date();
        this.username = username;
	}

	public ReservationEvent(Date executionTime, String username) {
		super();
		this.executionTime = executionTime;
		this.username = username;
	}

	public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
    
}
