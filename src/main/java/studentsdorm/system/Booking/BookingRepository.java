package studentsdorm.system.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByWmNo(String wmNo);

    List<Booking> findBookingsByUserId(String userId);
}
