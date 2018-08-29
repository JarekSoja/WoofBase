package com.soja.woofbase.repository;

import com.soja.woofbase.domain.Dog;
import com.soja.woofbase.domain.DogOwned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DogOwnedRepository extends CrudRepository<DogOwned, Long> {

    List<Dog> getAllByDogParent(Dog dog);
}
