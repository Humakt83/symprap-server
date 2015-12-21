package fi.ukkosnetti.symprap.dto;

public class DiseaseUpdate extends DiseaseCreate {
	
	public final Long id;
	
	public DiseaseUpdate(Long id, String disease) {
		super(disease);
		this.id = id;
	}
	
	@SuppressWarnings("unused")
	private DiseaseUpdate() {
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
		DiseaseUpdate other = (DiseaseUpdate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
