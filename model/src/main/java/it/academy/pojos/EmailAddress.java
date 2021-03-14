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
public class EmailAddress {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "EMAIL")
    private String email;
    @Column(name = "EMAIL_CATEGORY")
    @Enumerated(EnumType.STRING)
    private ContactCategory emailCategory;

    @ManyToOne
    private Employee employee;

}
