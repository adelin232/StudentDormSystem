package studentsdorm.platform.Student;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class StudentEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
}
