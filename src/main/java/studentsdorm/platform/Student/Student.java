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
import javax.validation.Valid;

@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STUDENTS", schema = "STUDENTDB")
public class Student extends StudentAbstract {

    private String name;
    private long room;
    private String phone;
    private String email;
    private double avgGrade;
    private boolean engCert;

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

    @Column(name = "phone")
    @Valid
    public String getPhone() {
        return phone;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "avg_grade")
    public Double getAvgGrade() {
        return avgGrade;
    }

    @Column(name = "eng_cert")
    public Boolean getEngCert() {
        return engCert;
    }
}
