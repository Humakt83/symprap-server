package fi.ukkosnetti.symprap.repository;

import org.springframework.data.repository.CrudRepository;

import fi.ukkosnetti.symprap.model.Disease;

public interface DiseaseRepository extends CrudRepository<Disease, Long> {

}
