package com.harafx.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.harafx.Models.TransferedData;
import com.harafx.Models.Word;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class meaningController extends wordFormController implements Initializable {

    Word word = new Word();

    @FXML
    Pane usAudioButton = new Pane();
    @FXML
    Pane ukAudioButton = new Pane();

    @FXML
    Label usPronunciation = new Label();
    @FXML
    Label ukPronunciation = new Label();

    void playAudio(String url) {
        // Media sound = new Media(url);
        // MediaPlayer mediaPlayer = new MediaPlayer(sound);
        AudioClip mediaPlayer = new AudioClip(url);

        mediaPlayer.play();
        System.out.println(url);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Word word = TransferedData.dict.getWords().get(TransferedData.wordIndex);

        usPronunciation.setText("/" + word.getIpa().getUs() + "/");
        ukPronunciation.setText("/" + word.getIpa().getUk() + "/");

        usAudioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            playAudio(word.getAudio().getUs());
        });

        ukAudioButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            playAudio(word.getAudio().getUk());
        });

        loadHTML(MEANING_PATH);
    }

}
