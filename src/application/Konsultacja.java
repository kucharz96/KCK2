package application;

import java.util.Date;

public class Konsultacja extends Usluga {
	
	public Konsultacja(Wizyta wizyta) {
		super(wizyta);
	}
	
	public String ustaw_opis() {
		return super.ustaw_opis() + " -Konsultacja- ";
	}
	
	public Float ustaw_cene() {
		return super.ustaw_cene() + 100;
	}

	

	
}
