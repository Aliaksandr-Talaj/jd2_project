package it.academy.pojos;

import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter

public class Description {

    @Column(name = "COMMON_INFO")
    private String commonInfo;
    @Column(name = "PURPOSE")
    private String purpose;
    @Column(name = "MISSION")
    private String mission;

}
