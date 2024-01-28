package studentsdorm.platform.Complaint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Long> {

//    List<Complaint> findAllByUserId(String userId);
}
