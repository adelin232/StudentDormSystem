package studentsdorm.system.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/available-hours")
    public List<Map<String, Object>> getAvailableHours(@RequestParam String wmNo) {
        return bookingService.getAvailableHours(wmNo);
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        String wmNo = request.get("wmNo");
        String startHour = request.get("startHour");
        Booking newBooking = bookingService.createBooking(userId, wmNo, startHour);
        if (newBooking != null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Booking>> readAllBookings() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(bookingService.readAllBookings());
    }
}
