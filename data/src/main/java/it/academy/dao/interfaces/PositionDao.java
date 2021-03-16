package it.academy.dao.interfaces;

import it.academy.pojos.Position;

import java.util.List;

public interface PositionDao {

    String createPosition(Position position);

    Position getPosition(String id);

    List<Position> getAllPositions();

    List<Position> getPositionsOfEmployee(String employeeId);

    void updatePosition(Position position);

    boolean deletePosition(String positionId);

}
