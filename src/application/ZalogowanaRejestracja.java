package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ZalogowanaRejestracja implements Stan {
	private static Centrala C;
	
	public ZalogowanaRejestracja(Centrala a) {
		C = a;
	}
	
	@Override
	public void logowanie(String login, String haslo,Button b, ActionEvent event) {
	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Rejestracja.fxml"));
		
		
	
			BorderPane root = null;
			try {
				root = (BorderPane)loader.load();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Scene scene = new Scene(root,900,700);
			
			
			Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
			
			stageTheEventSourceNodeBelongs.setScene(scene);
		    stageTheEventSourceNodeBelongs.setTitle("Panel rejestracji");
		   
		    
		}		
	

}
