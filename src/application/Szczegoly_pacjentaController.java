package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Szczegoly_pacjentaController {
	@FXML
	private Label l_pesel, l_imie, l_nazwisko, l_wiek, l_ulica, l_numer_domu, l_numer_mieszkania, l_miejscowosc ;
	@FXML
	private Button  p_ok;
	public void set_labels(Pacjent a) {
		l_pesel.setText(a.getPesel());
		l_imie.setText(a.getImie());
		l_nazwisko.setText(a.getNazwisko());
		l_wiek.setText(Integer.toString(a.getWiek()));
		l_ulica.setText(a.getUlica());
		l_numer_domu.setText(Integer.toString(a.getNr_domu()));
		l_numer_mieszkania.setText(Integer.toString(a.getNr_mieszkania()));
		l_miejscowosc.setText(a.getMiejscowosc());

		
		
		
		
	}
	public void zamknijOkno(ActionEvent event)
	{
		   Stage stage = (Stage) p_ok.getScene().getWindow();
		   stage.close();
	}


}
