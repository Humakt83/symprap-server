package fi.ukkosnetti.symprap.dto;

import lombok.Getter;
import lombok.ToString;
import fi.ukkosnetti.symprap.model.AnswerType;

@ToString
public class QuestionUpdate extends QuestionCreate {

	@Getter
	private final Long id;

	public QuestionUpdate(String question, AnswerType answerType, Long symptomId, Long id) {
		super(question, answerType, symptomId);
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
