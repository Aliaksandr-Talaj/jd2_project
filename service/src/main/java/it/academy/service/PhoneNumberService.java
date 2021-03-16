package it.academy.service;

import it.academy.dao.interfaces.PhoneNumberDao;
import it.academy.pojos.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneNumberService {
    @Autowired
    PhoneNumberDao phoneNumberDao;

    public List<PhoneNumber> findAllPhoneNumbers() {
        return phoneNumberDao.getAllPhoneNumbers();
    }

    public List<PhoneNumber> findPhoneNumbersOfDepartment(String depId) {
        return phoneNumberDao.getPhoneNumbersOfDepartment(depId);
    }

    public List<PhoneNumber> findPhoneNumbersOfEmployee(String empId) {
        return phoneNumberDao.getPhoneNumbersOfEmployee(empId);
    }

    public PhoneNumber findPhoneNumber(String id) {
        return phoneNumberDao.getPhoneNumber(id);
    }

    public String createPhoneNumber(PhoneNumber phoneNumber) {
        return (String) phoneNumberDao.createPhoneNumber(phoneNumber);
    }

    public boolean updatePhoneNumber(PhoneNumber phoneNumber) {
        String id = phoneNumber.getId();
        if (id != null) {
            PhoneNumber pn = phoneNumberDao.getPhoneNumber(id);
            if (pn != null) {
                phoneNumberDao.updatePhoneNumber(phoneNumber);
                return true;
            }
        }
        return false;
    }

    public boolean deletePhoneNumber(String id) {
        if (id != null) {
            PhoneNumber pn = phoneNumberDao.getPhoneNumber(id);
            if (pn != null) {
                return phoneNumberDao.deletePhoneNumber(id);
            }
        }
        return false;
    }
}
