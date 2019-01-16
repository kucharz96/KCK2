package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Lekarz {
	private static int total_id = 0;
	private int id;
	private String login;
	private String haslo;
	private String imie;
	private String nazwisko;
	private int wiek;
	private int sala;
	private String telefon;
	private Rodzaj_lekarza rodzaj;
	private List<Recepta> recepty = new ArrayList<Recepta>();
	private List<Skierowanie> skierowania = new ArrayList<Skierowanie>();
	private List<Wizyta> wizyty = new ArrayList<Wizyta>();
	
public boolean czy_dodac_wizyte(Wizyta w, Lekarz l) {
		
		for(Wizyta a:l.getWizyty()) {

			if(a.getData().equals(w.getData())) {
				Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("B³¹d dodania wizyty");
			alert.setHeaderText(null);
			alert.setContentText("Lekarz zajêty");
			alert.showAndWait();
			return false;
			
			}		
		}
		
		return rodzaj.czy_dodac_wizyte(w, this);
		
	}
	
	public Lekarz(String login, String haslo, String imie, String nazwisko, int wiek, int sala, String telefon, boolean staz)
	{
		this.id = total_id++;
		this.login = login;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.sala = sala;
		this.telefon = telefon;
		if(staz)
			rodzaj = new Staz();
		else
			rodzaj = new Bez_stazu();
	}

	public List<Recepta> getRecepty() {
		return recepty;
	}
	public List<Skierowanie> getSkierowania() {
		return skierowania;
	}
	public List<Wizyta> getWizyty() {
		return wizyty;
	}
	
	

	
	
	public String getRodzaj() {
			if(rodzaj.getClass() == Staz.class)
				return "tak";
			else
				return "nie";
	
		}
	public void setRodzaj(Rodzaj_lekarza rodzaj) {
		
		this.rodzaj = rodzaj;
	}
	
	public void addRecepta(Recepta e) {
		recepty.add(e);
	}
	public void removeRecepta(int index) {
		recepty.remove(index);
	}
	public void addSkierowanie(Skierowanie e) {
		skierowania.add(e);
	}
	public void removeSkierowanie(int index) {
		skierowania.remove(index);
	}
	public void addWizyta(Wizyta e) {
		wizyty.add(e);
	}
	public void removeWizyta(int index) {
		wizyty.remove(index);
	}
	public static int getTotal_id() {
		return total_id;
	}

	public static void setTotal_id(int total_id) {
		Lekarz.total_id = total_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public boolean setLogin(String login) {

		this.login = login;
		return true;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public int getSala() {
		return sala;
	}

	public boolean setSala(int sala) {
		
		this.sala = sala;
		return true;
	}

	public String getTelefon() {
		return telefon;
	}

	public boolean setTelefon(String telefon) {
		if (telefon.length() < 9)
		{
			return false;
		}
		this.telefon = telefon;
		return true;
	}
}
