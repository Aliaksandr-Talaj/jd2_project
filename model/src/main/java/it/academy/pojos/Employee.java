package it.academy.pojos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
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
    @OneToMany(mappedBy = "employee")
    private Set<PhoneNumber> phoneNumbers;


    @OneToMany(mappedBy = "employee")
    private Set<EmailAddress> emailAddress;


    @OneToMany(mappedBy = "employee")
    private Set<Position> positions;

    @Column(name = "DATE_OF_EMPLOYMENT")
    private Date dateOfEmployment;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
