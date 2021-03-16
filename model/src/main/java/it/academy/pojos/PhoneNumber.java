package it.academy.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Proxy(lazy = false)
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

    @Column(name = "NUMBER")
    private String number;

    @ManyToOne
    @JsonBackReference
    private Department department;
    @ManyToOne

    private Employee employee;

}
