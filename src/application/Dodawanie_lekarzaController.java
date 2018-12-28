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


public class Dodawanie_lekarzaController extends MainController {
	@FXML
	private TextField l_login, l_haslo, l_imie, l_nazwisko, l_wiek, l_sala, l_telefon;
	@FXML
	public Button p_ok, p_anuluj;
	@FXML
	private CheckBox staz;
	//Stworzenie instancji na pacjenta stworzonego w tym kontrolerze
	@FXML
	private ObservableList<Lekarz> lekarz;
	
	//Nale¿y j¹ wykonaæ, by nadaæ jakby eventy na poszczególne pola (wykonuje siê dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w³asciwoœci dla FXMLi
	@FXML
	public void initialize() throws NumberFormatException
	{
		//Login Property nie jest potrzebne - ka¿da cyfra m
		l_login.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (newValue.matches("^[0-9A-Za-z\\s-]+$")) {
		            int value = Integer.parseInt(newValue);
		        } else {
		           l_login.setText(oldValue);
		        }
		    }
		});
		//Niepotrzebne na haslo
		/*
		l_haslo.textProperty().addListener(new ChangeListener<String>() {
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
		*/
		
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
		            l_imie.setText(oldValue);
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

			if (l.setLogin(l_login.getText()) == true && l.setSala(Integer.parseInt(l_sala.getText()))==true
					&& l.setTelefon(l_telefon.getText()) == true)
			{	
				l.setLogin(l_login.getText());
				l.setHaslo(l_haslo.getText());
				l.setImie(l_imie.getText());
				l.setNazwisko(l_nazwisko.getText());
				l.setSala(Integer.parseInt(l_sala.getText()));
				l.setWiek(Integer.parseInt(l_wiek.getText()));
				l.setTelefon(l_telefon.getText());
				centrala.addLekarz(l);

				//Dodanie lekarza
				lekarz.add(l);
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
		alert.setContentText("Pomyœlnie dodano doktorka.");

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
	public void setItems(ObservableList<Lekarz> lekarz) {
		this.lekarz = lekarz;
		
	}
}
