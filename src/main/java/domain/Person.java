package domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String rNumber;
    private List<LicensePlate> plates;

    public Person(String firstName, String lastName, String rNumber){
        setFirstName(firstName);
        setLastName(lastName);
        setrNumber(rNumber);
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
        lastName = lastName;
    }

    public String getrNumber() {
        return rNumber;
    }

    public void setrNumber(String rNumber) {
        this.rNumber = rNumber;
    }
}
