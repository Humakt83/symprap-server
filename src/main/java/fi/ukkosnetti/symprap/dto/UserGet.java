package fi.ukkosnetti.symprap.dto;

import java.util.Date;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class UserGet {
	
	private final Long id;
	
	private final String userName;
	
	private final String firstName;
	
	private final String lastName;
	
	private final Date dateOfBirth;
	
	private final Long medicalRecordNumber;

	public UserGet(Long id, String userName, String firstName, String lastName, Date dateOfBirth, Long medicalRecordNumber) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.medicalRecordNumber = medicalRecordNumber;
	}
	
	@SuppressWarnings("unused")
	private UserGet() {
		this(null, null, null, null, null, null);
	}

}
