package com.hsd.ims.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsd.ims.model.Institute;

@Repository
public interface InstitutionRepository extends JpaRepository<Institute, Long>{

}
