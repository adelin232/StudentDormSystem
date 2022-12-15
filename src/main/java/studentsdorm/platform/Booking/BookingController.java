package studentsdorm.platform.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/new_booking")
    @Transactional
    public String createBooking(Model model) {
        model.addAttribute("bookingForm", new Booking());
        return "new_booking";
    }

    @PostMapping("/new_booking")
    @Transactional
    public String createBooking(Model model, @ModelAttribute("bookingForm") Booking booking) {
        bookingService.createBooking(booking);
        model.addAttribute("bookingForm", booking);
        return "new_booking";
    }
}
