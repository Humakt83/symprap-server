package fi.ukkosnetti.symprap.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fi.ukkosnetti.symprap.model.UserRole;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserCreate {

	public final String userName;
	
	public final String password;
	
	public final String firstName;
	
	public final String lastName;
	
	public final Date dateOfBirth;
	
	public final Long medicalRecordNumber;
	
	public final List<UserRole> roles;
	
	public final List<DiseaseGet> diseases;

	public UserCreate(String userName, String password, String firstName, String lastName, Date dateOfBirth, Long medicalRecordNumber, List<UserRole> userRoles, List<DiseaseGet> diseases) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.medicalRecordNumber = medicalRecordNumber;
		this.roles = userRoles;
		this.diseases = diseases;
		this.password = password;
	}
	
	
	@SuppressWarnings("unused")
	private UserCreate() {
		this(null, null, null, null, null, null, new ArrayList<>(), new ArrayList<>());
	}
	
	
}
