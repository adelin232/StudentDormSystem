package studentsdorm.platform.Student;

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
@Table(name = "STUDENTS", schema = "STUDENTDB")
public class StudentEntity extends StudentAbstract {

    private String name;

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
}
