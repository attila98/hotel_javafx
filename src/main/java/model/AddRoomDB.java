package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * A szobák adatbázisba történő írását megvalósító osztály.
 */
public class AddRoomDB {

    /**
     * A logger létrehozása.
     */
    private static final Logger logger = LogManager.getLogger(AddRoomDB.class);

    /**
     * Az osztály konstruktora.
     * @param a emelet
     * @param b ajtó
     * @param c szoba típus
     */
    public AddRoomDB(String a,String b,String c){
        addRoom(a,b,c);
    }

    /**
     * Beírja a paraméterben kapott adatokat az xml dokumentumba.
     * @param floor emelet
     * @param door ajtó
     * @param type szoba típus
     */
    public void addRoom(String floor,String door,String type){

        DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        {
            try {
                db = dbf.newDocumentBuilder();

                File input=new File("rooms.xml");

                Document document= db.parse(input);

                Element root= document.getDocumentElement();

                Element room=document.createElement("room");
                root.appendChild(room);

                Element emelet=document.createElement("emelet");
                emelet.appendChild(document.createTextNode(floor));
                room.appendChild(emelet);

                Element ajto=document.createElement("ajto");
                ajto.appendChild(document.createTextNode(door));
                room.appendChild(ajto);

                Element tipus=document.createElement("tipus");
                tipus.appendChild(document.createTextNode(type));
                room.appendChild(tipus);

                TransformerFactory  tf=TransformerFactory.newInstance();
                Transformer transformer=tf.newTransformer();

                DOMSource source =new DOMSource(document);
                StreamResult result=new StreamResult(new File("rooms.xml"));
                transformer.getOutputProperty(OutputKeys.INDENT);
                transformer.transform(source,result);


            } catch (ParserConfigurationException e) {
                logger.error(e);
            } catch (TransformerConfigurationException e) {
                logger.error(e);
            } catch (TransformerException e) {
                logger.error(e);
            } catch (SAXException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            }

        }

    };
}
