package it.academy.dto;

import it.academy.pojos.Employee;
import it.academy.pojos.PhoneNumber;
import it.academy.pojos.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeePhoneNumberPositionEmailDto {

    private Employee employee;

    private List<PhoneNumber> phoneNumbers;

    private List<Position> positions;
}
