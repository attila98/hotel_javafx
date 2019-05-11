package model;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A foglalások adatbázisba történő írását megvalósító osztály.
 */
public class AddBookedRoom {
    private static final Logger logger = LogManager.getLogger(AddBookedRoom.class);

    /**
     * Az oszály konstruktora.
     * @param a keresztnév
     * @param b vezetéknév
     * @param c telefonszám
     * @param d emelet
     * @param e ajtó
     * @param f becsekkolás dátuma
     * @param g kicsekkolás dátuma
     */
    public AddBookedRoom(String a,String b, String c, String d, String e,String f, String g){
        add(a,b,c,d,e,f,g);
    }

    /**
     * Beírja a paraméterekben szereplő adatokat, az xml dokumentumba.
     * @param lnam keresztnév
     * @param fnam vezetéknév
     * @param pnu telefonszám
     * @param floo emelet
     * @param doo ajtó
     * @param indate becsekkolás dátuma
     * @param outdate kicsekkolás dátuma
     */
    public void add(String lnam,String fnam,String pnu,String floo,String doo,String indate, String outdate){
        DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
        DocumentBuilder db;

        {
            try {
                db = dbf.newDocumentBuilder();

                File input=new File("reservations.xml");
                Document document=db.parse(input);

                Element root=document.getDocumentElement();
                Element reservation=document.createElement("reservation");
                root.appendChild(reservation);

                Element lname=document.createElement("lname");
                lname.appendChild(document.createTextNode(lnam));
                reservation.appendChild(lname);

                Element fname=document.createElement("fname");
                fname.appendChild(document.createTextNode(fnam));
                reservation.appendChild(fname);

                Element pnum=document.createElement("pnum");
                pnum.appendChild(document.createTextNode(pnu));
                reservation.appendChild(pnum);

                Element floor=document.createElement("floor");
                floor.appendChild(document.createTextNode(floo));
                reservation.appendChild(floor);

                Element door=document.createElement("door");
                door.appendChild(document.createTextNode(doo));
                reservation.appendChild(door);

                Element in=document.createElement("in");
                in.appendChild(document.createTextNode(indate));
                reservation.appendChild(in);

                Element out=document.createElement("out");
                out.appendChild(document.createTextNode(outdate));
                reservation.appendChild(out);

                TransformerFactory  tf=TransformerFactory.newInstance();
                Transformer transformer=tf.newTransformer();

                DOMSource source =new DOMSource(document);
                StreamResult result=new StreamResult(new File("reservations.xml"));
                transformer.getOutputProperty(OutputKeys.INDENT);
                transformer.transform(source,result);

            } catch (ParserConfigurationException e) {
                logger.error(e);
            } catch (SAXException e) {
                logger.error(e);
            } catch (IOException e) {
                logger.error(e);
            } catch (TransformerConfigurationException e) {
                logger.error(e);
            } catch (TransformerException e) {
                logger.error(e);
            }
        }

    }



}
