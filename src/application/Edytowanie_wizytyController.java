package application;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


public class Edytowanie_wizytyController extends MainController {
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
	@FXML
	private CheckBox konsultacja, ekg, echo;
	@FXML
	private RadioButton domowa, przychodnia;
	private ToggleGroup grupa;
	private ObservableList<Lekarz> lekarz = FXCollections.observableArrayList(centrala.getLekarze());
	private ObservableList<Pacjent> pacjent = FXCollections.observableArrayList(centrala.getPacjenci());
	private ObservableList<Wizyta> wizyta;
	private int index;
	//Nale¿y j¹ wykonaæ, by nadaæ jakby eventy na poszczególne pola (wykonuje siê dla wszystkich FXML), jest to taka inicjalizacyjna
	//Inicjalizuje ona w³asciwoœci dla FXMLi	
	public void initialize() throws NumberFormatException
	{
		
		grupa = new ToggleGroup();
		domowa.setToggleGroup(grupa);
		przychodnia.setToggleGroup(grupa);
		przychodnia.setSelected(true);
		
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
				//Ogarniêcie peselku
				String peselek = w_pacjent.getSelectionModel().getSelectedItem().toString();
				String peselek1[] = peselek.split(" ", 2);
				//Ogarniêcie id lekarza
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
					Wizyta w;
					if(przychodnia.isSelected())
						w = new Wizyta_w_przychodni(Integer.parseInt(id1[0]), peselek1[0], w_opis.getText(),
							dateczka+ " " + godzina +":"+  minutka);
					else
						w = new Wizyta_domowa(Integer.parseInt(id1[0]), peselek1[0], w_opis.getText(),
								dateczka+ " " + godzina +":"+  minutka);
					
					if(konsultacja.isSelected())
						w = new Konsultacja(w);
					
					if(ekg.isSelected())
						w = new Ekg(w);
					
					if(echo.isSelected())
						w = new Echo_serca(w);
					
					w.setOpis(w.ustaw_opis() + w_opis.getText());
					w.setCena(w.ustaw_cene());
					int tmp = index;
					for(Iterator<Lekarz> iter = centrala.getLekarze().iterator(); iter.hasNext();) {
						Lekarz a = iter.next();
						if(a.getRecepty().size() - 1 < tmp)
							tmp -= a.getRecepty().size();
						else {
							a.removeWizyta(tmp);
							
							break;
						}
					}
					for(Iterator<Lekarz> iter = centrala.getLekarze().iterator(); iter.hasNext();) {
						Lekarz a = iter.next();
						
						if(a.getId() == Integer.parseInt(id1[0])) {
						a.addWizyta(w);

						break;
						}
					}
					
					
					wizyta.set(index, w);
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
		alert.setContentText("Pomyœlnie edytowano wizytê.");

		alert.showAndWait();
	}
	public void errorWindow()
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B³¹d");
		alert.setHeaderText(null);
		alert.setContentText("Poprawnie uzupe³nij pola.");

		alert.showAndWait();
	}
	//Implementacja metody potrzebnej w tym kontrolerze na dodanie Pacjenta do listy.

	public void setItems(ObservableList<Wizyta> observableList) {
		// TODO Auto-generated method stub
		this.wizyta = observableList;
	}
	public void setIndex(int i) {
		// TODO Auto-generated method stub
		this.index = i;
	}
	public void getItems(Wizyta selectedItem) {
		// TODO Auto-generated method stub
		for (Lekarz L: centrala.getLekarze())
		{
			if(L.getId() == selectedItem.getId_lekarza())
			w_lekarz.setValue((Integer.toString(selectedItem.getId_lekarza()) + " : "+L.getImie()+" "+L.getNazwisko()));
		}
		for (Pacjent L: centrala.getPacjenci())
		{

			if(L.getPesel().equals(selectedItem.getPesel_pacjenta()))
			w_pacjent.setValue((selectedItem.getPesel_pacjenta() + " : "+L.getImie()+" "+L.getNazwisko()));
		}
		Date date = null;
		w_opis.setText(selectedItem.getOpis());
		if(selectedItem.ustaw_opis().contains("Wizyta domowa")) {
			domowa.setSelected(true);
			przychodnia.setSelected(false);
		}
		else {
			przychodnia.setSelected(true);
			domowa.setSelected(false);
		}
		
		if(selectedItem.ustaw_opis().contains("Konsultacja"))
			konsultacja.setSelected(true);
		
		if(selectedItem.ustaw_opis().contains("Ekg"))
			ekg.setSelected(true);
		
		if(selectedItem.ustaw_opis().contains("Echo serca"))
			echo.setSelected(true);
		
			
		String dataa = selectedItem.getData().toString();
		String dataaa[] = dataa.split(" ",6);
		String godzina_minuta = dataaa[3];
		String godzinka_minutka[] = godzina_minuta.split(":", 3);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date = formatter.parse(selectedItem.getData().toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w_data.setPromptText(dataaa[2]+" "+ dataaa[0]+" " + dataaa[1]+ " "+ dataaa[5]);

	}
}

