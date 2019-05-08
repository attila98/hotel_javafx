package model;

import javafx.beans.property.SimpleStringProperty;

/**
 * A foglaláshoz szükséges szobát megvalósító osztály.
 */
public class Room {
    /**
     * Melyik emeleten található a szoba.
     */
    private final SimpleStringProperty floor;
    /**
     * A szoba ajtójának száma.
     */
    private final SimpleStringProperty door;
    /**
     * A szoba típusa.
     */
    private final SimpleStringProperty roomType;

    /**
     * Az oszály konstruktora, amely üresre állítja a változókat.
     */
    public Room(){
        this.floor = new SimpleStringProperty("");
        this.door = new SimpleStringProperty("");
        this.roomType = new SimpleStringProperty("");
    }

    /**
     * Az oszály konstruktora, amely a változókat a kapott paraméter szerint állítja be.
     * @param floor emelet
     * @param door ajtó
     * @param roomT típus
     */
    public Room(String floor,String door, String roomT){
        this.floor = new SimpleStringProperty(floor);
        this.door = new SimpleStringProperty(door);
        this.roomType = new SimpleStringProperty(roomT);

    }


    /**
     * Az emelet gettere.
     * @return String: emelet
     */
    public String getFloor() {
        return floor.get();
    }

    /**
     * Az emelet settere.
     * @param floor emelet
     */
    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    /**
     * Az ajtó gettere.
     * @return String: ajtó
     */
    public String getDoor() {
        return door.get();
    }

    /**
     * Az ajtó settere.
     * @param door ajtó
     */
    public void setDoor(String door) {
        this.door.set(door);
    }

    /**
     * A szoba típusának gettere.
     * @return String: szoba típusa
     */
    public String getRoomType() {
        return roomType.get();
    }

    /**
     * A típus settere.
     * @param roomType szoba típus
     */
    public void setRoomType(String roomType) {
        this.roomType.set(roomType);
    }

    /**
     * Stringgé alakítja a változókat.
     * @return String: amely tartalmazza az osztály elemeinek értékeit
     */
    @Override
    public String toString() {
        return "Room{" +
                "floor=" + floor +
                ", door=" + door +
                ", roomType=" + roomType +
                '}';
    }
}
