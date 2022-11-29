package studentsdorm.platform.Student;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import studentsdorm.platform.PDFGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{id}")
    @ResponseBody
    public String getStudent(@PathVariable final Long id) throws ExecutionException, InterruptedException {
        return studentService.getStudent(id).getName();
    }

    @GetMapping("/students")
    @Transactional
    public String getStudents(Model model) {
        List<Student> students = studentService.getStudents();

        model.addAttribute("allStudentsForm", students);

        return "students";
    }

    @PostMapping("/students")
    @Transactional
    public String getStudentss(Model model) {
        List<Student> payments = studentService.getStudents();

        model.addAttribute("allStudentsForm", payments);

        return "redirect:pdf/students";
    }

    @GetMapping("/pdf/students")
    public void generateReportPdf(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        PDFGenerator generator = new PDFGenerator();
        generator.setStudentService(studentService);
        generator.generate(response);
    }

    @PostMapping("/student/new")
    public String createStudent() {
        return "redirect:students";
    }
}
