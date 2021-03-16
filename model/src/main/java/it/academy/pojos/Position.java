package it.academy.pojos;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "POSITION")
public class Position {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PURPOSE")
    private String purpose;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Employee employee;

}
