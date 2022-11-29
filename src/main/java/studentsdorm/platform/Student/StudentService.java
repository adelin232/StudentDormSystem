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

    public Student getStudent(final Long id) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Student>> future = CompletableFuture.supplyAsync(this::getStudents);

        List<Student> students = future.get().stream().filter(studentEntity -> Objects.equals(studentEntity.getId(), id)).toList();

        return students.size() == 1 ? students.get(0) : null;
    }

    public Student getStudentTest(final Long id) {
        return studentRepo.findById(id).orElseThrow();
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentByName(final String name) {
        return studentRepo.findByName(name);
    }

    public Student getStudentByRoom(final Long room) {
        return studentRepo.findByRoom(room);
    }
}
