package fi.ukkosnetti.symprap.dto;

import fi.ukkosnetti.symprap.model.AnswerType;

public class QuestionUpdate extends QuestionCreate {

	public final Long id;

	public QuestionUpdate(String question, AnswerType answerType, Long diseaseId, Long id) {
		super(question, answerType, diseaseId);
		this.id = id;
	}
	
	@SuppressWarnings("unused")
	private QuestionUpdate() {
		this(null, null, null, null);
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
		QuestionUpdate other = (QuestionUpdate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
