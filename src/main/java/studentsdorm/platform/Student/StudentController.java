package studentsdorm.platform.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
