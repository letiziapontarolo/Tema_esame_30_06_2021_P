package it.polito.tdp.genes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {

    }

    @FXML
    void doStatistiche(ActionEvent event) {
    	
    	String loc = boxLocalizzazione.getSelectionModel().getSelectedItem();
    	 if (loc == null) {
    	 txtResult.appendText("\nPerfavore seleziona una localizzazione!\n");
    	 return;
    	 }
    	 
    	 txtResult.appendText("\nADIACENTI A: " + loc + "\n");
    	 txtResult.appendText(this.model.localizzazioniConnesse(loc));
    	 

    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxLocalizzazione.getItems().clear();
		txtResult.clear();
		this.model.creaGrafo();
		txtResult.appendText("Grafo creato!\n");
		txtResult.appendText("#VERTICI: " + this.model.numeroVertici() + "\n");
		txtResult.appendText("#ARCHI: " + this.model.numeroArchi() + "\n");
		boxLocalizzazione.getItems().addAll(this.model.listaLocalizzazioni());

		
		
	}
}
