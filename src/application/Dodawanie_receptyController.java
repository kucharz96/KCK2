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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


public class Dodawanie_receptyController {
	@FXML
	private TextArea r_opis;
	@FXML
	private ComboBox r_lekarz, r_pacjent;
	@FXML
	private DatePicker r_data;
	@FXML
	public Button p_ok, p_anuluj;
	@FXML
	private Spinner r_godzina, r_minuta;
	private Centrala C;
	private ObservableList<Lekarz> lekarz = FXCollections.observableArrayList(C.getInstance().getLekarze());
	private ObservableList<Pacjent> pacjent = FXCollections.observableArrayList(C.getInstance().getPacjenci());
	private ObservableList<Recepta> recepta;
	//Nale¿y j¹ wykonaæ, by nadaæ jakby eventy na poszczególne pola (wykonuje siê dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w³asciwoœci dla FXMLi

	
	public void initialize() throws NumberFormatException
	{
		
		for(Pacjent P: pacjent)
		{
			r_pacjent.getItems().addAll(P.getPesel() + " : " +P.getImie() + " " +P.getNazwisko());
		}
		for(Lekarz L: lekarz)
		{
			r_lekarz.getItems().addAll(L.getId() + " : " +L.getImie() + " " +L.getNazwisko());
		}		
		
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
			boolean czyPustyPacjent = r_pacjent.getSelectionModel().isEmpty();
			boolean czyPustyLekarz = r_lekarz.getSelectionModel().isEmpty();
			boolean czyPustaData = r_data.getValue() == null;
			boolean czyOpisPusty = r_opis.getText().isEmpty();
			if(czyPustyLekarz || czyPustaData || czyPustyPacjent || czyOpisPusty)
				{
					errorWindow();
					return;
				}
				Recepta r = new Recepta(0, null, null);
				//Ogarniêcie peselku
				String peselek = r_pacjent.getSelectionModel().getSelectedItem().toString();
				String peselek1[] = peselek.split(" ", 2);
				r.setPesel_pacjenta(peselek1[0]);
				//Ogarniêcie id lekarza
				String id = r_lekarz.getSelectionModel().getSelectedItem().toString();
				String id1[] = id.split(" ", 2);
				r.setId_lekarza(Integer.parseInt(id1[0]));
				/*
				 * String wyraz = listaPacjentow.getSelectedItem();
							String wyraz2 = listaLekarzy.getText();
							String wyr[] = wyraz.split(" ", 2);
							String wyr2[] = wyraz2.split(" ", 2);
							S.setId_lekarza(Integer.parseInt(wyr2[0]));
							S.setPesel_pacjenta(wyr[0]);
				 * 
				 * 
				 */
				//r.se
				r.setOpis(r_opis.getText());
				Centrala.getInstance().addRecepta(r);

				//Dodanie pacjenta//
				recepta.add(r);
				informationWindow();
			
		}

			
	}
	public void informationWindow()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Komunikat");
		alert.setHeaderText(null);
		alert.setContentText("Pomyœlnie dodano pacjenta.");

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

	public void setItems(ObservableList<Recepta> items) {
		// TODO Auto-generated method stub
		this.recepta = items;
	}
}
