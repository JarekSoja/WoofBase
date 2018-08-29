package com.soja.woofbase.repository;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.TickPrevention;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface TickPreventionRepository extends CrudRepository<TickPrevention, Long> {

    List<TickPrevention> findAllByDogOwned(DogOwned dogOwned);
}
