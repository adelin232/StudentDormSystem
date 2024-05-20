package studentsdorm.system.Student;

import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Setter
@MappedSuperclass
public abstract class StudentAbstract {

    protected Long id;
    protected String userId;
}
