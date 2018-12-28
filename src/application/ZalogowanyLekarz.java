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

public class ZalogowanyLekarz implements Stan {
	private Centrala C;

	public ZalogowanyLekarz(Centrala a) {
		C = a;
	}
	@Override
	public void logowanie(String login, String haslo, Button b, ActionEvent event) {
		Lekarz tmp = null;
		for(Lekarz a:C.getLekarze()) {
			if(login.equals(a.getLogin()))
			tmp = a;
		}
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Loading.fxml"));
		Stage stage = new Stage();
	    stage.initOwner(b.getScene().getWindow());
	    
	    try {
			stage.setScene(new Scene((Parent) fxmlLoader.load()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		stage.setTitle("£adowanie ustawieñ...");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.showAndWait();
		
		LoeadingController controller2 = fxmlLoader.getController();
		System.out.println(controller2.setItems());
		if(controller2.setItems() == 1)
			{
				System.out.println("XD");
				stage.close();
				Panel_lekarzaController.id = tmp.getId();
	    		/////////////
	    		FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("Panel_lekarza.fxml"));

				BorderPane root1 = null;
				try {
					root1 = (BorderPane)fxmlLoader1.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene2 = new Scene(root1);
			    Stage stageTheEventSourceNodeBelongs2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    		stageTheEventSourceNodeBelongs2.setTitle("Panel lekarza; zalogowany: dr " + tmp.getImie() + " " + tmp.getNazwisko());
	    		
	    		stageTheEventSourceNodeBelongs2.setScene(scene2);
		
			}

		}
}
