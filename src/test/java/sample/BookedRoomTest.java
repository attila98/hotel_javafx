package sample;

import model.BookedRoom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookedRoomTest {

    BookedRoom teszt;

    @BeforeEach
    void setUp() {
        teszt=new BookedRoom();
        teszt.setInDate("2019-03-03");
        teszt.setOutDate("2019-03-05");
    }

    @AfterEach
    void tearDown() {
        teszt=null;
    }

    @Test
    void getPrice() {
        assertEquals(4000,teszt.getPrice());
        teszt.setInDate("2019-02-05");
        teszt.setOutDate("2018-02-05");
        assertEquals(0,teszt.getPrice());
        teszt.setInDate("2020-12-05");
        teszt.setOutDate("2020-12-05");
        assertEquals(1000,teszt.getPrice());
    }
}