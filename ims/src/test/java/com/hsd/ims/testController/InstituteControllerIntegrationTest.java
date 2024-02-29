package com.hsd.ims.testController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class InstituteControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetInstituteById() throws Exception {
		Long instituteId = 1L;

		mockMvc.perform(MockMvcRequestBuilders.get("/api/institutes/getById/{id}", instituteId))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(instituteId));
	}
	
	@Test
	void testCreateInstitute() throws Exception {
		String instituteJson = "{\"name\":\"Test Institute\",\"location\":\"Test Location\",\"contact\":\"Test Contact\"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/institutes/createInstitute").content(instituteJson)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void testUpdateInstitute() throws Exception {
		Long instituteId = 1L;
		String updatedInstituteJson = "{\"name\":\"Updated Institute\",\"location\":\"Updated Location\",\"contact\":\"Updated Contact\"}";

		mockMvc.perform(MockMvcRequestBuilders.put("/api/institutes/update/{id}", instituteId)
				.content(updatedInstituteJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Updated Institute"));
	}
}
