package studentsdorm.system.Announcement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentsdorm.system.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    public List<Announcement> getAllAnnouncements() {
        return announcementService.getAllAnnouncements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable(value = "id") Long id) {
        Announcement announcement = announcementService.getAnnouncementById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found for this id :: " + id));
        return ResponseEntity.ok().body(announcement);
    }

    @PostMapping
    public Announcement createAnnouncement(@RequestBody Announcement announcement) {
        return announcementService.createAnnouncement(announcement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(@PathVariable(value = "id") Long announcementId,
                                                           @RequestBody Announcement announcementDetails) {
        Announcement updatedAnnouncement = announcementService.updateAnnouncement(announcementId, announcementDetails);
        return ResponseEntity.ok(updatedAnnouncement);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable(value = "id") Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.noContent().build();
    }
}
