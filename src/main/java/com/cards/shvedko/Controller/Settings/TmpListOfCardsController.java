package com.cards.shvedko.Controller.Settings;

import com.cards.shvedko.Controller.A_Controller;
import com.cards.shvedko.Helpers.FillDatabase;
import com.cards.shvedko.Helpers.ReadCSV;
import com.cards.shvedko.Model.TmpCards;
import com.cards.shvedko.ModelDAO.ModelsDAO;
import com.cards.shvedko.ModelDAO.TmpCardsDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.util.Callback;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TmpListOfCardsController extends A_Controller {

    @FXML
    public TableColumn<TmpCards, String> tmpLevel;
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
    @FXML
    public TableColumn<TmpCards, Boolean> tmpProceed;
    @FXML
    public Button importButton;
    @FXML
    public Label fileName;
    @FXML
    public Button importFinalButton;

    private ObservableList<TmpCards> tmpCardsData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        tmpCardsTable.setEditable(true);

        importFinalButton.setVisible(false);
        linkToColumns();
        makeTitleOfColumns();

        fulfilTableWithData();
    }

    /**
     *
     */
    private void fulfilTableWithData() {

        clearData();

        TmpCardsDAO tmpCardsDAO = new TmpCardsDAO();

        List tmpCards = new ArrayList();
        try {
            tmpCards = tmpCardsDAO.selectAll();
            tmpCardsDAO.closeSession();
        } catch (Exception e) {
            crashAppeared(e.getMessage(), new ActionEvent());
        }

        if (tmpCards.size() > 0) {
            for (Object tmpCard : tmpCards) {
                tmpCardsData.add((TmpCards) tmpCard);
            }
            importFinalButton.setVisible(true);
        }

        if (getTmpCardsData().size() > 0) {
            tmpCardsTable.setTableMenuButtonVisible(true);
            tmpCardsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
                                if (cardsValue != null) {
                                    row.setStyle("");
                                }
                            }
                        }
                    });
                    return row;
                }
            });
        }
    }

    private void clearData() {
        tmpCardsData.clear();
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
                goToPage("Modals/modalRemoveCard.fxml", "Удаление/восстановление карточки", selectedItem);
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
        tmpLevel.setText("Уровень");
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
        tmpNouneType.setText("Тип сущ");
        tmpPerfectWithHaben.setText("Perfect mit haben");
        tmpReflexive.setText("Возвратный");
        tmpTrembare.setText("С отделяемой приставкой");
        tmpRegelmessig.setText("Правильный");
        tmpAkkusative.setText("Предлог аккузатива");
        tmpDative.setText("Предлог датива");
        tmpGenetive.setText("Предлог генетива");
        tmpProceed.setText("Импортировать");
    }

    private void linkToColumns() {
        tmpLevel.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("level"));
        tmpLevel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TmpCards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TmpCards, String> p) {
                if (p.getValue().getLevel() != null) {
                    return new SimpleStringProperty(p.getValue().getLevel().getName());
                }
                return new SimpleStringProperty("");
            }
        });

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

        tmpType.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("type"));
        tmpType.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TmpCards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TmpCards, String> p) {
                return new SimpleStringProperty(p.getValue().getType().getName());
            }
        });

        tmpIsPlural.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("plural_endung"));
        tmpThirdFace.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("foreign_nama_infinitive"));
        tmpPreteritum.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("foreign_name_preteritum"));
        tmpPerfect.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("foreign_name_perfect"));
        tmpNouneType.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("kind_of_noun"));
        tmpPerfectWithHaben.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("is_perfect_with_haben"));
        tmpReflexive.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("is_reflexive_verb"));
        tmpTrembare.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("is_trembare_prefix_verb"));
        tmpRegelmessig.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("is_regular_verb"));

        tmpAkkusative.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("preposition_akk"));
        tmpAkkusative.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TmpCards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TmpCards, String> p) {
                if (p.getValue().getPrepositionAkk() != null) {
                    return new SimpleStringProperty(p.getValue().getPrepositionAkk().getName());
                }

                return new SimpleStringProperty("");
            }
        });

        tmpDative.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("preposition_dat"));
        tmpDative.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<TmpCards, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<TmpCards, String> p) {
                if (p.getValue().getPrepositionDativ() != null) {
                    return new SimpleStringProperty(p.getValue().getPrepositionDativ().getName());
                }
                return new SimpleStringProperty("");
            }
        });

        tmpGenetive.setCellValueFactory(new PropertyValueFactory<TmpCards, String>("preposition_gen"));

        tmpProceed.setCellValueFactory(new PropertyValueFactory<TmpCards, Boolean>("proceed"));
        tmpProceed.setCellFactory(new Callback<TableColumn<TmpCards, Boolean>, TableCell<TmpCards, Boolean>>() {
            @Override
            public TableCell<TmpCards, Boolean> call(TableColumn<TmpCards, Boolean> param) {
                return new CheckBoxTableCell<TmpCards, Boolean>() {
                    {
                        setAlignment(Pos.CENTER);
                    }
                };
            }
        });
        tmpProceed.setEditable(true);
    }

    public ObservableList<TmpCards> getTmpCardsData() {
        return tmpCardsData;
    }

    public void setTmpCardsData(ObservableList<TmpCards> cardsData) {
        this.tmpCardsData = cardsData;
    }

    public void handleCancelButton(ActionEvent actionEvent) {
        this.goToPage("Settings/settings.fxml", A_Controller.SETTINGS_PAGE_TITLE, "");
    }

    /**
     * @param actionEvent
     */
    public void importFileAction(ActionEvent actionEvent) {
        try {
            //TODO: to export from Excel use option save as ->"Unicode text (*.txt)" and then rename file to .csv and convert to UTF-8
            ReadCSV.read();
            //FillDatabase.validateData(ReadCSV.fileContent);

            Task<Void> task = new Task<Void>() {
                @Override
                public Void call() throws Exception {
                    FillDatabase.fillCardsFromCSV(ReadCSV.fileContent);
                    fulfilTableWithData();
                    return null ;
                }
            };
            showSplashProgressFoImport(actionEvent, task);

            Thread thread = new Thread(task);
            thread.start();
        } catch (Exception e) {
            crashAppeared(e.getMessage(), actionEvent);
        }
        fileName.setText(ReadCSV.fileNameValue);

    }

    public void handleImportFinalButton(ActionEvent actionEvent) throws UnsupportedEncodingException {

        ObservableList<TmpCards> values = tmpCardsTable.getItems();
        if(values.size() > 0){
            FillDatabase fillDatabase = new FillDatabase();
            try{
                Task<Void> task = new Task<Void>() {
                    @Override
                    public Void call() throws Exception {

                        fillDatabase.fillMainTableFromTmpTable(values, actionEvent);
                        return null ;
                    }
                };
                showSplashProgressFoImport(actionEvent, task);

                Thread thread = new Thread(task);
                thread.start();
            } catch (Exception e){
                crashAppeared(e.getMessage(), actionEvent);
            }
        }
    }
}
