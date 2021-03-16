package it.academy.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Proxy(lazy = false)
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "FULL_NAME")
    @Embedded
    private FullName fullName;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "PHONE_NUMBERS")
    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)

    @JsonIgnoreProperties("employee")
    private List<PhoneNumber> phoneNumbers;


    @Embedded
    private EmailAddress emailAddress;


    @OneToMany(mappedBy = "employee")
    @JsonManagedReference
    private List<Position> positions;

    @Column(name = "DATE_OF_EMPLOYMENT")
    private Date dateOfEmployment;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
