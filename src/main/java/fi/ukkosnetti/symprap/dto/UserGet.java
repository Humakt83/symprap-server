package fi.ukkosnetti.symprap.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.UserRole;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class UserGet {
	
	private final Long id;
	
	private final String userName;
	
	private final String firstName;
	
	private final String lastName;
	
	private final Date dateOfBirth;
	
	private final Long medicalRecordNumber;
	
	private final List<UserRole> roles;
	
	private final List<DiseaseGet> diseases;
	
	private final List<String> followers;
	
	private final List<String> followees;

	public UserGet(Long id, String userName, String firstName, String lastName, Date dateOfBirth, Long medicalRecordNumber, 
			List<UserRole> roles, List<DiseaseGet> diseases, List<String> followers, List<String> followees) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.medicalRecordNumber = medicalRecordNumber;
		this.roles = roles;
		this.diseases = diseases;
		this.followers = followers;
		this.followees = followees;
	}
	
	@SuppressWarnings("unused")
	private UserGet() {
		this(null, null, null, null, null, null, null, null, null, null);
	}

}
