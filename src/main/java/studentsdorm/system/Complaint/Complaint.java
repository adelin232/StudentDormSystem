package studentsdorm.system.Complaint;

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
@Table(name = "COMPLAINTS", schema = "STUDENTDB")
public class Complaint {

    private long id;
    private String userId;
    private String subject;
    private String description;

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

    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
}
