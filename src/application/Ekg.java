package application;

public class Ekg extends Usluga {
	
	public Ekg(Wizyta wizyta) {
		super(wizyta);
	}
	
	public String ustaw_opis() {
		return super.ustaw_opis() + " -Ekg- ";
	}

	
	public Float ustaw_cene() {
		return super.ustaw_cene() + 25;
	}

}

