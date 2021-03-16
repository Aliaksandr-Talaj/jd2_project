package it.academy.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Proxy(lazy = false)
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

    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    @JsonManagedReference

    @Transient
    private List<PhoneNumber> phoneNumber;

    @Column(name = "DATE_OF_FORMATION")
    private Date dateOfFormation;

    @Column(name = "EMPLOYEES")
    @OneToMany(mappedBy = "department")

    @Transient
    @ToString.Exclude
    private List<Employee> employees;
}
