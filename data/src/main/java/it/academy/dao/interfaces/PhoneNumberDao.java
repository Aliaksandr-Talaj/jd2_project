package it.academy.dao.interfaces;

import it.academy.pojos.PhoneNumber;

import java.util.List;

public interface PhoneNumberDao {

    PhoneNumber getPhoneNumber(String id);

    List<PhoneNumber> getAllPhoneNumbers();

    List<PhoneNumber> getPhoneNumbersOfDepartment(String depId);

    List<PhoneNumber> getPhoneNumbersOfEmployee(String empId);

    String createPhoneNumber(PhoneNumber phoneNumber);

    void updatePhoneNumber(PhoneNumber phoneNumber);

    boolean deletePhoneNumber(String id);
}
