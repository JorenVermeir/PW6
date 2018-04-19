package db;

import domain.LicensePlate;
import domain.Person;

import java.util.ArrayList;
import java.util.List;

public class LicensePlateDBInMemory implements PersonLicensePlateDB{

    private List<Person> persondb;

    public LicensePlateDBInMemory(){
        persondb = new ArrayList<Person>();
        persondb.add(new Person("Daan","Adams","r0585626",new LicensePlate("1ABC123")));
        persondb.add(new Person("Joren","Vermeir","",new LicensePlate("1RUR907")));
        persondb.add(new Person("Quinten","Nouters", "r0663683", new LicensePlate("1PSZ068")));
        addLicencePlateToPerson(getPersonWithRnumber("r0663683"),new LicensePlate("1QTS123"));

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

    public void addLicencePlateToPerson(Person person, LicensePlate licensePlate){
        person.addLicensePlate(licensePlate);
    }

    public boolean contains(LicensePlate plate){
        return persondb.stream().map(p -> p.getPlates()).anyMatch(l -> l.contains(plate));
    }

    public Person getPersonWithPlate(LicensePlate plate){
        return persondb.stream().filter(p -> p.getPlates().contains(plate)).findFirst().orElse(null);
    }

    public Person getPersonWithRnumber(String rnumber){
        return persondb.stream().filter(p -> p.getrNumber().equals(rnumber)).findFirst().orElse(null);
    }
}
