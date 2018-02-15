package com.cards.shvedko.Controller;

import com.cards.shvedko.Model.TmpCards;
import com.cards.shvedko.ModelDAO.TmpCardsDAO;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TmpListOfCardsController extends A_Controller {

    @FXML
    public TableColumn<TmpCards, String> tmpTranslatedWord;
    @FXML
    public TableColumn<TmpCards, String> tmpOriginalWord;
    @FXML
    public TableColumn<TmpCards, String> tmpIsPlural;
    @FXML
    public TableColumn<TmpCards, String> tmpThirdFace;
    @FXML
    public TableColumn<TmpCards, String> tmpPreteritum;
    @FXML
    public TableColumn<TmpCards, String> tmpPerfect;
    @FXML
    public TableColumn<TmpCards, String> tmpTranslatedExample;
    @FXML
    public TableColumn<TmpCards, String> tmpOriginalExample;
    @FXML
    public TableColumn<TmpCards, String> tmpCategory;
    @FXML
    public TableColumn<TmpCards, String> tmpType;
    @FXML
    public TableColumn<TmpCards, String> tmpNouneType;
    @FXML
    public TableColumn<TmpCards, String> tmpPerfectWithHaben;
    @FXML
    public TableColumn<TmpCards, String> tmpReflexive;
    @FXML
    public TableColumn<TmpCards, String> tmpTrembare;
    @FXML
    public TableColumn<TmpCards, String> tmpRegelmessig;
    @FXML
    public TableColumn<TmpCards, String> tmpAkkusative;
    @FXML
    public TableColumn<TmpCards, String> tmpDative;
    @FXML
    public TableColumn<TmpCards, String> tmpGenetive;

    private ObservableList<TmpCards> tmpCardsData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        linkToColumns();
        makeTitleOfColumns();

        TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();

        List tmpCards = new ArrayList();
        try {
            tmpCards = tmpCardsDAO.selectAll();
        } catch (Exception e) {
            crashAppeared(e.getMessage());
        }

        if (tmpCards.size() > 0) {
            for (Object tmpCard : tmpCards) {
                tmpCardsData.add((TmpCards) tmpCard);
            }
        }

        tmpCardsTable.setItems(getTmpCardsData());

        tmpCardsTable.setRowFactory(new Callback<TableView<TmpCards>, TableRow<TmpCards>>() {
            @Override
            public TableRow<TmpCards> call(TableView<TmpCards> tv) {
                final TableRow<TmpCards> row = new TableRow<>();
                row.hoverProperty().addListener(new InvalidationListener() {
                    @Override
                    public void invalidated(Observable observable) {
                        TmpCards cardsValue = row.getItem();
                        if (row.isHover()) {
                            if (cardsValue != null) {
                                row.setStyle("-fx-background-color: cornflowerblue");
                            }
                        } else {
                            row.setStyle("-fx-background-color: indianred");
                        }
                    }
                });
                return row;
            }
        });

//        initializeContentMenu();

    }

    // Create ContextMenu
    private void initializeContentMenu() {

        final ContextMenu contextMenu = new ContextMenu();

        MenuItem item1 = new MenuItem("Редактировать");
        item1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TmpCards selectedItem = tmpCardsTable.getSelectionModel().getSelectedItem();
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
                TmpCards selectedItem = tmpCardsTable.getSelectionModel().getSelectedItem();
                goToPage("modalRemoveCard.fxml", "Удаление/восстановление карточки", selectedItem);
            }
        });

        contextMenu.getItems().addAll(item1, item2);

        tmpCardsTable.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                contextMenu.show(tmpCardsTable, event.getScreenX(), event.getScreenY());
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
        tmpTranslatedWord.setText("Русский перевод");
        tmpOriginalWord.setText("Немецкий перевод");
        tmpTranslatedExample.setText("Русский пример");
        tmpOriginalExample.setText("Немецкий пример");
        tmpCategory.setText("Категория");
        tmpType.setText("Тип");
        tmpIsPlural.setText("Множественное число");
        tmpThirdFace.setText("Форма глагола в 3ем лице");
        tmpPreteritum.setText("Präteritum");
        tmpPerfect.setText("Perfect");
        tmpPerfect.setText("Perfect");
        tmpNouneType.setText("Тип сущ");
        tmpPerfectWithHaben.setText("Perfect mit haben");
        tmpReflexive.setText("Возвратный");
        tmpTrembare.setText("С отделяемой приставкой");
        tmpRegelmessig.setText("Правильный");
        tmpAkkusative.setText("Предлог аккузатива");
        tmpDative.setText("Предлог датива");
        tmpGenetive.setText("Предлог генетива");
    }

    private void linkToColumns() {


        tmpTranslatedWord.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("name"));
        tmpOriginalWord.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("foreign_name"));
        tmpTranslatedExample.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("example"));
        tmpOriginalExample.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("foreign_example"));

        tmpCategory.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("category"));
        tmpCategory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TmpCards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TmpCards, String> p) {
                return new SimpleStringProperty(p.getValue().getCategory().getName());
            }
        });
    }

    public ObservableList<TmpCards> getTmpCardsData() {
        return tmpCardsData;
    }

    public void setTmpCardsData(ObservableList<TmpCards> cardsData) {
        this.tmpCardsData = cardsData;
    }
}
