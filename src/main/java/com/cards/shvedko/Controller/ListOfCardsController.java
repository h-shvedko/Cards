package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardsDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
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
            crashAppeared(e.getMessage());
        }

        if (cards.size() > 0) {
            for (Object card : cards) {
                cardsData.add((Cards) card);
            }
        }

        cardsTable.setItems(getCardsData());

        initializeContentMenu();

    }

    // Create ContextMenu
    private void initializeContentMenu() {

        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Редактировать");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                goToPage("", "", getCardsData());
            }
        });
        MenuItem item2 = new MenuItem("Удалить");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                goToPage("", "", getCardsData());
            }
        });

        contextMenu.getItems().addAll(item1, item2);

        cardsTable.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(cardsTable, event.getScreenX(), event.getScreenY());
            }
        });
    }

    @Override
    protected void handleCancelButtonAction() {

    }

    @Override
    protected void handleSubmitButtonAction() {

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
        tableForeignValue.setCellValueFactory(new PropertyValueFactory<Cards, String>("foreignName"));
        tableForeignExample.setCellValueFactory(new PropertyValueFactory<Cards, String>("foreignExample"));
    }

    public ObservableList<Cards> getCardsData() {
        return cardsData;
    }

    public void setCardsData(ObservableList<Cards> cardsData) {
        this.cardsData = cardsData;
    }
}
