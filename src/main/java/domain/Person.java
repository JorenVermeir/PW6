package domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String rNumber;
    private List<LicensePlate> plates;
    private LicensePlate plateinside;

    public Person(String firstName, String lastName, String rNumber, LicensePlate licensePlate){
        setFirstName(firstName);
        setLastName(lastName);
        setrNumber(rNumber);
        plates = new ArrayList<LicensePlate>();
        addLicensePlate(licensePlate);
    }

    public Person(LicensePlate licensePlate){
        plates = new ArrayList<LicensePlate>();
        addLicensePlate(licensePlate);
    }

    public Person(){
        plates = new ArrayList<LicensePlate>();
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
        if(plates.size() <= 3){
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

    public LicensePlate getPlateinside() {
        return plateinside;
    }

    public void setPlateinside(LicensePlate plateinside) {
        this.plateinside = plateinside;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rNumber='" + rNumber + '\'' +
                ", plates=" + plates +
                ", plateinside=" + plateinside +
                '}';
    }
}
