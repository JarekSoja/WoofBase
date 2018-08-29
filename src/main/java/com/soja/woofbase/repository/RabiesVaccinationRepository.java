package com.soja.woofbase.repository;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.RabiesVaccination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RabiesVaccinationRepository extends CrudRepository<RabiesVaccination, Long> {

    List<RabiesVaccination> findAllByDogOwned(DogOwned dogOwned);
}
