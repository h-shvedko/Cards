package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.util.Callback;

import javax.smartcardio.Card;
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

        cardsTable.setRowFactory(new Callback<TableView<Cards>, TableRow<Cards>>() {
            @Override
            public TableRow<Cards> call(TableView<Cards> tv) {
                final TableRow<Cards> row = new TableRow<>();
                row.itemProperty().addListener(new ChangeListener<Cards>() {
                    @Override
                    public void changed(ObservableValue<? extends Cards> obs, Cards oldItem, Cards newItem) {
                        if (newItem.getIsVisible() != ModelsDAO.READY_ON) {
                            row.setStyle("-fx-background-color: indianred");
                        }
                    }
                });
                return row;
            }
        });

        initializeContentMenu();

    }

    // Create ContextMenu
    private void initializeContentMenu() {

        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Редактировать");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                switch (selectedItem.getType().getName()){
                    case ModelsDAO.NOUN:
                        goToPage("editCardNoun.fxml", A_Controller.EDIT_NOUN_PAGE, selectedItem);
                        break;
                    case ModelsDAO.VERB:
                        goToPage("editCardVerb.fxml", A_Controller.EDIT_VERB_PAGE, selectedItem);
                        break;
                    case ModelsDAO.ADJECTIVE:
                        goToPage("editCardAdjective.fxml", A_Controller.EDIT_ADJECTIVE_PAGE, selectedItem);
                        break;
                    case ModelsDAO.ADVERB:
                        goToPage("editCardAdverb.fxml", A_Controller.EDIT_ADVERB_PAGE, selectedItem);
                        break;
                    case ModelsDAO.NUMERAL:
                        goToPage("editCardNumeral.fxml", A_Controller.EDIT_NUMERAL_PAGE, selectedItem);
                        break;
                    case ModelsDAO.PARTICIPLE:
                        goToPage("editCardParticiple.fxml", A_Controller.EDIT_PARTICIPLE_PAGE, selectedItem);
                        break;
                    case ModelsDAO.PRONOUN:
                        goToPage("editCardPronoun.fxml", A_Controller.EDIT_PRONOUN_PAGE, selectedItem);
                        break;
                    default:
                        goToPage("editCardOther.fxml", A_Controller.EDIT_OTHER_PAGE, selectedItem);
                        break;
                }
            }
        });

        MenuItem item2 = new MenuItem("Удалить");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                goToPage("modalRemoveCard.fxml", "Удаление карточки", selectedItem);
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
        active.setText("Active");
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
        active.setCellValueFactory(new PropertyValueFactory<Cards, Integer>("isVisible"));
    }

    public ObservableList<Cards> getCardsData() {
        return cardsData;
    }

    public void setCardsData(ObservableList<Cards> cardsData) {
        this.cardsData = cardsData;
    }
}
