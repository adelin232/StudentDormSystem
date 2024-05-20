package studentsdorm.system.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BookingScheduler {

    @Autowired
    private BookingService bookingService;

    @Scheduled(fixedRate = 3600000)
    public void runExpiredBookingDeletion() {
        bookingService.deleteExpiredBookings();
    }
}
