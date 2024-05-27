package studentsdorm.system.Announcement;

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
@Table(name = "ANNOUNCEMENTS", schema = "STUDENTDB")
public class Announcement {

    private Long id;
    private String title;
    private String description;
    private String date;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }
}
