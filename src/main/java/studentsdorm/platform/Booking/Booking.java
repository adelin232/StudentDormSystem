package studentsdorm.platform.Booking;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "BOOKINGS", schema = "STUDENTDB")
public class Booking {

    private long id;
    private long wmNo; // Washing Machine Number (1 or 2)
    private String startDate;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "wmNo")
    public Long getWmNo() {
        return wmNo;
    }

    @Column(name = "startDate")
    public String getStartDate() {
        return startDate;
    }
}