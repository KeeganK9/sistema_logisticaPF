package senac.senacfx.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import senac.senacfx.gui.util.Alerts;
import senac.senacfx.gui.util.Constraints;
import senac.senacfx.model.entities.Transport;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ViewController implements Initializable {

    @FXML
    private ComboBox<Transport> combo1;

    @FXML
    private Button btAll;

    private ObservableList<Transport> obsList;
    @FXML
    public void onComboBoxPersonAction() {
        Transport transport = combo1.getSelectionModel().getSelectedItem();
        System.out.println(transport);
    }
    @FXML
    public void onBtAllAction() {
        for(Transport transport : combo1.getItems()){
            System.out.println(transport);
        }
    }

    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;

    @FXML
    private Label labelResult;
    @FXML
    private Button btSum;
    @FXML
    public void onBtSumAction(){
        try{
            Locale.setDefault(Locale.US);
            double n1 = Double.parseDouble(txt1.getText());
            double n2 = Double.parseDouble(txt2.getText());
            double sum = n1 + n2;
            labelResult.setText(String.format("%.2f", sum));
        } catch (NumberFormatException e){
            Alerts.showAlert("Error", "Parse error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private Button bTest;
    @FXML
    public void onBtTestAction(){
        Alerts.showAlert("ERRO", "DEU RUIM", "VAI EXPLODIR", Alert.AlertType.ERROR);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Transport> list = new ArrayList<>();
        list.add(new Transport(1, "Maria", "vidaloka12@gmail.com"));
        list.add(new Transport(2, "John", "snow@gmail.com"));
        list.add(new Transport(3, "Marcos", "marcoveio@gmail.com"));

        obsList = FXCollections.observableArrayList(list);
        combo1.setItems(obsList);

        Callback<ListView<Transport>, ListCell<Transport>> factory = lv -> new ListCell<Transport>() {
            @Override
            protected void updateItem(Transport item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        };
        combo1.setCellFactory(factory);
        combo1.setButtonCell(factory.call(null));

        Constraints.setTextFieldDouble(txt1);
        Constraints.setTextFieldDouble(txt2);
        Constraints.setTextFieldMaxLength(txt1, 5);
        Constraints.setTextFieldMaxLength(txt2, 5);
        Constraints.setTextFieldInteger(txt1);
        Constraints.setTextFieldInteger(txt2);
    }
}