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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class editController extends wordFormController implements Initializable {

    private void saveAndLoadDictionary() {
        try {
            TransferedData.dict.saveJson(DICT_PATH);
            TransferedData.dict.loadJson(DICT_PATH);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(AlertType alertType, String headerText, String contentText) {
        Alert alert = new Alert(alertType, contentText, ButtonType.OK);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private void applyChange() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.OK, ButtonType.CANCEL);
        alert.setHeaderText("Do you want to apply your change to \"" + targetField.getText() + "\"?");

        if (alert.getResult() == ButtonType.OK) {
            Word word = getDefElement();
            System.out.println(TransferedData.wordIndex);
            System.out.println(TransferedData.dict.size());
            TransferedData.dict.removeWord(TransferedData.wordIndex);
            System.out.println(TransferedData.dict.size());
            TransferedData.dict.addWord(word);

            saveAndLoadDictionary();

            showAlert(AlertType.INFORMATION, "Information", "Your change is applied. Please reload the application");
            wrapFormPane.getChildren().clear();
        }
    }

    private void initButtonControl() {
        okButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> applyChange());
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> wrapFormPane.getChildren().clear());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        driver.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {

            @Override
            public void changed(ObservableValue<? extends State> arg0, State arg1, State arg2) {
                if (arg2 == State.SUCCEEDED) {
                    driver.executeScript("addDef()");
                    initButtonControl();
                }
            }
        });
        loadHTML(FORM_PATH);
        initButtonControl();
    }
}
