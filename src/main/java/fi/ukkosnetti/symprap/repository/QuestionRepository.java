package fi.ukkosnetti.symprap.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fi.ukkosnetti.symprap.model.Disease;
import fi.ukkosnetti.symprap.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

	List<Question> findByDisease(Disease disease);
}
