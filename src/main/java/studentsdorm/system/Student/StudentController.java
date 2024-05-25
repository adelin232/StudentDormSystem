package studentsdorm.system.Student;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentsdorm.system.PDFGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping("/create")
    public ResponseEntity<?> createOrUpdateStudent(@RequestBody Student student) throws ExecutionException, InterruptedException {
        Optional<Student> existingStudent = Optional.ofNullable(studentService.getStudent(student.getUserId()));
        if (existingStudent.isPresent()) {
            studentService.createOrUpdateStudent(student);
            return ResponseEntity.ok("Profilul a fost actualizat.");
        } else {
            studentService.createOrUpdateStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profilul a fost creat.");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getStudentProfile(@RequestParam String userId) throws ExecutionException, InterruptedException {
        Optional<Student> studentOpt = Optional.ofNullable(studentService.getStudent(userId));
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profilul nu a fost găsit.");
        }
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
