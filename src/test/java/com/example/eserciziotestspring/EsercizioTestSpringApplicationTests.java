package com.example.eserciziotestspring;

import com.example.eserciziotestspring.controllers.StudentController;
import com.example.eserciziotestspring.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class EsercizioTestSpringApplicationTests {

	@Test
	void contextLoads() {
		assertThat(studentController).isNotNull();
	}

	@Autowired
	private StudentController studentController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Student createStudent() throws Exception {
		Student student = new Student();
		student.setName("Anna");
		student.setSurname("Simionato");
		student.setIsWorking(true);
		String studentJSON = objectMapper.writeValueAsString(student);
		MvcResult result = this.mockMvc.perform(post("/student/create-new-student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(studentJSON))
				.andExpect(status().isOk())
				.andReturn();
		return objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
	}

	@Test
	void createStudentTest () throws Exception {
		Student createStudents = createStudent();
		assertThat(createStudents.getId()).isNotNull();
	}
}
