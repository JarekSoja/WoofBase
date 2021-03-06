package com.soja.woofbase.repository;

import com.soja.woofbase.domain.Deworming;
import com.soja.woofbase.domain.DogOwned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DewormingRepository extends CrudRepository<Deworming, Long> {

    List<Deworming> findAllByDogOwned(DogOwned dogOwned);

}
