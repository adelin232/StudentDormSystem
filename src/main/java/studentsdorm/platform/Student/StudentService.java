package studentsdorm.platform.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public StudentEntity getStudent(final Long id) {

        List<StudentEntity> students = studentRepo.findAll().stream().filter(studentEntity -> Objects.equals(studentEntity.getId(), id)).toList();

        if (students.size() != 1) {
            return null;
        } else {
            return students.get(0);
        }
    }
}
