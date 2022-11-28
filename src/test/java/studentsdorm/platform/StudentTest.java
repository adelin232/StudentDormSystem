package studentsdorm.platform;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import studentsdorm.platform.Student.StudentAbstract;
import studentsdorm.platform.Student.StudentEntity;
import studentsdorm.platform.Student.StudentRepo;
import studentsdorm.platform.Student.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentTest {

	@InjectMocks
	StudentService studentService;

	@Mock
	StudentRepo studentRepo;

	@Test
	@DisplayName("Test getStudent Success")
	public void testGetStudentById() throws ExecutionException, InterruptedException {

		List<StudentEntity> students = new ArrayList<>();
		StudentEntity student = new StudentEntity();

		students.add(student);

		when(studentRepo.findById(1L)).thenReturn(Optional.ofNullable(students.get(0)));

		StudentEntity studentEntity = studentService.getStudent(1L);

		Assertions.assertEquals(student, studentEntity);
		verify(studentRepo, times(1)).findById(1L);
	}

	@Test
	@DisplayName("Test getStudentByName Success")
	public void testGetStudentByName() throws ExecutionException, InterruptedException {

		List<StudentEntity> students = new ArrayList<>();
		StudentEntity student = new StudentEntity("Adelin");

		students.add(student);

		when(studentRepo.findByName("Adelin")).thenReturn(students.get(0));

		StudentEntity studentEntity = studentService.getStudentByName("Adelin");

		Assertions.assertEquals(student, studentEntity);
		verify(studentRepo, times(1)).findByName("Adelin");
	}

	@Test
	@DisplayName("Test getStudents Success")
	public void testGetStudents() {

		List<StudentEntity> students = new ArrayList<>();
		StudentEntity student = new StudentEntity("Adelin");

		students.add(student);

		when(studentRepo.findAll()).thenReturn(students);

		List<StudentEntity> studentList = studentService.getStudents();

		Assertions.assertEquals(1, studentList.size());
		verify(studentRepo, times(1)).findAll();
	}
}
