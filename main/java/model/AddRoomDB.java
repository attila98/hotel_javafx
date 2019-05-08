package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * A szobák adatbázisba történő írását megvalósító osztály.
 */
public class AddRoomDB {


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

                //File input=new File(getClass().getClassLoader().getResource("rooms.xml").getFile());

                //Document document= db.parse(input);
                Document document=db.parse(getClass().getResourceAsStream("/database/rooms.xml"));

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
                StreamResult result=new StreamResult(getClass().getResourceAsStream("/database/rooms.xml").toString());
                transformer.transform(source,result);


            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerConfigurationException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    };
}
