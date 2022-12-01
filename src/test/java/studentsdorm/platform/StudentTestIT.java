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

    @Test
    @DisplayName("Test Controller methods")
    public void testControllerMethods() throws Exception {
        List<Student> students = new ArrayList<>();
        Student student = new Student("Adelin", 239L, "+40747553042", "narcis.adelin.miulet@gmail.com", 8.56, false);
        students.add(student);

        Mockito.when(studentService.getStudents()).thenReturn(students);
        mvc.perform(MockMvcRequestBuilders
                        .get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String url = "/students" + "/new";

        Gson gson = new Gson();
        String json = gson.toJson(student);

        mvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();
    }
}
