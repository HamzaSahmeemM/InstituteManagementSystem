package com.hsd.ims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsd.ims.dao.InstitutionRepository;
import com.hsd.ims.model.Institute;

@Service
public class InstituteService {
	
	@Autowired
    private InstitutionRepository institutionRepository;
	
	public Institute saveInstitute(Institute institute) {
        return institutionRepository.save(institute);
    }

    public Institute getInstituteById(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }
}
