package studentsdorm.system.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentsdorm.system.Log;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student getStudent(final String id) throws ExecutionException, InterruptedException {
        CompletableFuture<List<Student>> future = CompletableFuture.supplyAsync(this::getStudents);
        List<Student> students = future.get().stream().filter(studentEntity -> Objects.equals(studentEntity.getUserId(), id)).toList();
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
            existingStudent.setPhone(student.getPhone());
            studentRepository.save(existingStudent);
        } else {
            studentRepository.save(student);
        }
    }
}
