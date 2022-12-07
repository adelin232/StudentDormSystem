package studentsdorm.platform.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    private final static String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private final static String PHONE_PATTERN = "^\\d{10}$";

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

    public void createStudent(final Student student) {
        studentRepo.save(student);
    }

    public boolean isEmailCorrect(String email) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(email)
                .matches();
    }

    public boolean isPhoneCorrect(String phone) {
        return Pattern.compile(PHONE_PATTERN)
                .matcher(phone)
                .matches();
    }
}
