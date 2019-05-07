package sample;

import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    Room teszt;

    @BeforeEach
    void setUp() {
        teszt=new Room();
    }

    @Test
    void settersGetters(){
        teszt.setDoor("1");
        teszt.setFloor("2");
        teszt.setRoomType("egyagyas");
        assertEquals("1",teszt.getDoor());
        assertEquals("2",teszt.getFloor());
        assertEquals("egyagyas",teszt.getRoomType());
        teszt.setFloor("5");
        assertEquals("5",teszt.getFloor());
    }
}