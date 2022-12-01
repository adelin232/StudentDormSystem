package studentsdorm.platform;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import studentsdorm.platform.Student.Student;
import studentsdorm.platform.Student.StudentController;
import studentsdorm.platform.Student.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentTestIT {

    @Autowired
    MockMvc mvc;

    @MockBean
    StudentService studentService;

    Student student = new Student("Adelin", 239L, "+40747553042", "narcis.adelin.miulet@gmail.com", 8.56, false);

    @Test
    @DisplayName("Test GET Student methods")
    public void testGetStudent() throws Exception {
        Student newStudent = new Student();
        student.setId(1L);

        Mockito.when(studentService.getStudent(1L)).thenReturn(newStudent);
        mvc.perform(MockMvcRequestBuilders
                        .get("/students/new")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test GET Students methods")
    public void testGetStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(student);

        Mockito.when(studentService.getStudents()).thenReturn(students);
        mvc.perform(MockMvcRequestBuilders
                        .get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test POST Student methods")
    public void testPostStudent() throws Exception {
        String url2 = "/students" + "/new";

        Gson gson = new Gson();
        String json = gson.toJson(student);

        mvc.perform(MockMvcRequestBuilders
                        .post(url2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("Test POST Students methods")
    public void testPostStudents() throws Exception {
        String url1 = "/students";

        mvc.perform(MockMvcRequestBuilders
                        .post(url1))
                .andExpect(status().is3xxRedirection())
                .andReturn();
    }
}
