package studentsdorm.system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import studentsdorm.system.Student.Student;
import studentsdorm.system.Student.StudentRepo;
import studentsdorm.system.Student.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentTest {

	@InjectMocks
	StudentService studentService;

	@Mock
	StudentRepo studentRepo;

	@Test
	public void main() {
		SystemApplication.main(new String[] {});
	}

	@Test
	@DisplayName("Test getStudent Success")
	public void testGetStudentById() throws ExecutionException, InterruptedException {
		List<Student> students = new ArrayList<>();
		Student student = new Student();
		student.setId(1L);
		student.setName("Adelin");
//		student.setRoom(239L);
		student.setPhone("0747553042");
		student.setEmail("narcis.adelin.miulet@gmail.com");
//		student.setAvgGrade(8.56);
//		student.setEngCert(false);

		students.add(student);

		when(studentRepo.findById(1L)).thenReturn(Optional.ofNullable(students.get(0)));

		Assertions.assertNull(studentService.getStudent(1L));

		Student studentEntity = studentService.getStudentTest(1L);

		Assertions.assertEquals(student, studentEntity);
		Assertions.assertEquals(student.getRoom(), studentEntity.getRoom());
		Assertions.assertEquals(student.getPhone(), studentEntity.getPhone());
		Assertions.assertEquals(student.getEmail(), studentEntity.getEmail());
//		Assertions.assertEquals(student.getAvgGrade(), studentEntity.getAvgGrade());
//		Assertions.assertEquals(student.getEngCert(), studentEntity.getEngCert());
		verify(studentRepo, times(1)).findById(1L);
	}

	@Test
	@DisplayName("Test getStudentByName Success")
	public void testGetStudentByName() {
		List<Student> students = new ArrayList<>();
		Student student = new Student();

		student.setName("Adelin");
		students.add(student);

		when(studentRepo.findByName("Adelin")).thenReturn(students.get(0));

		Student studentEntity = studentService.getStudentByName("Adelin");

		Assertions.assertEquals(student.getName(), studentEntity.getName());
		verify(studentRepo, times(1)).findByName("Adelin");
	}

	@Test
	@DisplayName("Test getStudentByRoom Success")
	public void testGetStudentByRoom() {
		List<Student> students = new ArrayList<>();
		Student student = new Student();

//		student.setRoom(239L);
		students.add(student);

		when(studentRepo.findByRoom(239L)).thenReturn(students.get(0));

		Student studentEntity = studentService.getStudentByRoom(239L);

		Assertions.assertEquals(student.getRoom(), studentEntity.getRoom());
		verify(studentRepo, times(1)).findByRoom(239L);
	}

	@Test
	@DisplayName("Test getStudents Success")
	public void testGetStudents() {
		List<Student> students = new ArrayList<>();
//		Student student = new Student("Adelin", 239L, "0747553042", "narcis.adelin.miulet@gmail.com", 8.56, false);
		Student student = new Student("Adelin", "239", "0747553042", "narcis.adelin.miulet@gmail.com");
		studentService.createOrUpdateStudent(student); // just for Coverage (createStudent)

		students.add(student);

		when(studentRepo.findAll()).thenReturn(students);

		List<Student> studentList = studentService.getStudents();

		Assertions.assertEquals(1, studentList.size());
		verify(studentRepo, times(1)).findAll();
	}
}
