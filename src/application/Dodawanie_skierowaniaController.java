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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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


public class Dodawanie_skierowaniaController extends MainController  {
	@FXML
	private TextArea r_opis;
	@FXML
	private ComboBox r_pacjent;
	@FXML
	private Label r_lekarz;
	@FXML
	public Button p_ok, p_anuluj;
	@FXML
	private TextField r_cel;
	private Panel_lekarzaController p;
	private int id;
	private ObservableList<Lekarz> lekarz = FXCollections.observableArrayList(centrala.getLekarze());
	private ObservableList<Pacjent> pacjent = FXCollections.observableArrayList(centrala.getPacjenci());
	private ObservableList<Skierowanie> skierowanie;
	//Nale�y j� wykona�, by nada� jakby eventy na poszczeg�lne pola (wykonuje si� dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w�asciwo�ci dla FXMLi

	
	public void initialize() throws NumberFormatException
	{
		id = p.id;
		for(Pacjent P: pacjent)
		{
			r_pacjent.getItems().addAll(P.getPesel() + " : " +P.getImie() + " " +P.getNazwisko());
		}
		for(Lekarz L : lekarz)
		{	if(id == L.getId())
			r_lekarz.setText(L.getId() + " : "+ L.getImie() + " " + L.getNazwisko());	
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
			boolean czyOpisPusty = r_opis.getText().isEmpty();
			boolean czyCelPusty = r_cel.getText().isEmpty();
			if(czyPustyPacjent || czyOpisPusty || czyCelPusty)
				{
					errorWindow();
					return;
				}
				Skierowanie r = new Skierowanie(0, null, null, null);
				//Ogarni�cie peselku
				String peselek = r_pacjent.getSelectionModel().getSelectedItem().toString();
				String peselek1[] = peselek.split(" ", 2);
				r.setPesel_pacjenta(peselek1[0]);
				//Ogarni�cie id lekarza
				String id = r_lekarz.getText();
				String id1[] = id.split(" ", 2);
				r.setId_lekarza(Integer.parseInt(id1[0]));

				r.setOpis(r_opis.getText());
				r.setCel(r_cel.getText());
				for(Iterator<Lekarz> iter = centrala.getLekarze().iterator(); iter.hasNext();) {
					Lekarz a = iter.next();
					
					if(a.getId() == Integer.parseInt(id1[0])) {
					a.addSkierowanie(r);

					skierowanie.add(r);
					break;
					}
				}

				//Dodanie pacjenta//
				informationWindow();
			
		}

			
	}
	public void informationWindow()
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Komunikat");
		alert.setHeaderText(null);
		alert.setContentText("Pomy�lnie dodano skierowanie.");

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

	public void setItems(ObservableList<Skierowanie> items) {
		// TODO Auto-generated method stub
		this.skierowanie = items;
	}
}
