package studentsdorm.platform.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public void createBooking(final Booking booking) {
        bookingRepo.save(booking);
    }
}
