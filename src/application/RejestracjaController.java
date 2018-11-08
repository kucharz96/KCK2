package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class RejestracjaController {
	
	static public boolean deci = true;
	public TextField pesel_filtr;
	public Tab tab_lekarze;
	public Label etykieta_filtr;
	public MenuBar bar;
	
	public ContextMenu c_menu;
	public ContextMenu c_menu1;
	public ContextMenu c_menu2;
	public ContextMenu c_menu3;
	public ContextMenu c_menu4;
	
	public TableView<Lekarz> lekarze;
	public TableView<Pacjent> pacjenci;
	public TableView<Wizyta> wizyty;
	public TableView<Skierowanie> skierowania;
	public TableView<Recepta> recepty;

	public TableColumn<Pacjent,String> p_pesel, p_imie, p_nazwisko, p_ulica, p_miejscowosc;
	public TableColumn<Pacjent,Integer> p_wiek, p_nr_d, p_nr_m;
	public TableColumn<Wizyta,String> w_pesel, w_opis;
	public TableColumn<Wizyta,Integer> w_id_p,w_id;
	public TableColumn<Wizyta,Date> w_data;
	public TableColumn<Lekarz,String> l_login, l_haslo, l_imie, l_nazwisko, l_telefon;
	public TableColumn<Lekarz,Integer> l_id, l_wiek, l_nr_sali;
	public TableColumn<Skierowanie,String> s_pesel, s_opis;
	public TableColumn<Skierowanie,Integer> s_id_p,s_id;
	public TableColumn<Skierowanie,Date> s_data;
	public TableColumn<Recepta,String> r_pesel, r_opis;
	public TableColumn<Recepta,Integer> r_id_p,r_id;
	public TableColumn<Recepta,Date> r_data;
	
	public void change() {
		if(tab_lekarze.isSelected())
			etykieta_filtr.setText("Nazwisko lekarza: ");
		else
			etykieta_filtr.setText("Pesel pacjenta: ");

		
	}
	//////////////////////////////////////////////////////////////FILTR/////////////////////////////////////////////////////////////////////////////

	public void fuck(){
		List<Pacjent> tmp1 = new ArrayList<>();
		List<Wizyta> tmp2 = new ArrayList<>();
		List<Skierowanie> tmp3 = new ArrayList<>();
		List<Recepta> tmp4 = new ArrayList<>();
		List<Lekarz> tmp5 = new ArrayList<>();

		if(!tab_lekarze.isSelected()) {

			for(Pacjent a:Centrala.getInstance().getPacjenci()) {
				if(a.getPesel().startsWith(pesel_filtr.getText()))
					tmp1.add(a);
			
			}
			for(Wizyta a:Centrala.getInstance().getWizyty()) {
				if(a.getPesel_pacjenta().startsWith(pesel_filtr.getText()))
					tmp2.add(a);
			
			}
			for(Skierowanie a:Centrala.getInstance().getSkierowania()) {
				if(a.getPesel_pacjenta().startsWith(pesel_filtr.getText()))
					tmp3.add(a);
			
			}
			for(Recepta a:Centrala.getInstance().getRecepty()) {
				if(a.getPesel_pacjenta().startsWith(pesel_filtr.getText()))
					tmp4.add(a);
			
			}
			ObservableList<Pacjent> lista = FXCollections.observableArrayList(tmp1);
			ObservableList<Wizyta> lista1 = FXCollections.observableArrayList(tmp2);
			ObservableList<Skierowanie> lista2 = FXCollections.observableArrayList(tmp3);
			ObservableList<Recepta> lista3 = FXCollections.observableArrayList(tmp4);

			pacjenci.setItems(lista);
			wizyty.setItems(lista1);
			skierowania.setItems(lista2);
			recepty.setItems(lista3);
		}
		
		else {
			
			for(Lekarz a:Centrala.getInstance().getLekarze()) {
				if(a.getNazwisko().startsWith(pesel_filtr.getText()))
					tmp5.add(a);
				
			}
			
			ObservableList<Lekarz> lista = FXCollections.observableArrayList(tmp5);
			lekarze.setItems(lista);

			
		}
	}
	  
	public void initialize() {
									////////////INICJALIZACJA KOLUMN///////////////

		p_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
		p_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
		p_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
		p_wiek.setCellValueFactory(new PropertyValueFactory<>("wiek"));
		p_ulica.setCellValueFactory(new PropertyValueFactory<>("ulica"));
		p_nr_d.setCellValueFactory(new PropertyValueFactory<>("nr_domu"));
		p_nr_m.setCellValueFactory(new PropertyValueFactory<>("nr_mieszkania"));
		p_miejscowosc.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
		
		w_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel_pacjenta"));
		w_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		w_id_p.setCellValueFactory(new PropertyValueFactory<>("id_lekarza"));
		w_opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		w_data.setCellValueFactory(new PropertyValueFactory<>("data"));
		
		s_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel_pacjenta"));
		s_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		s_id_p.setCellValueFactory(new PropertyValueFactory<>("id_lekarza"));
		s_opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		s_data.setCellValueFactory(new PropertyValueFactory<>("data"));
		
		r_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel_pacjenta"));
		r_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		r_id_p.setCellValueFactory(new PropertyValueFactory<>("id_lekarza"));
		r_opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
		r_data.setCellValueFactory(new PropertyValueFactory<>("data"));
	
		l_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		l_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
		l_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
		l_wiek.setCellValueFactory(new PropertyValueFactory<>("wiek"));
		l_login.setCellValueFactory(new PropertyValueFactory<>("login"));
		l_haslo.setCellValueFactory(new PropertyValueFactory<>("haslo"));
		l_telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
		l_nr_sali.setCellValueFactory(new PropertyValueFactory<>("sala"));
											////////////UZUPELNIENIE TABELEK///////////////

		ObservableList<Pacjent> lista = FXCollections.observableArrayList(Centrala.getInstance().getPacjenci());
		ObservableList<Wizyta> lista1 = FXCollections.observableArrayList(Centrala.getInstance().getWizyty());
		ObservableList<Lekarz> lista2 = FXCollections.observableArrayList(Centrala.getInstance().getLekarze());
		ObservableList<Skierowanie> lista3 = FXCollections.observableArrayList(Centrala.getInstance().getSkierowania());
		ObservableList<Recepta> lista4 = FXCollections.observableArrayList(Centrala.getInstance().getRecepty());
	
		pacjenci.setItems(lista);
		wizyty.setItems(lista1);
		lekarze.setItems(lista2);
		skierowania.setItems(lista3);
		recepty.setItems(lista4);
								////////////URUCHOMIENIE SZCZEGOL PO PODWOJNYM KLIKNIECIU MYSZY///////////////

		pacjenci.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
            	try {
					p_szczegoly();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		wizyty.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                w_szczegoly();
            }
        });
		skierowania.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                s_szczegoly();
            }
        });
		recepty.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                r_szczegoly();
            }
        });
		lekarze.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                l_szczegoly();
            }
        });
    
		
	
		
	}
	//////////////////////////////////////////////////////////////WYLOGOWANIE/////////////////////////////////////////////////////////////////////////////

	public void wyloguj() throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie wylogowania");
		alert.setHeaderText(null);
		alert.setContentText("Czy wylogowaæ?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Logowanie.fxml"));
			Scene scene = new Scene(root,400,220);
			System.out.println("COstam");
		    Stage stageTheEventSourceNodeBelongs = (Stage) ((Stage)bar.getScene().getWindow());
		    stageTheEventSourceNodeBelongs.setScene(scene);
		    stageTheEventSourceNodeBelongs.setTitle("Logowanie");
		} 
		else if (result.get() == nie);
		
	}	
	//////////////////////////////////////////////////////////////WYJSCIE/////////////////////////////////////////////////////////////////////////////

	public void wyjdz() throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie wyjœcia");
		alert.setHeaderText(null);
		alert.setContentText("Czy wyjœæ?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
		    Stage stageTheEventSourceNodeBelongs = (Stage) ((Stage)bar.getScene().getWindow());
		    stageTheEventSourceNodeBelongs.close();
		} 
		else if (result.get() == nie);
		
	}	
	//////////////////////////////////////////////////////////////DLA PACJENTA/////////////////////////////////////////////////////////////////////////////
	public void p_dodaj() {
		System.out.println("bbbbb");
		
	}
	
	public void p_edytuj() {
		System.out.println(pacjenci.getSelectionModel().getSelectedItem().getPesel());
	}
	
	public void p_usun() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuniêcia pacjenta");
		alert.setHeaderText(null);
		alert.setContentText("Czy usun¹æ pacjenta?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
			Centrala.getInstance().removePacjent(pacjenci.getSelectionModel().getSelectedIndex());
			pacjenci.getItems().remove(pacjenci.getSelectionModel().getSelectedIndex());
		    
		   
		} 
		else if (result.get() == nie);
		
	}
	public void p_szczegoly() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Szczegoly_pacjenta.fxml"));
		BorderPane root = (BorderPane)fxmlLoader.load();
		Szczegoly_pacjentaController controller = fxmlLoader.<Szczegoly_pacjentaController>getController();
		controller.set_labels(pacjenci.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root,400,320);
		System.out.println("COstam");
		Stage stage = new Stage();
		stage.setScene(scene);
	    stage.setTitle("Szczegol");	
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
	    
	    stage.show();
	    
	}
	//////////////////////////////////////////////////////////////DLA WIZYT/////////////////////////////////////////////////////////////////////////////

	public void w_dodaj() {
		System.out.println("bbbbb");
		
	}
	
	public void w_edytuj() {
		System.out.println(wizyty.getSelectionModel().getSelectedItem().getPesel_pacjenta());
	}
	
	public void w_usun() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuniêcia wizyty");
		alert.setHeaderText(null);
		alert.setContentText("Czy usun¹æ wizytê?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
			Centrala.getInstance().removeWizyta(wizyty.getSelectionModel().getSelectedIndex());
			wizyty.getItems().remove(wizyty.getSelectionModel().getSelectedIndex());
		    
		   
		} 
		else if (result.get() == nie);
		
	}
	public void w_szczegoly() {
		System.out.println("cccc");
		
	}
	//////////////////////////////////////////////////////////////DLA SKIEROWAN/////////////////////////////////////////////////////////////////////////////

	public void s_dodaj() {
		System.out.println("bbbbb");
		
	}
	
	public void s_edytuj() {
		System.out.println(skierowania.getSelectionModel().getSelectedItem().getPesel_pacjenta());
	}
	
	public void s_usun() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuniêcia skierowania");
		alert.setHeaderText(null);
		alert.setContentText("Czy usun¹æ skierowanie?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
			Centrala.getInstance().removeSkierowanie(skierowania.getSelectionModel().getSelectedIndex());
			skierowania.getItems().remove(skierowania.getSelectionModel().getSelectedIndex());
		    
		   
		} 
		else if (result.get() == nie);
		
	}
	public void s_szczegoly() {
		System.out.println("cccc");
		
	}
	//////////////////////////////////////////////////////////////DLA RECEPT/////////////////////////////////////////////////////////////////////////////
	public void r_dodaj() {
		System.out.println("bbbbb");
		
	}
	
	public void r_edytuj() {
		System.out.println(recepty.getSelectionModel().getSelectedItem().getPesel_pacjenta());
	}
	
	public void r_usun() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuniêcia recepty");
		alert.setHeaderText(null);
		alert.setContentText("Czy usun¹æ receptê?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
			Centrala.getInstance().removeRecepta(recepty.getSelectionModel().getSelectedIndex());
			recepty.getItems().remove(recepty.getSelectionModel().getSelectedIndex());
		    
		   
		} 
		else if (result.get() == nie);
		
	}
	public void r_szczegoly() {
		System.out.println("cccc");
		
	}
	//////////////////////////////////////////////////////////////DLA LEKARZY/////////////////////////////////////////////////////////////////////////////
	public void l_dodaj() {
		System.out.println("bbbbb");
		
	}
	
	public void l_edytuj() {
		System.out.println(lekarze.getSelectionModel().getSelectedItem().getNazwisko());
	}
	
	public void l_usun() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Potwierdzenie usuniêcia lekarza");
		alert.setHeaderText(null);
		alert.setContentText("Czy usun¹æ lekarza?");

		ButtonType tak = new ButtonType("Tak");
		ButtonType nie = new ButtonType("Nie");
		alert.getButtonTypes().setAll(tak, nie);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == tak){
			Centrala.getInstance().removeLekarz(lekarze.getSelectionModel().getSelectedIndex());
			for(Iterator<Wizyta> iter = wizyty.getItems().iterator(); iter.hasNext();) {
				Wizyta a = iter.next();
				if(a.getId_lekarza()==lekarze.getSelectionModel().getSelectedItem().getId()) 
					iter.remove();
				
			}
			for(Iterator<Skierowanie> iter = skierowania.getItems().iterator(); iter.hasNext();) {
				Skierowanie a = iter.next();
				if(a.getId_lekarza()==lekarze.getSelectionModel().getSelectedItem().getId()) 
					iter.remove();
					
			}
			for(Iterator<Recepta> iter = recepty.getItems().iterator(); iter.hasNext();) {
				Recepta a = iter.next();
				if(a.getId_lekarza()==lekarze.getSelectionModel().getSelectedItem().getId()) 
					iter.remove();
					
				
			}
			
			
			lekarze.getItems().remove(lekarze.getSelectionModel().getSelectedIndex());
		    
		   
		} 
		else if (result.get() == nie);
		
	}
	public void l_szczegoly() {
		System.out.println("cccc");
		
	}
	//////////////////////////////////////////////////////////////BLOKADA CONTEXT MENU/////////////////////////////////////////////////////////////////////////////

	public void test() {
		try {
			if(pacjenci.getSelectionModel().isEmpty())
				c_menu.hide();
			if(wizyty.getSelectionModel().isEmpty())
				c_menu1.hide();
			if(skierowania.getSelectionModel().isEmpty())
				c_menu2.hide();
			if(recepty.getSelectionModel().isEmpty())
				c_menu3.hide();
			if(lekarze.getSelectionModel().isEmpty())
				c_menu4.hide();
		} catch (NullPointerException e) {
			
			System.out.println("");
			
			
		}
	}
	
	
	

}
