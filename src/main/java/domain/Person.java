package domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String rNumber;
    private List<LicensePlate> plates;

    public Person(String firstName, String lastName, String rNumber, LicensePlate licensePlate){
        setFirstName(firstName);
        setLastName(lastName);
        setrNumber(rNumber);
        plates = new ArrayList<LicensePlate>();
        plates.add(licensePlate);
    }

    public Person(LicensePlate licensePlate){
        plates = new ArrayList<LicensePlate>();
        plates.add(licensePlate);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getrNumber() {
        return rNumber;
    }

    public void setrNumber(String rNumber) {
        this.rNumber = rNumber;
    }

    public void addLicensePlate(LicensePlate licensePlate){
        if(plates.size() < 3){
            plates.add(licensePlate);
        } else{
            throw new IllegalArgumentException("verwijder 1 nummerplaat");
        }
    }

    public void removeLicensePlate(LicensePlate licensePlate){
        plates.remove(licensePlate);
    }

    public List<LicensePlate> getPlates() {
        return plates;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +": "+rNumber + " registered license plates: " + plates;
    }
}
