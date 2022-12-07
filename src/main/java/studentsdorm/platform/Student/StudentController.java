package studentsdorm.platform.Student;

import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studentsdorm.platform.PDFGenerator;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    private Boolean wrongEmail = Boolean.FALSE;
    private Boolean wrongPhone = Boolean.FALSE;

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
    @Transactional
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

    @GetMapping("/new_student")
    @Transactional
    public String createStudent(Model model) {
        if (wrongEmail) {
            model.addAttribute("wrongEmail", new Object());
            wrongEmail = false;
        }

        if (wrongPhone) {
            model.addAttribute("wrongPhone", new Object());
            wrongPhone = false;
        }

        model.addAttribute("studentForm", new Student());

        return "new_student";
    }

    @PostMapping("/new_student")
    @Transactional
    public String createStudent(Model model, @ModelAttribute("studentForm") Student studentForm) {
        if (!studentService.isEmailCorrect(studentForm.getEmail())) {
            wrongEmail = true;
            return "redirect:new_student";
        }

        if (!studentService.isPhoneCorrect(studentForm.getPhone())) {
            wrongPhone = true;
            return "redirect:new_student";
        }

        studentService.createStudent(studentForm);
        model.addAttribute("studentForm", studentForm);

        return "new_student";
    }
}
