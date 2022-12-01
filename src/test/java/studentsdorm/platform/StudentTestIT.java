package studentsdorm.platform;

import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.ContentCachingResponseWrapper;
import studentsdorm.platform.Student.Student;
import studentsdorm.platform.Student.StudentController;
import studentsdorm.platform.Student.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @DisplayName("Test GET PDF methods")
    public void testGetPdf() throws Exception {
        Response newResponse = new Response(256);
        newResponse.setCoyoteResponse(new org.apache.coyote.Response());
        HttpServletResponse response = new ContentCachingResponseWrapper(newResponse);
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        PDFGenerator generator = new PDFGenerator();
        generator.setStudentService(studentService);

        mvc.perform(MockMvcRequestBuilders
                        .get("/pdf/students")
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
