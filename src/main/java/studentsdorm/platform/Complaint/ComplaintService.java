package studentsdorm.platform.Complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    public void createComplaint(final Complaint complaint) {
        complaintRepo.save(complaint);
    }

//    public List<Complaint> readBookings(final String userId) {
//        System.out.println(complaintRepo.findAllByUserId(userId));
//        return complaintRepo.findAllByUserId(userId);
//    }
}
