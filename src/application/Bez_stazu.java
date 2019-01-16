package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Bez_stazu implements Rodzaj_lekarza {

	@Override
	public boolean czy_dodac_wizyte(Wizyta w, Lekarz l) {
		
			int iter = 0;
			for(Wizyta a : l.getWizyty()) {
				if(w.getData().getDay() == a.getData().getDay() && w.getData().getMonth() == a.getData().getMonth())
					iter ++;
			}
			if(iter >= 10) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("B³¹d dodania wizyty");
				alert.setHeaderText(null);
				alert.setContentText("Przekroczono limit wizyt dziennych lekarza");

				alert.showAndWait();
				
				
				return false;
				
			} 
				
			
			return true;
		
	}

}
