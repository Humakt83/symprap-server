package fi.ukkosnetti.symprap.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
public class SymptomUpdate extends SymptomCreate {
	
	@Getter
	public final Long id;
	
	public SymptomUpdate(Long id, String symptom) {
		super(symptom);
		this.id = id;
	}
	
	@SuppressWarnings("unused")
	private SymptomUpdate() {
		this(null, null);
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
		SymptomUpdate other = (SymptomUpdate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
