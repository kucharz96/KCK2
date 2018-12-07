package application;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
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


public class Dodawanie_wizytyController {
	@FXML
	private TextArea w_opis;
	@FXML
	private ComboBox w_lekarz, w_pacjent;
	@FXML
	private DatePicker w_data;
	@FXML
	public Button p_ok, p_anuluj;
	@FXML
	private Spinner w_godzina, w_minuta;
	private Centrala C;
	private ObservableList<Lekarz> lekarz = FXCollections.observableArrayList(C.getInstance().getLekarze());
	private ObservableList<Pacjent> pacjent = FXCollections.observableArrayList(C.getInstance().getPacjenci());
	private ObservableList<Wizyta> wizyta;
	//Nale�y j� wykona�, by nada� jakby eventy na poszczeg�lne pola (wykonuje si� dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w�asciwo�ci dla FXMLi

	
	public void initialize() throws NumberFormatException
	{
		
		for(Pacjent P: pacjent)
		{
			w_pacjent.getItems().addAll(P.getPesel() + " : " +P.getImie() + " " +P.getNazwisko());
		}
		for(Lekarz L: lekarz)
		{
			w_lekarz.getItems().addAll(L.getId() + " : " +L.getImie() + " " +L.getNazwisko());
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
			boolean czyPustyPacjent = w_pacjent.getSelectionModel().isEmpty();
			boolean czyPustyLekarz =w_lekarz.getSelectionModel().isEmpty();
			boolean czyPustaData = w_data.getValue() == null;
			boolean czyOpisPusty = w_opis.getText().isEmpty();
			if(czyPustyLekarz || czyPustaData || czyPustyPacjent || czyOpisPusty)
				{
					errorWindow();
					return;
				}
				//Ogarni�cie peselku
				String peselek = w_pacjent.getSelectionModel().getSelectedItem().toString();
				String peselek1[] = peselek.split(" ", 2);
				//Ogarni�cie id lekarza
				String id = w_lekarz.getSelectionModel().getSelectedItem().toString();
				String id1[] = id.split(" ", 2);
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				Date Data = null;
				try {
					String dateczka = w_data.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
					String godzina;
					if (((Integer) w_godzina.getValue()) < 10)
					{
						godzina = "0";
						godzina += Integer.toString(((Integer) w_godzina.getValue()));
					}
					else
					{
						godzina = Integer.toString(((Integer) w_godzina.getValue()));
					}
					String minutka = Integer.toString(((Integer) w_minuta.getValue()));
					Data = formatter.parse(dateczka+ " " + godzina +":"+  minutka);
					Wizyta w = new Wizyta(Integer.parseInt(id1[0]), peselek1[0], w_opis.getText(),
							dateczka+ " " + godzina +":"+  minutka);
					C.getInstance().addWizyta(w);
					wizyta.add(w);
					informationWindow();
					
				} catch (ParseException e) {
					System.out.println("niet");
					return;
				}

			
		}

			
	}
	public void informationWindow()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Komunikat");
		alert.setHeaderText(null);
		alert.setContentText("Pomy�lnie dodano wizyt�.");

		alert.showAndWait();
	}
	public void errorWindow()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B��d");
		alert.setHeaderText(null);
		alert.setContentText("Poprawnie uzupe�nij pola.");

		alert.showAndWait();
	}
	//Implementacja metody potrzebnej w tym kontrolerze na dodanie Pacjenta do listy.

	public void setItems(ObservableList<Wizyta> observableList) {
		// TODO Auto-generated method stub
		this.wizyta = observableList;
	}
}