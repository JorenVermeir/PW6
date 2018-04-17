package db;

import domain.LicensePlate;
import domain.Person;

import java.util.ArrayList;
import java.util.List;

public class LicensePlateDB {
    private List<Person> persondb;

    public LicensePlateDB(){
        persondb = new ArrayList<Person>();
        persondb.add(new Person(new LicensePlate("1ABC123")));
        persondb.add(new Person(new LicensePlate("1RUR907")));
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

    public boolean contains(LicensePlate plate){
        return persondb.stream().map(p -> p.getPlates()).anyMatch(l -> l.contains(plate));
    }
}
