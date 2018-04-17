package db;

import domain.LicensePlate;
import domain.Person;

import java.util.ArrayList;
import java.util.List;

public class LicensePlateDB {
    private List<Person> persondb;

    public LicensePlateDB(){
        persondb = new ArrayList<Person>();
    }

    public List<Person> getPersondb() {
        return persondb;
    }

    public void addPerson(Person person){
        persondb.add(person);
    }

    public void removePerson(Person person){
        persondb.remove(person);
    }
}
