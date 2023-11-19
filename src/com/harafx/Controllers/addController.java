package com.harafx.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class addController extends wordFormController implements Initializable {

    private void addWord() {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK, ButtonType.CANCEL);
        confirmationAlert.setHeaderText("Do you want to add \"" + targetField.getText() + "\" to your dictionary?");
        confirmationAlert.showAndWait();

        if (confirmationAlert.getResult() == ButtonType.OK) {
            Word word = getDefElement();

            if (word.getTarget() == null || word.getTarget().isEmpty()) {
                showInformationAlert("Information", "Please fill all the fields before adding");
            } else if (!isWordAlreadyExists(word)) {
                printDebugBeforeAndAfter();

                TransferedData.dict.addWord(word);

                saveAndLoadDictionary();

                showInformationAlert("Information", "Your word is added. Please reload the application");
                wrapFormPane.getChildren().clear();
            }
        }
    }

    private boolean isWordAlreadyExists(Word word) {
        if (TransferedData.dict.searchWord(word.getTarget()) != -1) {
            showInformationAlert("Information", "This word is already in your dictionary");
            return true;
        }
        return false;
    }

    private void printDebugBeforeAndAfter() {
        System.out.println("BEFORE==========================");
        TransferedData.dict.getWords().get(TransferedData.dict.size() - 2).debug();
        System.out.println("AFTER==========================");
    }

    private void saveAndLoadDictionary() {
        try {
            TransferedData.dict.saveJson(DICT_PATH);
            TransferedData.dict.loadJson(DICT_PATH);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void showInformationAlert(String title, String message) {
        Alert informationAlert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
        informationAlert.setHeaderText(title);
        informationAlert.showAndWait();
    }

    void initButtonControl() {
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addWord());
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> wrapFormPane.getChildren().clear());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TransferedData.word.clear();
        loadHTML(FORM_PATH);

        driver.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

            @Override
            public void changed(ObservableValue<? extends State> arg0, State arg1, State arg2) {
                if (arg2 == State.SUCCEEDED) {
                    driver.executeScript("addDef()");
                    initButtonControl();
                }
            }
        });
    }

}
