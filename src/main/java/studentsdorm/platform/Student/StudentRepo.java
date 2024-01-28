package studentsdorm.platform.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByName(final String name);

    @Query("SELECT s FROM Student s WHERE s.room = ?1")
    Student findByRoom(final Long room);

    Optional<Student> findStudentByUserId(final String userId);
}
