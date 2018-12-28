package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class BladLogowania implements Stan {
	Centrala C;

	public BladLogowania(Centrala a) {
		C = a;
	}
	@Override
	public void logowanie(String login, String haslo, Button b, ActionEvent event) {
		boolean calert = false;
		for(Lekarz a:C.getLekarze()) {
			if(login.equals(a.getLogin())) {
				calert = true;
			break;
			}
		}
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B��d logowania");
		alert.setHeaderText(null);
		if(calert)
			alert.setContentText("Nieprawid�owe has�o");
		else
			alert.setContentText("Nieprawid�owy login lub has�o");

		alert.showAndWait();
		
		C.setStan(new Niezalogowany(C));
		
	}

}
