package com.soja.woofbase.repository;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.VetVisit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface VetVisitRepository extends CrudRepository<VetVisit, Long> {

    List<VetVisit> findAllByDogOwned(DogOwned dogOwned);
}
