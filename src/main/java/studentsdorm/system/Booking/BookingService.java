package studentsdorm.system.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentsdorm.system.Student.Student;
import studentsdorm.system.Student.StudentService;

//import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private StudentService studentService;

//    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public List<Map<String, Object>> getAvailableHours(String wmNo) {
        List<Map<String, Object>> availableHours = new ArrayList<>();
//        LocalDateTime now = LocalDateTime.now();
//        int i = now.getHour();
//        i = Math.max(i, 8);

        for (int i = 8; i <= 21; i++) {
            String time = String.format("%02d:00", i);
            Optional<Booking> bookingOptional = bookingRepository.findByWmNo(wmNo).stream()
                    .filter(booking -> booking.getStartHour().equals(time))
                    .findFirst();
            Map<String, Object> hour = new HashMap<>();
            hour.put("time", time);
            hour.put("reserved", bookingOptional.isPresent());
            if (bookingOptional.isPresent()) {
                Booking booking = bookingOptional.get();
//                System.out.println(booking.getUserId());
                Student student = studentService.getStudentByUserId(booking.getUserId());
                if (student != null) {
                    hour.put("userName", student.getName());
//                    System.out.println(hour.get("userName"));
                    hour.put("userPhone", student.getPhone());
                }
            }
            availableHours.add(hour);
        }
        return availableHours;
    }

    public boolean canBook(String userId, String wmNo, String startHour) {
        boolean userHasBooking = bookingRepository.findByUserId(userId).stream()
                .anyMatch(booking -> (booking.getWmNo().equals(wmNo) && !booking.getStartHour().equals(startHour)));
        if (userHasBooking) {
            return false;
        }
        return bookingRepository.findByWmNo(wmNo).stream()
                .noneMatch(booking -> booking.getStartHour().equals(startHour));
    }

    public Booking createBooking(String userId, String wmNo, String startHour) {
        if (canBook(userId, wmNo, startHour)) {
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setWmNo(wmNo);
            booking.setStartHour(startHour);
            return bookingRepository.save(booking);
        } else {
            return null;
        }
    }

//    public List<Booking> readBookings(final String userId) {
//        return bookingRepository.findAllByUserId(userId);
//    }

    public List<Booking> readAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
