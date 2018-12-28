package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Niezalogowany implements Stan {

	private Centrala C;
	public Niezalogowany(Centrala a) {
		C = a;
	}
	@Override
	public void logowanie(String login, String haslo,Button b, ActionEvent event) {
		boolean decision = true;
		if(login.equals("admin") && haslo.equals("admin")) {
			C.setStan(new ZalogowanaRejestracja(C));
			decision = false;
		}
		
		else {
			for(Lekarz a: C.getLekarze()) {
				if(login.equals(a.getLogin()) && haslo.equals(a.getHaslo())) {
						C.setStan(new ZalogowanyLekarz(C));
						decision = false;
						break;
				}
			}
			
			
		}
		if(decision)
			C.setStan(new BladLogowania(C));
		
		C.Logowanie(login, haslo,b,event);
		
		
	}

}
