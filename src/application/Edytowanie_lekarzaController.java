package application;

import java.io.IOException;
import java.util.function.UnaryOperator;

import javax.imageio.spi.RegisterableService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


public class Edytowanie_lekarzaController extends MainController {
	@FXML
	private TextField l_login, l_haslo, l_imie, l_nazwisko, l_wiek, l_sala, l_telefon;
	@FXML
	public Button p_ok, p_anuluj;
	@FXML
	private CheckBox staz;
	//Stworzenie instancji na pacjenta stworzonego w tym kontrolerze]
	private int index;
	private ObservableList<Lekarz> lekarz_lista;
	//Nale¿y j¹ wykonaæ, by nadaæ jakby eventy na poszczególne pola (wykonuje siê dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w³asciwoœci dla FXMLi
	@FXML
	public void initialize() throws NumberFormatException
	{
		//System.out.println(pacjent.getPesel());
		//p_pesel.textProperty().setValue();
		l_imie.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("[A-Z][a-z]*")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            l_imie.setText("");
		        }
		    }
		});
		l_nazwisko.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("[A-Z][a-z]*")) {
		        	int value = Integer.parseInt(newValue);
		        } else {
		            l_nazwisko.setText("");
		        }
		    }
		});
		l_wiek.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,3}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            l_wiek.setText(oldValue);
		        }
		    }
		});

		l_sala.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,3}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            l_sala.setText(oldValue);
		        }
		    }
		});

		l_telefon.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,9}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            l_telefon.setText(oldValue);
		        }
		    }
		});
		//Brak walidacji dla miejscowoœci (za du¿o k³opotów zwi¹zanych z kilku wyrazów, itp). W imionach rzadziej takie coœ.
		
	}
	public void zamknijOkno(ActionEvent event)
	{
		   Stage stage = (Stage) p_anuluj.getScene().getWindow();
		   stage.close();
	}
	public void handle(ActionEvent event)
	{	

		if(p_ok != null)
		{	
			if(l_login.getText().isEmpty() || l_haslo.getText().isEmpty() || l_imie.getText().isEmpty() ||
				l_nazwisko.getText().isEmpty() || l_wiek.getText().isEmpty() || l_sala.getText().isEmpty()
				|| l_telefon.getText().isEmpty())
				{
					errorWindow();
				}

			Lekarz l;
			if(staz.isSelected())
				l = new Lekarz(null, null, null, null, 0, 0, null,true);
			else
				l = new Lekarz(null, null, null, null, 0, 0, null,false);
			
			Lekarz PP = centrala.getLekarze().get(index);
			for(Lekarz P1: centrala.getLekarze())
			{
				if ((P1.getLogin().equals(l_login) && PP.getLogin() != P1.getLogin()) ||
						(P1.getSala() == Integer.parseInt(l_sala.getText()) && PP.getSala() != P1.getSala()) ||
						(P1.getTelefon().equals(l_telefon) && PP.getTelefon() != P1.getTelefon()))
				{
					peselError();
					
				}
			}

				l.setLogin(l_login.getText());
				l.setHaslo(l_haslo.getText());
				l.setImie(l_imie.getText());
				l.setNazwisko(l_nazwisko.getText());
				l.setSala(Integer.parseInt(l_sala.getText()));
				l.setWiek(Integer.parseInt(l_wiek.getText()));
				l.setTelefon(l_telefon.getText());

				//Dodanie lekarza
				lekarz_lista.add(l);
				informationWindow();
				
			
		}

			
	}
	public void informationWindow()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Komunikat");
		alert.setHeaderText(null);
		alert.setContentText("Pomyœlnie edytowano doktorka.");

		alert.showAndWait();
	}
	public void errorWindow()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B³¹d");
		alert.setHeaderText(null);
		alert.setContentText("Uzupe³nij pola.");

		alert.showAndWait();
	}
	public void peselError()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B³¹d");
		alert.setHeaderText(null);
		alert.setContentText("Login, sala lub telefon s¹ zajête.");

		alert.showAndWait();
	}
	//Implementacja metody potrzebnej w tym kontrolerze na dodanie Pacjenta do listy.

	public void setItems(ObservableList<Lekarz> pacjent2) {
		// TODO Auto-generated method stub
		this.lekarz_lista = pacjent2;
		
	}
	public void getItems(Lekarz selectedItem) {
		// TODO Auto-generated method stub
		l_imie.textProperty().setValue(selectedItem.getImie());
		l_nazwisko.textProperty().setValue(selectedItem.getNazwisko());
		l_wiek.textProperty().setValue(Integer.toString(selectedItem.getWiek()));
		l_login.textProperty().setValue(selectedItem.getLogin());
		l_sala.textProperty().setValue(Integer.toString(selectedItem.getSala()));
		l_haslo.textProperty().setValue(selectedItem.getHaslo());
		l_telefon.textProperty().setValue(selectedItem.getTelefon());
		if(selectedItem.getRodzaj().equals("tak"))
			staz.setSelected(true);
		else
			staz.setSelected(false);
		
	}
	public void setIndex(int i) {
		// TODO Auto-generated method stub
		this.index = i;
		
	}

}
