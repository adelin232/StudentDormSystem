package studentsdorm.system.Student;

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
@Table(name = "STUDENTS", schema = "STUDENTDB")
public class Student extends StudentAbstract {

    private String userId;
    private String name;
    private String room;
    private String phone;
    private String email;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "user_id", columnDefinition = "varchar(255) default 'defaultUserId'")
    public String getUserId() {
        return userId;
    }

    @Column(name = "name", columnDefinition = "varchar(255) default 'defaultName'")
    public String getName() {
        return name;
    }

    @Column(name = "room", columnDefinition = "varchar(255) default 'defaultRoom'")
    public String getRoom() {
        return room;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }
}
