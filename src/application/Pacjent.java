package application;

import java.util.ArrayList;

public class Pacjent {
	//private int id_lekarza;
	private String pesel;
	private String imie;
	private String nazwisko;
	private int wiek;
	private String ulica;
	private int nr_domu;
	private int nr_mieszkania;
	private String miejscowosc;
	
	Pacjent()
	{
		
	}
	Pacjent(/*int id_lekarza, */String pesel, String imie, String nazwisko, int wiek, String ulica, int nr_domu, int nr_mieszkania,
			String miejscowosc)
	{
		//this.id_lekarza = id_lekarza;
		this.imie = imie;
		this.pesel = pesel;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.ulica = ulica;
		this.nr_domu = nr_domu;
		this.nr_mieszkania = nr_mieszkania;
		this.miejscowosc = miejscowosc;
	}
	public String getPesel() {
		return pesel;
	}
	@SuppressWarnings("static-access")
	public boolean setPesel(String pesel) {
		String pattern = "[0-9]{11}";
		if (pesel.matches(pattern))
		{
			this.pesel = pesel;
			return true;
			
		}
		return false;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		String pattern = "[A-Z][a-z]*";
		if (imie.matches(pattern))
		{
			this.imie = imie;
		}
		
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		String pattern = "[A-Z][a-z]*";
		if (nazwisko.matches(pattern))
		{
			this.nazwisko = nazwisko;
		}
		this.nazwisko = nazwisko;
	}
	public int getWiek() {
		return wiek;
	}
	public void setWiek(int wiek) {
		this.wiek = wiek;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public int getNr_domu() {
		return nr_domu;
	}
	public void setNr_domu(int nr_domu) {
		this.nr_domu = nr_domu;
	}
	public int getNr_mieszkania() {
		return nr_mieszkania;
	}
	public void setNr_mieszkania(int nr_mieszkania) {
		this.nr_mieszkania = nr_mieszkania;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	/*
	public int getLekarz() {
		return id_lekarza;
	}
	public void setLekarz(int id) {
		this.id_lekarza = id;
	}
	*/
}
