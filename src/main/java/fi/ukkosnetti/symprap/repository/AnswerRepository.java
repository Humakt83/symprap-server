package fi.ukkosnetti.symprap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.symprap.model.Answer;
import fi.ukkosnetti.symprap.model.Question;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

	List<Answer> findByQuestion(Question question);
}
