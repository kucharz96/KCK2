package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Szczegoly_lekarzeController {
	@FXML
	private Label imie, nazwisko, login, haslo, wiek, sala, telefon;
	@FXML
	private Button  p_ok;
	public void set_labels(Lekarz a) {
		imie.setText(a.getImie());
		nazwisko.setText(a.getNazwisko());
		wiek.setText(Integer.toString(a.getWiek()));
		login.setText(a.getLogin());
		haslo.setText(a.getHaslo());
		sala.setText(Integer.toString(a.getSala()));
		telefon.setText(a.getTelefon());
		
		
	}
	public void zamknijOkno(ActionEvent event)
	{
		   Stage stage = (Stage) p_ok.getScene().getWindow();
		   stage.close();
	}


}