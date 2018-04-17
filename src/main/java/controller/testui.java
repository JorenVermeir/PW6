package controller;

import db.LicensePlateDB;
import domain.LicensePlate;
import domain.Person;

public class testui {
    public static void main(String[] args){
        LicensePlateDB db = new LicensePlateDB();

        LicensePlate p1 = new LicensePlate("1RGH390");
        LicensePlate p2 = new LicensePlate("1PSZ063");
        LicensePlate p3 = new LicensePlate("1RUR907");
        LicensePlate p4 = new LicensePlate("1BHF333");

        Person p = new Person("Arnold", "Braine", "r0674221", p1);
        Person per = new Person("Quinten", "Nouters", "r0663683", p2);



        db.addPerson(p);
        db.addPerson(per);
        System.out.println(db.getPersondb());
        System.out.println(p.getPlates());
        db.removePerson(p);
        System.out.println(db.getPersondb().get(0));
    }
}
