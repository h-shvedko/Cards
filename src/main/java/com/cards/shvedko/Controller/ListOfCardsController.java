package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.Cards;
import com.cards.shvedko.ModelDAO.CardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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

    @FXML
    public TableColumn<Cards, String> tableSounds;
    @FXML
    public TableColumn<Cards, String> tableIsAnchor;
    @FXML
    public TableColumn<Cards, String> tableIsLearnt;

    private ObservableList<Cards> cardsData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
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
                                if (newItem != null) {
                                    if (newItem.getIsVisible() != ModelsDAO.READY_ON) {
                                        row.setStyle("-fx-background-color: indianred");
                                        Tooltip tooltip = new Tooltip("Эта карточка удалена");
                                        tooltip.setStyle("-fx-font-size: 16px;");
                                        row.setTooltip(tooltip);
                                    } else {
                                        row.setStyle("");
                                        Tooltip tooltip = new Tooltip("Эта карточка активна");
                                        tooltip.setStyle("-fx-font-size: 16px;");
                                        row.setTooltip(tooltip);
                                    }
                                }
                            }
                        });

                        row.hoverProperty().addListener(new InvalidationListener() {
                            @Override
                            public void invalidated(Observable observable) {
                                Cards cardsValue = row.getItem();
                                if (row.isHover()) {
                                    if (cardsValue != null) {
                                        row.setStyle("-fx-background-color: cornflowerblue");
                                    }
                                } else {
                                    if (cardsValue != null) {
                                        if (cardsValue.getIsVisible() == ModelsDAO.READY_ON) {
                                            row.setStyle("");
                                        } else {
                                            row.setStyle("-fx-background-color: indianred");
                                        }
                                    }
                                }
                            }
                        });
                        return row;
                    }
                });

                initializeContentMenu();

                return null;
            }
        };

        showLoadingSplashProgress(task);

        Thread thread = new Thread(task);
        thread.start();

    }

    // Create ContextMenu
    private void initializeContentMenu() {

        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Редактировать");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                switch (selectedItem.getType().getName()) {
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

        MenuItem item2 = new MenuItem("Удалить/Восстановить");
        item2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Cards selectedItem = cardsTable.getSelectionModel().getSelectedItem();
                goToPage("modalRemoveCard.fxml", "Удаление/восстановление карточки", selectedItem);
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
        tableTopic.setText("Тема");
        tableSpeechPart.setText("Часть речи");
        tableNativeValue.setText("Слово (рус)");
        tableForeignValue.setText("Перевод (нем)");
        tableSounds.setText("Озвучено");
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
        tableForeignValue.setCellValueFactory(new PropertyValueFactory<Cards, String>("foreignName"));
        tableSounds.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Cards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Cards, String> p) {
                return new SimpleStringProperty(getNumberOfSounds(p.getValue()));
            }
        });
    }

    private String getNumberOfSounds(Cards card) {
        int cnt = 0;

        if (card.getNameVoice() != null && !card.getNameVoice().equals("")) {
            ++cnt;
        }
        if (card.getExampleVoice() != null && !card.getExampleVoice().equals("")) {
            ++cnt;
        }
        if (card.getForeignNameVoice() != null && !card.getForeignNameVoice().equals("")) {
            ++cnt;
        }
        if (card.getForeignExampleVoice() != null && !card.getForeignExampleVoice().equals("")) {
            ++cnt;
        }

        return cnt + "/4";
    }

    public ObservableList<Cards> getCardsData() {
        return cardsData;
    }

    public void setCardsData(ObservableList<Cards> cardsData) {
        this.cardsData = cardsData;
    }
}
