package controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Ez az osztály kapcsolja össze a UI-t az adatokkal.
 * A program logikai része itt valósul meg.
 */
public class Controller implements Initializable {


    /**
     * A logger létrehozása.
     */
    private static final Logger logger = LogManager.getLogger(Controller.class);

    /**
     * A controller összekötése a mainPanenel.
     */
    @FXML
    Pane mainPane;
    /**
     * A controller összekötése a bookPanenel.
     */
    @FXML
    Pane bookPane;
    /**
     * A controller összekötése a listPanenel.
     */
    @FXML
    Pane listPane;
    /**
     * A controller összekötése a modPanenel.
     */
    @FXML
    Pane modPane;
    /**
     * A controller összekötése a bookRoom gombbal.
     */
    @FXML
    Button bookRoom;
    /**
     * A controller összekötése a listRoom gombbal.
     */
    @FXML
    Button listRoom;
    /**
     * A controller összekötése a modRoom gombbal.
     */
    @FXML
    Button modRoom;
    /**
     * A controller összekötése a floorList választó listával.
     */
    @FXML
    ChoiceBox floorList;
    /**
     * A controller összekötése a doorList választó listával.
     */
    @FXML
    ChoiceBox doorList;
    /**
     * A controller összekötése a roomType választó listával.
     */
    @FXML
    ChoiceBox roomType;
    /**
     * A controller összekötése a lastName mezővel.
     */
    @FXML
    TextField lastName;
    /**
     * A controller összekötése a firstName mezővel.
     */
    @FXML
    TextField firstName;
    /**
     * A controller összekötése a phoneNum mezővel.
     */
    @FXML
    TextField phoneNum;
    /**
     * A controller összekötése a table táblázattal.
     */
    @FXML
    TableView table;
    /**
     * A controller összekötése az addRoom gombbal.
     */
    @FXML
    Button addRoom;
    /**
     * A controller összekötése az addType mezővel.
     */
    @FXML
    TextField addType;
    /**
     * A controller összekötése az addFloor mezővel.
     */
    @FXML
    TextField addFloor;
    /**
     * A controller összekötése az addDoor mezővel.
     */
    @FXML
    TextField addDoor;
    /**
     * A controller összekötése az inDate dátum választóval.
     */
    @FXML
    DatePicker inDate;
    /**
     * A controller összekötése az outDate dátum választóval.
     */
    @FXML
    DatePicker outDate;
    /**
     * A controller összekötése az alertPane-nel.
     */
    @FXML
    Pane alertPane;
    /**
     * A controller összekötése az alertButton gombbal.
     */
    @FXML
    Button alertButton;
    /**
     * A controller összekötése az alerText mezővel.
     */
    @FXML
    Label alertText;

    /**
     * Napi ár.
     */
    @FXML
    Label daily;
    /**
     * Fiztendő összeg.
     */
    @FXML
    Label price;

    /**
     * A szobákat tárolja egy listában.
     */
    private ObservableList<Room> data2 = FXCollections.observableArrayList();
    /**
     * A foglalásokat tarolja egy listában.
     */
    private ObservableList<BookedRoom> data3 = FXCollections.observableArrayList();

    /**
     * Dátom formátumot tárolja.
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Az aktuális dátumot tárolja.
     */
    LocalDate now=LocalDate.now();


    /**
     * Pane váltáshoz szükséges, a "szoba foglalása" gombot kezeli.
     */
    public void bookRoom(){
        listPane.setVisible(false);
        listPane.setDisable(true);
        bookPane.setVisible(true);
        bookPane.setDisable(false);
        modPane.setVisible(false);
        modPane.setDisable(true);
    }

    /**
     * Pane váltáshoz szükséges, a "szobák listázása" gombot kezeli.
     */
    public void listRoom(){
        bookPane.setVisible(false);
        bookPane.setDisable(true);
        listPane.setVisible(true);
        listPane.setDisable(false);
        modPane.setVisible(false);
        modPane.setDisable(true);
    }

    /**
     * Pane váltáshoz szükséges, a "szoba hozzáadása" gombot kezeli.
     */
    public void modRoom(){
        bookPane.setVisible(false);
        bookPane.setDisable(true);
        listPane.setVisible(false);
        listPane.setDisable(true);
        modPane.setVisible(true);
        modPane.setDisable(false);
    }

    /**
     * A "foglalás" gomb eseményét kezeli, hozzáadja az adatbázishoz, illetve a data3 listához az adatokat.
     */
    public void booking(){
        if (check()) {
            Person actualPerson = new Person(firstName.getText(), lastName.getText(), phoneNum.getText());
            Room actualRoom = new Room(floorList.getValue().toString(), doorList.getValue().toString(), roomType.getValue().toString());
            BookedRoom actualBRoom = new BookedRoom(actualPerson, actualRoom, roomType.getValue().toString(), inDate.getValue().toString(), outDate.getValue().toString());
            data3.add(actualBRoom);
            AddBookedRoom teszt = new AddBookedRoom(firstName.getText(), lastName.getText(), phoneNum.getText(), floorList.getValue().toString(), doorList.getValue().toString(),
                    inDate.getValue().toString(), outDate.getValue().toString());
            price.setText(String.valueOf(actualBRoom.getPrice()));
            logger.info("Sikeres foglalas!");
        }else {
            logger.error("Sikertelen foglalas!");
        }
    }

    /**
     * Új szobát lehet hozzáadni az adatbázishoz.
     */
    public void addRoom(){
        if(checkAddRoom()) {
            Room newRoom = new Room(addFloor.getText(), addDoor.getText(), addType.getText());
            data2.add(newRoom);
            floorList.getItems().add(addFloor.getText());
            doorList.getItems().add(addDoor.getText());
            roomType.getItems().add(addType.getText());

            AddRoomDB addRoom = new AddRoomDB(addFloor.getText(), addDoor.getText(), addType.getText());
            logger.info("Az uj szoba sikeresen letrejott!");
            setRoomList();
            logger.info("Sikeres szoba hozzaadas");
        }else {
            logger.error("Hiba a szoba letrehozasaban");
        }
    }


    /**
     * A legördülő menüket állítja, amelyből a szóbákhoz tartozó adatokat lehet kiválasztani(Emelet,ajtó,típus).
     */
    public void setRoomList(){

        floorList.setItems(data2.stream()
                .map(i -> i.getFloor())
                .distinct()
                .collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableArrayList(l))));

        floorList.setValue("");
        floorList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                doorList.setItems(data2.stream()
                        .filter(door -> door.getFloor().toString().contains((newValue.toString())))
                        .map(i -> i.getDoor())
                        .distinct()
                        .collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableArrayList(l))));

            }
        });

        doorList.setValue("");

        doorList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println("hiba3");
                roomType.setValue("");
                roomType.setItems(data2.stream()
                        .filter(door -> door.getDoor().toString().contains((newValue.toString())))
                        .map(i -> i.getRoomType())
                        .distinct()
                        .collect(Collectors.collectingAndThen(toList(), l -> FXCollections.observableArrayList(l))));
            }
        });

    }


    /**
     * Beállítja a szobák listájához tartozó táblázatot.Létrehozza az oszlopok neveit, és beállítja az oszlopok típusát.
     */
    public void setTable(){
        TableColumn firstNameCol=new TableColumn("Vezetéknév");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BookedRoom, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BookedRoom, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getP().getFirstName());
            }
        });

        TableColumn lastNameCol=new TableColumn("Keresztnév");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BookedRoom, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BookedRoom, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getP().getLastName());
            }
        });

        TableColumn phoneNumCol=new TableColumn("Telefonszám");
        phoneNumCol.setMinWidth(100);
        phoneNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNumCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BookedRoom, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BookedRoom, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getP().getPhoneNum());
            }
        });

        TableColumn floorCol=new TableColumn("Emelet");
        floorCol.setMinWidth(50);
        floorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        floorCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BookedRoom, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BookedRoom, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getR().getFloor());
            }
        });

        TableColumn doorCol=new TableColumn("Ajtó");
        doorCol.setMinWidth(50);
        doorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        doorCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BookedRoom, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<BookedRoom, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getR().getDoor());
            }
        });

        TableColumn inDateCol = new TableColumn("Bejelentkezés");
        inDateCol.setMinWidth(100);
        inDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        inDateCol.setCellValueFactory(new PropertyValueFactory<BookedRoom, String>("inDate"));

        TableColumn outDateCol = new TableColumn("Kijelentkezés");
        outDateCol.setMinWidth(100);
        outDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        outDateCol.setCellValueFactory(new PropertyValueFactory<BookedRoom, String>("outDate"));

        table.getColumns().addAll(firstNameCol,lastNameCol,phoneNumCol,floorCol,doorCol,inDateCol,outDateCol);
        table.setItems(data3);
        logger.info("A tablazat letrejott.");
    }

    /**
     * A szobák beolvasása az xml állományból
     *  a data2 listába.
     */
    public void readRooms(){
        ReadRoomsDB rooms=new ReadRoomsDB();
        data2=rooms.getData2();
        logger.info("A szobak beolvasasa az XML-bol sikeresen megtortent.");
    }

    /**
     * A foglalások beolvasása az xml állományból
     *  a data3 listába.
     */
    public  void readReservations(){
        ReadBookedDB reservations=new ReadBookedDB();
        data3=reservations.getData3();
        logger.info("A foglalasok beolvasasa az XML-bol sikeresen megtörtent.");
    }

    /**
     * Ellenőrzi a foglaláskor megadott adatok validságát.
     * @return true, ha minden renden van. false, ha valami nem helyes
     */
    public boolean check(){
        LocalDate choosenInDate=inDate.getValue();
        LocalDate choosenOutDate=outDate.getValue();
        sdf.setLenient(true);
        if(firstName.getText().isEmpty()){
            alert("keresztnév");
            return false;
        }else if(lastName.getText().isEmpty()){
            alert("vezetéknév");
            return false;
        }else if(phoneNum.getText().isEmpty() || phoneNum.getText().length()!=11) {
            alert("telefonszám");
            return false;
        }else if(choosenInDate.isBefore(now) || choosenOutDate.isBefore(choosenInDate)){
            alert("dátum");
            return false;
        }
        for(int i=0;i<data3.size();i++){
            if(data3.get(i).getR().getFloor().equals(floorList.getValue())  && data3.get(i).getR().getDoor().equals(doorList.getValue()) &&
                    data3.get(i).getInDate().equals(inDate.getValue().toString())
                    && data3.get(i).getOutDate().equals(outDate.getValue().toString())) {
                alert("már foglalt.");
                return false;
            }
        }
        logger.info("Az adatok validok.");
        return true;
    }

    public boolean checkAddRoom(){
        if(addType.getText().isEmpty()){
            alert("típus");
            return false;
        }else if(addDoor.getText().isEmpty()){
            alert("ajtó");
            return false;
        }else if(addFloor.getText().isEmpty()){
            alert("emelet");
            return false;
        }
        return true;
    }

    /**
     *A figyelmeztető Panen lévő gomb kezelése.
     */
   public void alertButton(){
        alertPane.setVisible(false);
        alertPane.setDisable(true);
        bookPane.setDisable(false);
        bookPane.setVisible(true);
        bookRoom.setDisable(false);
        listRoom.setDisable(false);
        modRoom.setDisable(false);
    }

    /**
     *Előhozza a figyelmeztető Pane-t.
     * @param text a figyelmeztető szöveget kapja meg.
     */
    public void alert(String text){
        listPane.setVisible(false);
        listPane.setDisable(true);
        bookPane.setVisible(true);
        bookPane.setDisable(true);
        modPane.setVisible(false);
        modPane.setDisable(true);

        alertPane.setVisible(true);
        alertPane.setDisable(false);

        bookRoom.setDisable(true);
        listRoom.setDisable(true);
        modRoom.setDisable(true);

        alertText.setText("Hibás " + text+"!");
    }

    /**
     *A program indulásakor fut le, beállítja a program kezdőállapotát.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readRooms();
        readReservations();
        setTable();
        setRoomList();
        inDate.setValue(now);
        outDate.setValue(now);

    }
}
