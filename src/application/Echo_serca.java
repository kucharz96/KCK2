package application;

	public class Echo_serca extends Usluga {
		
		public Echo_serca(Wizyta wizyta) {
			super(wizyta);
		}
		
		public String ustaw_opis() {
			return super.ustaw_opis() + " -Echo serca- ";
		}
		
		public Float ustaw_cene() {
			return super.ustaw_cene() + 80;
		}
		
	

}
