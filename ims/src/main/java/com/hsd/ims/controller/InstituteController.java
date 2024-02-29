package com.hsd.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsd.ims.model.Institute;
import com.hsd.ims.service.InstituteService;

@RestController
@RequestMapping("/api/institutes")
public class InstituteController {
	
	@Autowired
	private InstituteService instituteService;
	
	/**
	 * Create new institute
	 * @param institute
	 * @return
	 */
	
	@PostMapping("/createInstitute")
    public ResponseEntity<Institute> createInstitute(@RequestBody Institute institute) {
        Institute savedInstitute = instituteService.saveInstitute(institute);
        return ResponseEntity.ok(savedInstitute);
    }
	
	/**
	 * Get institute by idea
	 * @param id
	 * @return
	 */
	@GetMapping("/getById/{id}")
    public ResponseEntity<Institute> getInstitute(@PathVariable Long id) {
        Institute institute = instituteService.getInstituteById(id);
        if (institute == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(institute);
    }
	
	/**
	 * Update institute 
	 * @param id
	 * @param updatedInstitute
	 * @return
	 */
	@PutMapping("/update/{id}")
    public ResponseEntity<Institute> updateInstitute(@PathVariable Long id, @RequestBody Institute updatedInstitute) {
		
        Institute existingInstitute = instituteService.getInstituteById(id);
        if (existingInstitute == null) {
            return ResponseEntity.notFound().build();
        }

        existingInstitute.setName(updatedInstitute.getName() != null ? updatedInstitute.getName() : existingInstitute.getName());
        existingInstitute.setLocation(updatedInstitute.getLocation() != null ? updatedInstitute.getLocation() : existingInstitute.getLocation());
        existingInstitute.setContact(updatedInstitute.getContact() != null ? updatedInstitute.getContact() : existingInstitute.getContact());

        Institute savedInstitute = instituteService.saveInstitute(existingInstitute);
        return ResponseEntity.ok(savedInstitute);
    }
}
