package studentsdorm.system.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentsdorm.system.Log;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
//import java.util.regex.Pattern;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

//    private final static String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
//            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
//
//    private final static String PHONE_PATTERN = "^\\d{10}$";

    public Student getStudent(final Long id) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Student>> future = CompletableFuture.supplyAsync(this::getStudents);

        List<Student> students = future.get().stream().filter(studentEntity -> Objects.equals(studentEntity.getId(), id)).toList();

        return students.size() == 1 ? students.get(0) : null;
    }

    public Student getStudentTest(final Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Log
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByUserId(final String userId) {
        return studentRepository.findByUserId(userId);
    }

    public Student getStudentByName(final String name) {
        return studentRepository.findByName(name);
    }

    public Student getStudentByRoom(final String room) {
        return studentRepository.findByRoom(room);
    }

    @Log
    public void createOrUpdateStudent(final Student student) {
        Student existingStudent = studentRepository.findByUserId(student.getUserId());

        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setRoom(student.getRoom());
            studentRepository.save(existingStudent);
        } else {
            studentRepository.save(student);
        }
    }

//    public boolean isEmailCorrect(String email) {
//        return Pattern.compile(EMAIL_PATTERN)
//                .matcher(email)
//                .matches();
//    }
//
//    public boolean isPhoneCorrect(String phone) {
//        return Pattern.compile(PHONE_PATTERN)
//                .matcher(phone)
//                .matches();
//    }
}
