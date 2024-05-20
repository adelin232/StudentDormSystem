package studentsdorm.system.Complaint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Complaint complaint) {
        complaintService.createComplaint(complaint);
        return ResponseEntity.ok("Complaint created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> readComplaints() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(complaintService.readComplaints());
    }
}
