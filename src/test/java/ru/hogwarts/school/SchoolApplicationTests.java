package ru.hogwarts.school;

import net.minidev.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	public class SchoolApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	void contextLoadsStudentControllerTest() throws Exception {
		Assertions.assertThat(studentController).isNotNull();
	}

	@Test
	void contextLoadsFacultyControllerTest() throws Exception {
		Assertions.assertThat(facultyService).isNotNull();
	}

	@Test
	public void postAddStudentTest() throws Exception {
		Student student = new Student();
		student.setName("Boby");
		student.setAge(15);
		Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
				.isNotNull();

	}


	@WebMvcTest
	public class StudentControllerTest {

		@Autowired
		private MockMvc mockMvc;

		@MockBean
		private StudentRepository studentRepository;
		@MockBean
		private AvatarRepository avatarRepository;
		@SpyBean
		private StudentService studentService;
		@InjectMocks
		private StudentController studentController;

		public StudentControllerTest(StudentController studentController) {
			this.studentController = studentController;
		}

		@Test
		public void saveStudentTest() throws Exception {

			final long id = 1;
			final String name = "Garry";
			final int age = 14;


			JSONObject studentObject = new JSONObject();
			studentObject.put("name", name);
			studentObject.put("age", age);

			Student student = new Student();
			student.setId(1L);
			student.setName(name);
			student.setAge(age);

			when(studentRepository.save(any(Student.class))).thenReturn(student);
			when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

			mockMvc.perform(MockMvcRequestBuilders
							.post("student")
							.content(studentObject.toString())
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id").value(id))
					.andExpect(jsonPath("$.name").value(name))
					.andExpect(jsonPath("$.age").value(age));

			mockMvc.perform(MockMvcRequestBuilders
							.get("student/ + id")
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id").value(id))
					.andExpect(jsonPath("$.name").value(name))
					.andExpect(jsonPath("$.age").value(age));
		}


	}
}
