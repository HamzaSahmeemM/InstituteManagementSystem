package com.hsd.ims.testController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hsd.ims.controller.InstituteController;
import com.hsd.ims.model.Institute;
import com.hsd.ims.service.InstituteService;

@ExtendWith(MockitoExtension.class)
public class InstituteControllerTest {
	
	@Mock
    private InstituteService instituteService;
	
	@InjectMocks
    private InstituteController instituteController;
	
	@Test
    void testCreateInstitute() {
        Institute inputInstitute = new Institute(); // create a test Institute object
        when(instituteService.saveInstitute(any())).thenReturn(inputInstitute);

        ResponseEntity<Institute> responseEntity = instituteController.createInstitute(inputInstitute);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(inputInstitute, responseEntity.getBody());

        verify(instituteService, times(1)).saveInstitute(any());
    }
	
	@Test
    void testGetInstitute() {
        Long instituteId = 1L;
        Institute mockedInstitute = new Institute(); 

        when(instituteService.getInstituteById(instituteId)).thenReturn(mockedInstitute);

        ResponseEntity<Institute> responseEntity = instituteController.getInstitute(instituteId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockedInstitute, responseEntity.getBody());

        verify(instituteService, times(1)).getInstituteById(instituteId);
    }
	
	@Test
    void testUpdateInstitute() {
        Long instituteId = 1L;
        Institute existingInstitute = new Institute(); 
        Institute updatedInstitute = new Institute(); 
        
        when(instituteService.getInstituteById(instituteId)).thenReturn(existingInstitute);
        when(instituteService.saveInstitute(any())).thenReturn(updatedInstitute);

        ResponseEntity<Institute> responseEntity = instituteController.updateInstitute(instituteId, updatedInstitute);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedInstitute, responseEntity.getBody());

        verify(instituteService, times(1)).getInstituteById(instituteId);
        verify(instituteService, times(1)).saveInstitute(existingInstitute);
    }

}
