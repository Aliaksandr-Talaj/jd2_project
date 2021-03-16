package it.academy.pojos;

import lombok.*;
import net.bytebuddy.build.ToStringPlugin;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "DEPARTMENT_NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    @Embedded
    private Description description;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PhoneNumber> phoneNumber;

    @Column(name = "DATE_OF_FORMATION")
    private Date dateOfFormation;

    @Column(name = "EMPLOYEES")
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Employee> employees;
}
