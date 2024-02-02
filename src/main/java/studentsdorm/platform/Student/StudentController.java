package studentsdorm.platform.Student;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studentsdorm.platform.PDFGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Student student) {
        studentService.createOrUpdateStudent(student);
        return ResponseEntity.ok("Student created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Student>> readStudents() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(studentService.getStudents());
    }

    @GetMapping("/pdf/students")
    @Transactional
    public void generateReportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        PDFGenerator generator = new PDFGenerator();
        generator.setStudents(studentService.getStudents());
        generator.generate(response);
    }
}
