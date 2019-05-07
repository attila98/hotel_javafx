package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookedRoom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * A foglalt szobák adatbázisból való kiolvasását megvalósító osztály.
 */
public class ReadBookedDB {

    /**
     * A foglalt szobák tarolására alkalmas lista.
     */
    private final ObservableList<BookedRoom> data3 = FXCollections.observableArrayList();

    /**
     *A DocumentBuilderFactory létrehozása.
     */
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
    /**
     * DocumentBuilder létrehozása.
     */
    DocumentBuilder db;

    {
        try {
            db = dbf.newDocumentBuilder();
            File input = new File(getClass().getClassLoader().getResource("reservations.xml").getFile());

            Document document=db.parse(input);
            NodeList nodeList=document.getElementsByTagName("reservation");
            for (int i=0;i<nodeList.getLength();i++){
                Node node=nodeList.item(i);
                if (node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    BookedRoom reservation=new BookedRoom();
                    reservation.getP().setLastName(element.getElementsByTagName("fname").item(0).getTextContent());
                    reservation.getP().setFirstName(element.getElementsByTagName("lname").item(0).getTextContent());
                    reservation.getP().setPhoneNum(element.getElementsByTagName("pnum").item(0).getTextContent());
                    reservation.getR().setFloor(element.getElementsByTagName("floor").item(0).getTextContent());
                    reservation.getR().setDoor(element.getElementsByTagName("door").item(0).getTextContent());
                    reservation.setInDate(element.getElementsByTagName("in").item(0).getTextContent());
                    reservation.setOutDate(element.getElementsByTagName("out").item(0).getTextContent());
                    data3.add(reservation);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Visszaadja a foglalt szobák listáját.
     * @return foglalt szobák listája
     */
    public ObservableList<BookedRoom> getData3() {
        return data3;
    }
}
