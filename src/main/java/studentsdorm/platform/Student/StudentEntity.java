package studentsdorm.platform.Student;

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
public class StudentEntity extends StudentAbstract {

    private String name;
    private Long room;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "name", columnDefinition = "varchar(255) default 'defaultName'")
    public String getName() {
        return name;
    }

    @Column(name = "room")
    public Long getRoom() {
        return room;
    }
}
