package studentsdorm.platform.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public StudentEntity getStudent(final Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public List<StudentEntity> getStudents() {
        return studentRepo.findAll();
    }

    public StudentEntity getStudentByName(final String name) {
        return studentRepo.findByName(name);
    }
}
