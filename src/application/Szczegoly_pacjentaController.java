package application;

import javafx.scene.control.Label;

public class Szczegoly_pacjentaController {
	public Label pesel, imie, nazwisko, wiek, ulica, nr_domu, nr_mieszkania, miejscowosc ;
	public void set_labels(Pacjent a) {
		pesel.setText(a.getPesel());
		imie.setText(a.getImie());
		nazwisko.setText(a.getNazwisko());
		wiek.setText(Integer.toString(a.getWiek()));
		ulica.setText(a.getUlica());
		nr_domu.setText(Integer.toString(a.getNr_domu()));
		nr_mieszkania.setText(Integer.toString(a.getNr_mieszkania()));
		miejscowosc.setText(a.getMiejscowosc());

		
		
		
		
	}
	


}
