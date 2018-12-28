package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
//siemka
//costam
//Jeszcze jedno
//asdasdasdasdsadasd
public class Centrala {
	private static Centrala instance = null;
	private List<Lekarz> lekarze = new ArrayList<Lekarz>();
	private ArrayList<Pacjent> pacjenci = new ArrayList<Pacjent>();
	private Stan stan = null;
	
	public Centrala() {
		addPacjent(new Pacjent("96113983712", "Rafon", "Tracz", 12, "Rafonowo", 6, 16,
				"Lodz"));
		addPacjent(new Pacjent("96123983711", "Rafon", "Tracz", 12, "Rafonowo", 6, 16,
				"Lodz"));
		addPacjent(new Pacjent("96123983713", "Rafon", "Tracz", 12, "Rafonowo", 6, 16,
				"Lodz"));
		addPacjent(new Pacjent("96123983713", "Rafon", "Tracz", 12, "Rafonowo", 6, 16,
				"Lodz"));
		
		
		
		
		
		Lekarz a = new Lekarz("kucharz96", "1234", "Jan", "Wacek", 35, 100, "111111111",false);
		a.addWizyta(new Wizyta_w_przychodni(0, "96123934567", "jeden", "12-12-2018 12:00"));
		
		a.addSkierowanie(new Skierowanie(0, "96123934567", "chorysssssssssssss", "z³amanie"));
		a.addRecepta(new Recepta(0, "96123934567", "chorysssssssssssss"));
		
		Lekarz b = new Lekarz("jankomuzykant", "12346", "Kamil", "Kowalski", 30, 110, "111111112",true);
		Wizyta x = new Konsultacja(new Wizyta_domowa(1, "96123983764", "dwa", "12-12-2018 12:20"));
		
		Wizyta y = new Echo_serca(x);
		String opis = y.ustaw_opis() + x.getOpis();
		x.setOpis(opis);
		b.addWizyta(x);
		b.addSkierowanie(new Skierowanie(1, "96123983764", "chory", "z³amany piszczel przez Rafona"));
		b.addRecepta(new Recepta(1, "96123983764", "chory"));
		
		Lekarz c = new Lekarz("cos96", "ser123", "Magda", "Nowakowska", 45, 200, "211111111",false);
		c.addWizyta(new Wizyta_w_przychodni(2, "96123934567", "trzy", "12-12-2018 12:00"));
		c.addSkierowanie(new Skierowanie(2, "96123934567", "chorysssssssssssss", "z³amanie"));
		c.addRecepta(new Recepta(2, "96123934567", "chorysssssssssssss"));
		c.addRecepta(new Recepta(2, "96123983764", "chory"));
		
		Lekarz d = new Lekarz("cos96", "ser123", "Magda", "Nowakowska", 45, 200, "211111111",true);
		d.addWizyta(new Wizyta_domowa(3, "96123983764", "cztery", "12-12-2018 12:20"));
		d.addSkierowanie(new Skierowanie(3, "96123983764", "chory", "z³amany piszczel przez Rafona"));
		d.addRecepta(new Recepta(3, "96123983764", "chory"));
		
		addLekarz(a);
		addLekarz(b);
		addLekarz(c);
		addLekarz(d);
		addLekarz(new Lekarz("coswddasd", "ser123", "Eliza", "Nowakowska", 45, 200, "211111111",false));
		addLekarz(new Lekarz("123", "123", "Kamilo", "Kamco", 45, 200, "211111112",true));
	}
	
	public void setStan(Stan stan) {
		this.stan = stan;
	}
	public static Centrala getInstance() {
        if (instance == null) {
            instance = new Centrala();
        }
        
        return instance;
    }
	
	public List<Lekarz> getLekarze() {
		return lekarze;
	}
	
	public ArrayList<Pacjent> getPacjenci() {
		return pacjenci;
	}
	
	public void Logowanie(String login, String haslo,Button b, ActionEvent event)
	{//
		/*if(login == null || haslo == null)
			return "";

		if(login.equals("admin") && haslo.equals("admin"))
			return login;
		
		for (Lekarz a : lekarze)
		{
			if(a.getLogin().equals(login) && a.getHaslo().equals(haslo))
				return login;
		}
		return "";*/
		stan.logowanie(login, haslo, b, event);
	}
	public void addLekarz(Lekarz e) {
		lekarze.add(e);
	}
	public void removeLekarz(int index) {
		
/*
		for(Iterator<Recepta> iter = recepty.iterator(); iter.hasNext();) {
			Recepta a = iter.next();
			if(a.getId_lekarza()==lekarze.get(index).getId()) 
				iter.remove();
				
			
		}
			for(Iterator<Skierowanie> iter = skierowania.iterator(); iter.hasNext();) {
				Skierowanie a = iter.next();
				if(a.getId_lekarza()==lekarze.get(index).getId()) 
					iter.remove();
					
				
			}
			for(Iterator<Wizyta> iter = wizyty.iterator(); iter.hasNext();) {
				Wizyta a = iter.next();
				if(a.getId_lekarza()==lekarze.get(index).getId()) 
					iter.remove();
					
				
			}*/
			
			lekarze.remove(index);

	}
	public void addPacjent(Pacjent e) {
		pacjenci.add(e);
	}
	public void removePacjent(int index) {
		pacjenci.remove(index);
	}

	
}
