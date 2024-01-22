package studentsdorm.platform.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    public void createBooking(final Booking booking) {
        bookingRepo.save(booking);
    }

    public List<Booking> readBookings(final String userId) {
        System.out.println(bookingRepo.findAllByUserId(userId));
        return bookingRepo.findAllByUserId(userId);
    }
}
