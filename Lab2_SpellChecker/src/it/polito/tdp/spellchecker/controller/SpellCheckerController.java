package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SpellCheckerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextFlow txtCheck;

    @FXML
    private TextArea txtInsert;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button btnCheck;

    @FXML
    private Button btnClear;
    @FXML
    private Label labTip;
    
    Dictionary d;
    

    @FXML
    void doClearText(ActionEvent event) {
    	txtInsert.clear();
        txtCheck.getChildren().clear();
    	labTip.setVisible(false);
    	
    
    	

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	List<String> instl = new LinkedList<String>();
    	String insert = txtInsert.getText().toLowerCase();
    	StringTokenizer st = new StringTokenizer(insert);
    	while(st.hasMoreTokens()){
    		instl.add(st.nextToken());
    	}
    	
    	if(choiceBox.getValue().equals("ENGLISH")){
    		d= new EnglishDictionary();
    	}else if(choiceBox.getValue().equals("ITALIANO")){
    		d= new ItalianDictionary();
    		
    	}
    	
    	d.loadDictionary();
    	List<RichWord> result = d.spellCheckText(instl);
        if(result!=null){
    		labTip.setVisible(true);
    		for (RichWord r : result ){
    			Text t = new Text(r.getWord()+" ");
    			
    			if (!r.isCheck())
    				t.setFill(Color.RED);
    			
    		txtCheck.getChildren().add(t);
    		
    		
    		}
    	
    	
    	
    	
        }
    
    	

    }

    @FXML
    void initialize() {
    	
        assert txtCheck != null : "fx:id=\"txtCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labTip != null : "fx:id=\"labTip\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        choiceBox.getItems().addAll("ENGLISH", "ITALIANO");
        

    }
}

