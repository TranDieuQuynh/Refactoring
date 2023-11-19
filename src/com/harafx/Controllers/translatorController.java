package com.harafx.Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Models.TranslateAPI;

import java.awt.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import com.harafx.Models.TranslateAPI;

public class translatorController implements Initializable {

    String[] langs = { "Vietnamese", "English", "Germany", "French", "Russian", "Chinese", "Japanese" };
    String[] langIso = { "vi", "en", "de", "fr", "ru", "zh", "ja" };

    @FXML
    TextArea targetArea = new TextArea();
    @FXML
    TextArea resultArea = new TextArea();
    @FXML
    Button translateButton = new Button();

    @FXML
    ComboBox<String> langFrom = new ComboBox<>();
    @FXML
    ComboBox<String> langTo = new ComboBox<>();

    @FXML
    Hyperlink hypLink = new Hyperlink();

    private void initButtonControl() {
        addTranslateListener();

        hypLink.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://translate.google.com/"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        langFrom.getItems().setAll(langs);
        langTo.getItems().setAll(langs);

        langFrom.getSelectionModel().selectFirst();
        langTo.getSelectionModel().selectFirst();
    }

    private void openGoogleTranslatePage() throws IOException, URISyntaxException {

        
    }

/*
 * String getTranslateRes() throws IOException {
 * int indexFrom = langFrom.getSelectionModel().getSelectedIndex();
 * int indexTo = langTo.getSelectionModel().getSelectedIndex();
 * 
 * if (indexFrom < 0 || indexTo < 0 || indexFrom == indexTo) {
 * Alert alert = new Alert(AlertType.INFORMATION);
 * alert.setHeaderText("Please choose a pair of 2 diffirent langueges");
 * alert.showAndWait();
 * return "";
 * }
 * 
 * return TranslateAPI.translate(langIso[indexFrom], langIso[indexTo],
 * targetArea.getText());
 * }
 * 
 * void addTranslateListener() {
 * translateButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
 * try {
 * String res = new String();
 * res = getTranslateRes();
 * resultArea.setText(res);
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 * });
 * 
 * }
 * 
 * @Override
 * public void initialize(URL location, ResourceBundle resources) {
 * initButtonControl();
 * }
 * 
 * }
 */
