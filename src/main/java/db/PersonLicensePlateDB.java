package db;

import domain.LicensePlate;
import domain.Person;
import java.util.List;

public interface PersonLicensePlateDB {

    List<Person> getPersondb();

    List<LicensePlate> getLicensePlates();

    void addPerson(Person person);

    void removePerson(Person person);

    void addLicencePlateToPerson(Person person, LicensePlate licensePlate);

    boolean contains(LicensePlate plate);

    Person getPersonWithPlate(LicensePlate plate);

    Person getPersonWithRnumber(String rnumber);

    void update();
}
