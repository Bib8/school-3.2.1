package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import static org.springframework.boot.test.context.SpringBootTest.*;

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
	public void somethingTest() throws Exception {
		Assertions.assertThat(this.restTemplate.getForObject("http:localhost:" + port + "/students", String.class))
				.isNotEmpty();
	}

	@Test
	public void postAddStudentTest() throws Exception {
		Student student = new Student();
		student.setName("Boby");
		student.setAge(15);
		Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
				.isNotNull();
	}

}
