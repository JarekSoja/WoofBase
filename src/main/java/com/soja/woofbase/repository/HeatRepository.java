package com.soja.woofbase.repository;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.Heat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HeatRepository extends CrudRepository<Heat, Long> {

    List<Heat> findAllByDogOwned(DogOwned dogOwned);
}
