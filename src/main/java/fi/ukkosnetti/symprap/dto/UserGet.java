package fi.ukkosnetti.symprap.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.UserRole;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGet {
	
	public final Long id;
	
	public final String userName;
	
	public final String firstName;
	
	public final String lastName;
	
	public final Date dateOfBirth;
	
	public final Long medicalRecordNumber;
	
	public final List<UserRole> roles;
	
	public final List<DiseaseGet> diseases;
	
	public final List<String> followers;
	
	public final List<String> followees;

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
