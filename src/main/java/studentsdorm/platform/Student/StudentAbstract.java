package studentsdorm.platform.Student;

import lombok.Setter;

import javax.persistence.*;

@Setter
@MappedSuperclass
public abstract class StudentAbstract {

    protected Long id;
}
