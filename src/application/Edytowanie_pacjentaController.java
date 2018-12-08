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


public class Edytowanie_pacjentaController {
	@FXML
	private TextField p_pesel, p_imie, p_nazwisko, p_wiek, p_numer_domu, p_numer_mieszkania,p_ulica,
	p_miejscowosc;
	@FXML
	public Button p_ok, p_anuluj;
	//Stworzenie instancji na pacjenta stworzonego w tym kontrolerze]
	private int index;
	private ObservableList<Pacjent> pacjent_lista;
	private Centrala C;
	//Nale¿y j¹ wykonaæ, by nadaæ jakby eventy na poszczególne pola (wykonuje siê dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w³asciwoœci dla FXMLi
	@FXML
	public void initialize() throws NumberFormatException
	{
		//System.out.println(pacjent.getPesel());
		//p_pesel.textProperty().setValue();
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
			if(p_pesel.getText().isEmpty() || p_imie.getText().isEmpty() || p_nazwisko.getText().isEmpty() ||
				p_wiek.getText().isEmpty() || p_numer_domu.getText().isEmpty() || p_numer_mieszkania.getText().isEmpty()
				|| p_ulica.getText().isEmpty() || p_miejscowosc.getText().isEmpty())
				{
					errorWindow();
				}
			Pacjent p = new Pacjent(null, null, null, 0, null, 0, 0, null);
			Pacjent PP = C.getInstance().getPacjenci().get(index);
			for(Pacjent P1: C.getInstance().getPacjenci())
			{
				if (P1.getPesel().equals(p_pesel) && !PP.getPesel().equals(P1.getPesel()))
				{
					peselError();
					System.out.println("XD");
				}
			}
			if(p.setPesel(p_pesel.getText())==true)
			{
				p.setPesel(p_pesel.getText());
				p.setImie(p_imie.getText());
				p.setNazwisko(p_nazwisko.getText());
				p.setWiek(Integer.parseInt(p_wiek.getText()));
				p.setUlica(p_ulica.getText());
				p.setNr_domu(Integer.parseInt(p_numer_domu.getText()));
				p.setNr_mieszkania(Integer.parseInt(p_numer_mieszkania.getText()));
				p.setMiejscowosc(p_miejscowosc.getText());
				//Centrala.getInstance().addPacjent(p);

				//Dodanie pacjenta//
				pacjent_lista.set(index, p);
				informationWindow();
	
			}
			else
			{
				peselError();
			}
			}
			
		}

	
	public void informationWindow()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Komunikat");
		alert.setHeaderText(null);
		alert.setContentText("Pomyœlnie edytowano pacjenta.");

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
		alert.setContentText("Pesel ju¿ istnieje.");

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
		
	}
	public void setIndex(int i) {
		// TODO Auto-generated method stub
		this.index = i;
		
	}

}
