package studentsdorm.platform;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import studentsdorm.platform.Student.StudentController;
import studentsdorm.platform.Student.StudentService;

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
        mvc.perform(MockMvcRequestBuilders
                        .get("/students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
