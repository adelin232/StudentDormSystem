package studentsdorm.system.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByName(final String name);

    @Query("SELECT s FROM Student s WHERE s.room = ?1")
    Student findByRoom(final String room);

    @Query("SELECT s FROM Student s WHERE s.userId = ?1")
    Student findByUserId(final String userId);
}
