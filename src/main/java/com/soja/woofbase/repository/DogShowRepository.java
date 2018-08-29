package com.soja.woofbase.repository;

import com.soja.woofbase.domain.DogOwned;
import com.soja.woofbase.domain.DogShow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DogShowRepository extends CrudRepository<DogShow, Long> {

    List<DogShow> findAllByDogOwned(DogOwned dogOwned);
}
