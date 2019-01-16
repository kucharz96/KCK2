package application;

import java.util.Date;

public abstract class Usluga implements Wizyta {
	 
	
	 protected Wizyta wizyta;
	 protected Usluga(Wizyta wizyta) {
		 this.wizyta = wizyta;
	 }
	 public Wizyta getDekorator()
	 {
	   return wizyta;
	 }
	 public String ustaw_opis() {
		return wizyta.ustaw_opis();
	 }
	 
	 public Float ustaw_cene() {
			return wizyta.ustaw_cene();
		 }
	 
	 @Override
		public int getId() {
			// TODO Auto-generated method stub
			return wizyta.getId();
		}

		@Override
		public int getId_lekarza() {
			// TODO Auto-generated method stub
			return wizyta.getId_lekarza();
		}

		@Override
		public void setId_lekarza(int id_lekarza) {
			wizyta.setId_lekarza(id_lekarza);
		}

		@Override
		public String getPesel_pacjenta() {
			// TODO Auto-generated method stub
			return wizyta.getPesel_pacjenta();
		}

		@Override
		public void setPesel_pacjenta(String pesel_pacjenta) {
			wizyta.setPesel_pacjenta(pesel_pacjenta);
			
		}

		@Override
		public Date getData() {
			// TODO Auto-generated method stub
			return wizyta.getData();
		}

		@Override
		public void setData(String data) {
			// TODO Auto-generated method stub
			wizyta.setData(data);
			
		}

		@Override
		public String getOpis() {
			// TODO Auto-generated method stub
			return wizyta.getOpis();
		}

		@Override
		public void setCena(Float cena) {
			wizyta.setCena(cena);
		}
		public Float getCena() {
			// TODO Auto-generated method stub
			return wizyta.getCena();
		}

		@Override
		public void setOpis(String opis) {
			wizyta.setOpis(opis);
		}


}
