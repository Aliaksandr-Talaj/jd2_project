package it.academy.pojos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class FullName {
    @Column(name = "NAME")
    private String name;
    @Column(name = "PATRONYMIC")
    private String patronymic;
    @Column(name = "SURNAME")
    private String surname;

}
