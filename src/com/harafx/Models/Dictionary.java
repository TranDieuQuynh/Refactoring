package com.harafx.Models;

import com.harafx.Models.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Dictionary {
    private ArrayList<String> targetList = new ArrayList<>();

    private ArrayList<Word> words = new ArrayList<>();

    public Dictionary() {
    }

    public Dictionary(Dictionary dict) {
        this.words = dict.getWords();
        this.targetList = dict.getTargetList();
    }

    public ArrayList<String> getTargetList() {
        return targetList;
    }

    public void setTargetList(ArrayList<String> targetList) {
        this.targetList = targetList;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public void loadJson(String path) throws FileNotFoundException, IOException, ParseException {
        JSONArray wordsJA = Json.loadArrayFromFile(path);
        for (Object wordtmp : wordsJA) {
            Word word = new Word();
            word.convertFromJson((JSONObject) wordtmp);
            words.add(word);
            targetList.add(word.getTarget());
            // System.out.println("==========================================");
            // word.debug();
        }
    }

    public void saveJson(String path) throws IOException {
        JSONArray dictJA = new JSONArray();
        for (Word word : words) {
            dictJA.add(word.convertToJson());
        }
        PrintWriter pw = new PrintWriter(path);
        pw.write(dictJA.toJSONString());
        pw.flush();
        pw.close();
    }

    public int searchWord(String target) {
        if (target == "" || target == null) {
            return -1;
        }
        for (int i = 0; i < targetList.size(); i++) {
            if (target.equals(targetList.get(i))) {
                return i;
            }
        }
        return -1;
    }
}
