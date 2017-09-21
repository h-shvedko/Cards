package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListOfCardsController extends A_Controller {

    private ObservableList<Cards> cardsData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        linkToColumns();
        makeTitleOfColumns();

        CardsDAO cardsDAO = new CardsDAO();

        List cards = new ArrayList();
        try {
            cards = cardsDAO.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (cards.size() > 0) {
            for (Object card : cards) {
                cardsData.add((Cards) card);
            }
        }

        cardsTable.setItems(getCardsData());

    }

    private void makeTitleOfColumns() {
        tableTopic.setText("Topic");
        tableSpeechPart.setText("Part of speech");
        tableNativeValue.setText("Native value");
        tableNativeExample.setText("Native example");
        tableForeignValue.setText("Foreign value");
        tableForeignExample.setText("Foreign example");
    }

    private void linkToColumns() {
        tableTopic.setCellValueFactory(new PropertyValueFactory<Cards, String>("type"));
        tableTopic.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(p.getValue().getCategory().getName());
            }
        });
        tableSpeechPart.setCellValueFactory(new PropertyValueFactory<Cards, String>("category"));
        tableSpeechPart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(p.getValue().getType().getName());
            }
        });
        tableNativeValue.setCellValueFactory(new PropertyValueFactory<Cards, String>("name"));
        tableNativeExample.setCellValueFactory(new PropertyValueFactory<Cards, String>("example"));
        tableForeignValue.setCellValueFactory(new PropertyValueFactory<Cards, String>("value"));
        tableForeignExample.setCellValueFactory(new PropertyValueFactory<Cards, String>("foreignExample"));
    }

    public ObservableList<Cards> getCardsData() {
        return cardsData;
    }

    public void setCardsData(ObservableList<Cards> cardsData) {
        this.cardsData = cardsData;
    }
}
