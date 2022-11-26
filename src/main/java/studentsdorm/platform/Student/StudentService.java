package studentsdorm.platform.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public StudentEntity getStudent(final Long id) throws ExecutionException, InterruptedException {

        CompletableFuture<List<StudentEntity>> future = CompletableFuture.supplyAsync(this::getStudents);

        List<StudentEntity> students = future.get().stream().filter(studentEntity -> Objects.equals(studentEntity.getId(), id)).toList();

        return students.size() == 1 ? students.get(0) : null;
    }

    public List<StudentEntity> getStudents() {

        return studentRepo.findAll();
    }

    public void updateStudent(final Long id) throws ExecutionException, InterruptedException {

//        StudentEntity student = getStudent(id);
    }
}
