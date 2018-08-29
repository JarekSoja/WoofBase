package com.soja.woofbase.repository;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.SupplementaryVaccination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SupplementaryVaccinationRepository extends CrudRepository<SupplementaryVaccination, Long> {

    List<SupplementaryVaccination> findAllByDogOwned(DogOwned dogOwned);
}
