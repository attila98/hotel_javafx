package model;

import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * A foglalásokat megvalósító osztály.
 */
public class BookedRoom {

    /**
     * A logger létrehozása.
     */
    private static final Logger logger = LogManager.getLogger(AddRoomDB.class);
    /**
     * A foglalásban részt vevő személy.
     */
    private Person p;
    /**
     * A foglalásban részt vevő szoba.
     */
    private Room r;
    /**
     * A foglalás kezdete.
     */
    private final SimpleStringProperty inDate;
    /**
     * A foglalás vége.
     */
    private final SimpleStringProperty outDate;

    /**
     * Naponta fizetendő összeg.
     */
    public static final int DAILYPRICE=2000;

    /**
     * Az osztály egyik konstruktora, amely üresre állítja a változókat.
     */
    public BookedRoom(){
        this.p=new Person();
        this.r=new Room();
        this.inDate = new SimpleStringProperty("");
        this.outDate = new SimpleStringProperty("");
    }

    /**
     * Az osztály egyik konstruktora, amely beállítja a változókat a kapott paraméterek alapján.
     * @param person a szemény adati
     * @param room szoba adati
     * @param type személyek száma
     * @param inD becsekkolás dátuma
     * @param outD kicsekkolás dátuma
     */
    public BookedRoom(Person person,Room room,String type, String inD, String outD){
        this.p=person;
        this.r=room;
        //this.counter = new SimpleStringProperty(count);
        this.inDate = new SimpleStringProperty(inD);
        this.outDate = new SimpleStringProperty(outD);
    }

    /**
     * Visszaadja a foglálás árát.
     * @return a foglalás ára
     */
    public long getPrice(){
        logger.info("Az ár számítása megtörént!");
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        long diff=0;
        long days=0;
        Date in;
        try {
            in=format.parse(inDate.getValue());
            Date out=format.parse(outDate.getValue());
            diff=out.getTime()-in.getTime();
            days=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            System.out.println("Nem megfelőlő formátum!");
        }

        if(diff==0){
            return DAILYPRICE/2;
        }else if(diff<0) {
            return 0;
        }else{
            return days*DAILYPRICE;
        }
    }

    /**
     * Szemény gettere.
     * @return személy objektum
     */
    public Person getP() {
        return p;
    }

    /**
     * személy settere.
     * @param p egy szeméy osztály
     */
    public void setP(Person p) {
        this.p = p;
    }

    /**
     * szoba gettere.
     * @return szoba osztály
     */
    public Room getR() {
        return r;
    }

    /**
     * szoba settere.
     * @param r szoba osztály
     */
    public void setR(Room r) {
        this.r = r;
    }

    /**
     * Napi ár.
     * @return napi ár
     */
    public static int getDAILYPRICE() {
        return DAILYPRICE;
    }

    /**
     * becsekkolás gettere.
     * @return becsekkolás dátuma
     */
    public String getInDate() {
        return inDate.get();
    }

    /**
     * becsekkolás gettere.
     * @return becsekkolás dátuma
     */
    public SimpleStringProperty inDateProperty() {
        return inDate;
    }

    /**
     * becsekkolás settere.
     * @param inDate becsekkolás dátuma
     */
    public void setInDate(String inDate) {
        this.inDate.set(inDate);
    }

    /**
     * kicsekkolás gettere.
     * @return kicsekkolás dátuma
     */
    public String getOutDate() {
        return outDate.get();
    }

    /**
     * kicsekkolás gettere.
     * @return kicsekkolás dátuma
     */
    public SimpleStringProperty outDateProperty() {
        return outDate;
    }

    /**
     * kicsekkolás settere.
     * @param outDate kicsekkolás dátuma
     */
    public void setOutDate(String outDate) {
        this.outDate.set(outDate);
    }
}
