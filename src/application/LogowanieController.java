package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LogowanieController {
	public Button log;
	public TextField login;
	public PasswordField haslo;
	public void handle(ActionEvent event) throws IOException {
		switch(Centrala.getInstance().Logowanie(login.getText(), haslo.getText())) {
			case "":
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("B³¹d logowania");
				alert.setHeaderText(null);
				alert.setContentText("Nieprawid³owy login lub has³o");
				alert.showAndWait();
				break;
		
			case "admin":
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Rejestracja.fxml"));
				Scene scene = new Scene(root,900,700);
				System.out.println("COstam");
			    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
			    stageTheEventSourceNodeBelongs.setScene(scene);
			    stageTheEventSourceNodeBelongs.setTitle("Panel rejestracji");
			    break;
			    
			default:
				
			    
			    
			    for(Lekarz a:Centrala.getInstance().getLekarze()) {
			    	if(a.getLogin().equals(login.getText())) {
			    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Panel_lekarza.fxml"));
			    		Panel_lekarzaController.id = a.getId();

						BorderPane root1 = (BorderPane)fxmlLoader.load();

						Scene scene1 = new Scene(root1,900,700);
					    Stage stageTheEventSourceNodeBelongs1 = (Stage) ((Node)event.getSource()).getScene().getWindow();
			    		stageTheEventSourceNodeBelongs1.setTitle("Panel lekarza; zalogowany: dr " + a.getImie() + " " + a.getNazwisko());
			    		
			    		stageTheEventSourceNodeBelongs1.setScene(scene1);
			    		break;
			    	}
			    	
			    }
			    
			    
			    break;
		}
		
		

	}
}
