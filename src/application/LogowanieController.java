package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.stage.Modality;
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
	    		FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("Loading.fxml"));
	    		Stage stage3 = new Stage();
	    	    stage3.initOwner(log.getScene().getWindow());
	    		stage3.setScene(new Scene((Parent) fxmlLoader3.load()));
	    		stage3.setTitle("£adowanie ustawieñ...");
	    		stage3.initModality(Modality.WINDOW_MODAL);
	    		stage3.showAndWait();
	    		
				LoeadingController controller = fxmlLoader3.getController();
				System.out.println(controller.setItems());
				if(controller.setItems() == 1)
				{
					BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Rejestracja.fxml"));
					Scene scene = new Scene(root,900,700);
					System.out.println("COstam");
				    Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
				    stageTheEventSourceNodeBelongs.setScene(scene);
				    stageTheEventSourceNodeBelongs.setTitle("Panel rejestracji");
				    break;
				}
			    
			default:
			    for(Lekarz a:Centrala.getInstance().getLekarze()) {
			    	if(a.getLogin().equals(login.getText())) {
			    		
			    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Loading.fxml"));
			    		Stage stage = new Stage();
			    	    stage.initOwner(log.getScene().getWindow());
			    		stage.setScene(new Scene((Parent) fxmlLoader.load()));
			    		stage.setTitle("£adowanie ustawieñ...");
			    		stage.initModality(Modality.WINDOW_MODAL);
			    		stage.showAndWait();
			    		
						LoeadingController controller2 = fxmlLoader.getController();
						System.out.println(controller2.setItems());
						if(controller2.setItems() == 1)
							{
								System.out.println("XD");
								stage.close();
								Panel_lekarzaController.id = a.getId();
					    		/////////////
					    		FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Panel_lekarza.fxml"));

								BorderPane root1 = (BorderPane)fxmlLoader1.load();
								Scene scene2 = new Scene(root1);
							    Stage stageTheEventSourceNodeBelongs2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
					    		stageTheEventSourceNodeBelongs2.setTitle("Panel lekarza; zalogowany: dr " + a.getImie() + " " + a.getNazwisko());
					    		
					    		stageTheEventSourceNodeBelongs2.setScene(scene2);
					    		break;
								
							}
    		
			    	}
			    	
			    }
			    
			    
			    break;
		}
		
		

	}
}
