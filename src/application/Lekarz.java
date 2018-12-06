package application;

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
	private Centrala C;
	
	public Lekarz(String login, String haslo, String imie, String nazwisko, int wiek, int sala, String telefon)
	{
		this.id = total_id++;
		this.login = login;
		this.haslo = haslo;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.wiek = wiek;
		this.sala = sala;
		this.telefon = telefon;
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
		for(Lekarz L : C.getInstance().getLekarze())
		{
			if (L.equals(login))
			{
				return false;
			}
		}
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
		for(Lekarz L : C.getInstance().getLekarze())
		{
			if (L.getSala() == sala)
			{
				return false;
			}
		}
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
		for(Lekarz L : C.getInstance().getLekarze())
		{
			if (L.getLogin().equals(login))
			{
				return false;
			}
		}
		this.telefon = telefon;
		return true;
	}
}
