package application;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public interface Wizyta {
	
	public int getId();
	public int getId_lekarza();
	public void setId_lekarza(int id_lekarza);
	public String getPesel_pacjenta();
	public void setPesel_pacjenta(String pesel_pacjenta);
	public Date getData();
	public void setData(String data);
	public String getOpis();
	public void setOpis(String opis);
	public Float getCena();
	public void setCena(Float cena);
	
	public String ustaw_opis();
	public Float ustaw_cene();
	
	
}
