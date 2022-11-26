package studentsdorm.platform.Student;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@Component
public class StudentEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255) default 'defaultName'")
    private String name;
}
