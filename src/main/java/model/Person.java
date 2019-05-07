package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Az osztály a foglaláshoz szükséges szeményt valósítja meg.
 */
public class Person {

    /**
     * A szemény keresztneve.
     */
    private final SimpleStringProperty firstName;
    /**
     * A személy vezetékneve.
     */
    private final SimpleStringProperty lastName;
    /**
     * A szemény telefonszáma.
     */
    private final SimpleStringProperty phoneNum;

    /**
     * Az osztály konstruktora, amely üresre sztringre állítja az osztály változóit.
     */
    public Person(){
        this.lastName = new SimpleStringProperty("");
        this.firstName = new SimpleStringProperty("");
        this.phoneNum = new SimpleStringProperty("");
    }

    /**
     * Az osztály másik konstruktora, amely beállítja az osztály változók értéket a kapott paraméterek alapján.
     * @param fName a keresznévet tartalmazza
     * @param lName a vezetéknevet tartalmazza
     * @param phoneNum a telefonszámot tartalmazza
     */
    public Person(String fName, String lName, String phoneNum) {
        this.lastName = new SimpleStringProperty(lName);
        this.firstName = new SimpleStringProperty(fName);
        this.phoneNum = new SimpleStringProperty(phoneNum);

    }

    /**
     * Vezetéknév gettere.
     * @return String: vezetéknév
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Vezetéknév settere.
     * @param lastName veztéknevet tartalmazza
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    /**
     * keresztnév gettere.
     * @return String: keresztnév
     */
    public String getFirstName() {
        return firstName.get();
    }

    /**
     * keresztnév settere.
     * @param firstName keresztnév
     */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    /**
     * telefonszám gettere.
     * @return String: telefonszám
     */
    public String getPhoneNum() {
        return phoneNum.get();
    }

    /**
     * telefonszám settere.
     * @param num telefonszám
     */
    public void setPhoneNum(String num) {
        this.phoneNum.set(num);
    }
}
