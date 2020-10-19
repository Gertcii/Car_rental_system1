package dao;

import entity.Drivers;

import java.sql.SQLException;
import java.util.List;

public interface DriversDao extends Dao<Drivers>{
    //дао + интерфейс для работы с таблицей drivers
    List<Drivers> getBySurname(String lastName) throws SQLException;

    Drivers getByPassportNumber(String passportNumber) throws SQLException;

    boolean isValidName(String name);

    boolean isValidPassport(String passport);

    public boolean isValidExperience(int experience);

    void setIdFroDB(Drivers driver) throws SQLException;
}
