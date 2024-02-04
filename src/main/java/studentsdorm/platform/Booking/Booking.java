package studentsdorm.platform.Booking;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKINGS", schema = "STUDENTDB")
public class Booking {

    private long id;
    private String userId;
    private String wmNo; // Washing Machine Number
    private String startHour;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    @Column(name = "wmNo")
    public String getWmNo() {
        return wmNo;
    }

    @Column(name = "startHour")
    public String getStartHour() {
        return startHour;
    }
}
