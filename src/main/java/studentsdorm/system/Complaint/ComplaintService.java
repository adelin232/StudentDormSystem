package studentsdorm.system.Complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    public void createComplaint(final Complaint complaint) {
        complaintRepository.save(complaint);
    }

    public List<Complaint> readComplaints() {
        return complaintRepository.findAll();
    }

    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
