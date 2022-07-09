package com.luisfelipedejesusm.eRegistrarWebAPI;

import ch.qos.logback.core.net.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.luisfelipedejesusm.eRegistrarWebAPI.application.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.ZoneId;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ERegistrarWebApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private final Faker faker = new Faker();

	private static final String PATH = "/eregistrar/api/student/";

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldGetListOfAllStudents() throws Exception {
		mockMvc.perform(get(PATH + "list"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSaveNewStudent() throws Exception {
		Student student = new Student();
		student.setStudentNumber(faker.idNumber().valid());
		student.setCgpa(faker.number().randomDouble(2, 0, 4));
		student.setDateOfEnrollment(faker.date().birthday());
		student.setFirstName(faker.name().firstName());
		student.setLastName(faker.name().lastName());
		student.setIsInternational(faker.bool().bool());

		mockMvc.perform(post(PATH + "register")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json(student)))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFindStudentWithId() throws Exception {
		mockMvc.perform(get(PATH + "get/1"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void shouldNotFindStudentWithId() throws Exception {
		mockMvc.perform(get(PATH + "get/-5"))
				.andDo(print())
				.andExpect(status().isNoContent());
	}

	@Test
	public void shouldDeleteSomeStudent() throws Exception {
		Student student = new Student();
		student.setStudentNumber(faker.idNumber().valid());
		student.setCgpa(faker.number().randomDouble(2, 0, 4));
		student.setDateOfEnrollment(faker.date().birthday());
		student.setFirstName(faker.name().firstName());
		student.setLastName(faker.name().lastName());
		student.setIsInternational(faker.bool().bool());

		MvcResult response = mockMvc.perform(post(PATH + "register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(student)))
				.andReturn();

		String id = response.getResponse().getContentAsString();


		mockMvc.perform(get(PATH + "delete/" + id))
				.andDo(print())
				.andExpect(status().isOk());
	}

	private static String json(Object o){
		try{
			return new ObjectMapper().writeValueAsString(o);
		}catch (Exception e){
			throw new RuntimeException(e.getMessage());
		}
	}

}
