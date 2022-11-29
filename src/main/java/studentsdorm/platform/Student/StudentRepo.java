package studentsdorm.platform.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Long> {

    StudentEntity findByName(final String name);

    @Query("SELECT s FROM StudentEntity s WHERE s.room = ?1")
    StudentEntity findByRoom(final Long room);
}
