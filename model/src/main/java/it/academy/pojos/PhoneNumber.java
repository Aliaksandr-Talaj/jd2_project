package it.academy.pojos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PHONE_NUMBER")
public class PhoneNumber {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "PHONE_NUMBER_CATEGORY")
    @Enumerated(EnumType.STRING)
    private ContactCategory phoneNumberCategory;

    @Column(name = "PHONE_OPERATOR")
    @Enumerated(EnumType.STRING)
    private PhoneOperator phoneOperator;

    @Column(name = "PHONE_NUMBER")
    private String number;

    @ManyToOne
    private Department department;
    @ManyToOne
    private Employee employee;

}
