package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * A szobák adatbázisból való kiolvasását megvalósító osztály.
 */
public class ReadRoomsDB {

        /**
        * A logger létrehozása.
        */
        private static final Logger logger = LogManager.getLogger(AddRoomDB.class);

        /**
        * A szobák tarolására alkalmas lista.
        */
        private final ObservableList<Room> data2 = FXCollections.observableArrayList();

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
            File input = new File("rooms.xml");
            Document document=db.parse(input);

            NodeList nodeList=document.getElementsByTagName("room");
            for (int i=0;i<nodeList.getLength();i++){
                Node node=nodeList.item(i);
                if (node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) node;
                    Room room=new Room(element.getElementsByTagName("emelet").item(0).getTextContent().toString(),
                                       element.getElementsByTagName("ajto").item(0).getTextContent().toString(),
                                       element.getElementsByTagName("tipus").item(0).getTextContent().toString());
                    data2.add(room);
                }
            }

        } catch (ParserConfigurationException e) {
            logger.error(e);
        } catch (SAXException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    /**
     * Visszaadja a szobák listáját.
     * @return szobák listája
     */
    public ObservableList<Room> getData2() {
        return data2;
    }
}
