package fi.ukkosnetti.symprap.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import fi.ukkosnetti.symprap.model.UserRole;

@ToString
public class UserUpdate extends UserCreate {
	
	@Getter
	private final Long id;
	
	public UserUpdate(String userName, String password, String firstName, String lastName, Date dateOfBirth, Long medicalRecordNumber, List<UserRole> userRoles,
			List<DiseaseGet> diseases, Long id) {
		super(userName, password, firstName, lastName, dateOfBirth, medicalRecordNumber, userRoles, diseases);
		this.id = id;
	}

	@SuppressWarnings("unused")
	private UserUpdate() {
		this(null, null, null, null, null, null, new ArrayList<>(), new ArrayList<>(), null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserUpdate other = (UserUpdate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
