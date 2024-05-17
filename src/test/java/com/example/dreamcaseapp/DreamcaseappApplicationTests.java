package com.example.dreamcaseapp;

import com.example.dreamcaseapp.model.Case;
import com.example.dreamcaseapp.repository.CaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DreamCaseAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CaseRepository caseRepository;

	private Case testCase;

	@BeforeEach
	void setUp() {
		caseRepository.deleteAll();
		testCase = new Case();
		testCase.setTitle("Initial Title");
		testCase.setDescription("Initial Description");
		testCase.setCreationDate(LocalDateTime.now());
		testCase.setLastUpdateDate(LocalDateTime.now());
		testCase = caseRepository.save(testCase);
	}

	@Test
	void testCreateCase() throws Exception {
		Case newCase = new Case();
		newCase.setTitle("New Case");
		newCase.setDescription("Description of new case");

		mockMvc.perform(post("/cases")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(newCase)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("New Case"))
				.andExpect(jsonPath("$.description").value("Description of new case"));
	}

	@Test
	void testGetCase() throws Exception {
		Case savedCase = new Case();
		savedCase.setTitle("Saved Case");
		savedCase.setDescription("Description of saved case");
		savedCase.setCreationDate(LocalDateTime.now());
		savedCase = caseRepository.save(savedCase);

		mockMvc.perform(get("/cases/" + savedCase.getCaseId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Saved Case"))
				.andExpect(jsonPath("$.description").value("Description of saved case"));
	}

	@Test
	void testUpdateCase() throws Exception {
		Case updatedCase = new Case();
		updatedCase.setTitle("Updated Title");
		updatedCase.setDescription("Updated Description");

		mockMvc.perform(put("/cases/" + testCase.getCaseId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(updatedCase)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Updated Title"))
				.andExpect(jsonPath("$.description").value("Updated Description"))
				.andExpect(jsonPath("$.caseId").value(testCase.getCaseId()))
				.andExpect(jsonPath("$.lastUpdateDate").exists());
	}

	@Test
	void testDeleteCase() throws Exception {
		mockMvc.perform(delete("/cases/" + testCase.getCaseId()))
				.andExpect(status().isNoContent());

		mockMvc.perform(get("/cases/" + testCase.getCaseId()))
				.andExpect(status().isNotFound());
	}
}
