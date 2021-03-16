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
@Embeddable

public class EmailAddress {



    @Column(name = "EMAIL")
    private String email;
    @Column(name = "EMAIL_CATEGORY")
    @Enumerated(EnumType.STRING)
    private ContactCategory emailCategory;



}
