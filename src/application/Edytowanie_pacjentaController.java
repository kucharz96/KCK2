package application;

import java.io.IOException;
import java.util.Iterator;
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


public class Edytowanie_pacjentaController extends MainController {
	@FXML
	private TextField p_pesel, p_imie, p_nazwisko, p_wiek, p_numer_domu, p_numer_mieszkania,p_ulica,
	p_miejscowosc;
	@FXML
	public Button p_ok, p_anuluj;
	//Stworzenie instancji na pacjenta stworzonego w tym kontrolerze]
	private String poczatkowy_pesel;
	private int index;
	private ObservableList<Pacjent> pacjent_lista;
	//Nale�y j� wykona�, by nada� jakby eventy na poszczeg�lne pola (wykonuje si� dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w�asciwo�ci dla FXMLi
	@FXML
	public void initialize() throws NumberFormatException
	{

		p_pesel.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,11}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            p_pesel.setText(oldValue);
		        }
		    }
		});
		p_imie.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("[A-Z][a-z]*")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            p_imie.setText("");
		        }
		    }
		});
		p_nazwisko.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("[A-Z][a-z]*")) {
		        	int value = Integer.parseInt(newValue);
		        } else {
		            p_nazwisko.setText("");
		        }
		    }
		});
		p_wiek.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,3}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            p_wiek.setText(oldValue);
		        }
		    }
		});
		//Czy na pewno potrzeba nam walidacja numeru mieszkania i numeru domu (np. dom 11A)
		p_numer_domu.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,3}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            p_numer_domu.setText(oldValue);
		        }
		    }
		});
		//Czy na pewno potrzeba nam walidacja numeru mieszkania i numeru domu (np. dom 11A)
		p_numer_mieszkania.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("([0-9]){0,3}")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		            p_numer_mieszkania.setText(oldValue);
		        }
		    }
		});
		//Brak walidacji dla miejscowo�ci (za du�o k�opot�w zwi�zanych z kilku wyraz�w, itp). W imionach rzadziej takie co�.
		
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
			if(p_pesel.getText().isEmpty() || p_imie.getText().isEmpty() || p_nazwisko.getText().isEmpty() ||
				p_wiek.getText().isEmpty() || p_numer_domu.getText().isEmpty() || p_numer_mieszkania.getText().isEmpty()
				|| p_ulica.getText().isEmpty() || p_miejscowosc.getText().isEmpty())
				{
					errorWindow();
					return;
				}

			if (poczatkowy_pesel.equals(p_pesel.getText()))
			{
				//Nic, smia�o zapisa� mo�na.
			}
			else
			{
				String pattern = "[0-9]{11}";
				for (Pacjent P : centrala.getPacjenci())
				{
					if(p_pesel.getText().equals(P.getPesel()))
					{
						peselError();
						return;
					}

					if(!p_pesel.getText().matches(pattern))
					{
						peselError();
						return;
					}	
				}
			}
				Pacjent p = new Pacjent();
				p.setPesel(p_pesel.getText());
				p.setImie(p_imie.getText());
				p.setNazwisko(p_nazwisko.getText());
				p.setWiek(Integer.parseInt(p_wiek.getText()));
				p.setUlica(p_ulica.getText());
				p.setNr_domu(Integer.parseInt(p_numer_domu.getText()));
				p.setNr_mieszkania(Integer.parseInt(p_numer_mieszkania.getText()));
				p.setMiejscowosc(p_miejscowosc.getText());
				//Dodanie pacjenta//
				pacjent_lista.set(index, p);
				informationWindow();
	
			}
				
		}

	
	public void informationWindow()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Komunikat");
		alert.setHeaderText(null);
		alert.setContentText("Pomy�lnie edytowano pacjenta.");

		alert.showAndWait();
	}
	public void errorWindow()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B��d");
		alert.setHeaderText(null);
		alert.setContentText("Uzupe�nij pola.");

		alert.showAndWait();
	}
	public void peselError()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B��d");
		alert.setHeaderText(null);
		alert.setContentText("Pesel ju� istnieje.");

		alert.showAndWait();
	}
	//Implementacja metody potrzebnej w tym kontrolerze na dodanie Pacjenta do listy.

	public void setItems(ObservableList<Pacjent> pacjent2) {
		// TODO Auto-generated method stub
		this.pacjent_lista = pacjent2;
		
	}
	public void getItems(Pacjent selectedItem) {
		// TODO Auto-generated method stub
		p_pesel.textProperty().setValue(selectedItem.getPesel());
		p_imie.textProperty().setValue(selectedItem.getImie());
		p_nazwisko.textProperty().setValue(selectedItem.getNazwisko());
		p_wiek.textProperty().setValue(Integer.toString(selectedItem.getWiek()));
		p_ulica.textProperty().setValue(selectedItem.getUlica());
		p_numer_domu.textProperty().setValue(Integer.toString(selectedItem.getNr_domu()));
		p_numer_mieszkania.textProperty().setValue(Integer.toString(selectedItem.getNr_mieszkania()));
		p_miejscowosc.textProperty().setValue(selectedItem.getMiejscowosc());
		poczatkowy_pesel = selectedItem.getPesel();
		
	}
	public void setIndex(int i) {
		// TODO Auto-generated method stub
		this.index = i;
		
	}

}
