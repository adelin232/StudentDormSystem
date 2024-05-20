package studentsdorm.system.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
        bookingService.createBooking(booking);
        return ResponseEntity.ok("Booking created successfully");
    }

    @GetMapping
    public ResponseEntity<List<Booking>> readBookings(@RequestParam String userId) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(bookingService.readBookings(userId));
    }
}
