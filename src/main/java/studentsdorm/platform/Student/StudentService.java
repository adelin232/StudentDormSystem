package studentsdorm.platform.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public StudentEntity getStudent(final Long id) throws ExecutionException, InterruptedException {

        return studentRepo.findById(id).orElse(null);
    }

    public List<StudentEntity> getStudents() {

        return studentRepo.findAll();
    }

    public void updateStudent(final Long id) throws ExecutionException, InterruptedException {

//        StudentEntity student = getStudent(id);
    }

    public StudentEntity getStudentByName(final String name) throws ExecutionException, InterruptedException {

        return studentRepo.findByName(name);
    }
}
