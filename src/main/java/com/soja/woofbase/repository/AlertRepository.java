package com.soja.woofbase.repository;

import com.soja.woofbase.domain.Alert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AlertRepository extends CrudRepository<Alert, Long> {

}
