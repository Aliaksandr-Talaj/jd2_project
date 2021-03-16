package it.academy.service;

import it.academy.dao.interfaces.PositionDao;
import it.academy.pojos.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionDao positionDao;

    public Position findPosition(String id) {
        return positionDao.getPosition(id);
    }

    public List<Position> findAllPositions() {
        return positionDao.getAllPositions();
    }

    public List<Position> findPositionsOfEmployee(String empId) {
        return positionDao.getPositionsOfEmployee(empId);
    }

    public String createPosition(Position position) {
        return positionDao.createPosition(position);
    }

    public boolean updatePosition(Position position) {
        String id = position.getId();
        if (id != null) {
            Position p = positionDao.getPosition(id);
            if (p != null) {
                positionDao.updatePosition(position);
                return true;
            }
        }
        return false;
    }

    public boolean deletePosition(String id) {

        if (id != null) {
            Position p = positionDao.getPosition(id);
            if (p != null) {
                return positionDao.deletePosition(id);
            }
        }
        return false;

    }

}
