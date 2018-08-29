package com.soja.woofbase.repository;

import com.soja.woofbase.domain.Certification;
import com.soja.woofbase.domain.DogOwned;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CertificationRepository extends CrudRepository<Certification, Long> {

    List<Certification> findAllByDogOwned(DogOwned dogOwned);
}
