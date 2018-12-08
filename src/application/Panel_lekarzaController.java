package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Panel_lekarzaController {
	
	static public int id;
	static public boolean deci = true;
	public TextField pesel_filtr;
	public Label etykieta_filtr;
	public MenuBar bar;
	private Centrala C;
	public ContextMenu c_menu;
	public ContextMenu c_menu1;
	public ContextMenu c_menu2;
	public ContextMenu c_menu3;
	
	@FXML
	public TableView<Pacjent> pacjenci;
	@FXML
	public TableView<Wizyta> wizyty;
	@FXML
	public TableView<Skierowanie> skierowania;
	@FXML
	public TableView<Recepta> recepty;

	public TableColumn<Pacjent,String> p_pesel, p_imie, p_nazwisko, p_ulica, p_miejscowosc;
	public TableColumn<Pacjent,Integer> p_wiek, p_nr_d, p_nr_m;
	public TableColumn<Wizyta,String> w_pesel, w_opis;
	public TableColumn<Wizyta,Integer> w_id_p,w_id;
	public TableColumn<Wizyta,Date> w_data;
	public TableColumn<Skierowanie,String> s_pesel, s_opis;
	public TableColumn<Skierowanie,Integer> s_id_p,s_id;
	public TableColumn<Skierowanie,Date> s_data;
	public TableColumn<Recepta,String> r_pesel, r_opis;
	public TableColumn<Recepta,Integer> r_id_p,r_id;
	public TableColumn<Recepta,Date> r_data;
	public ObservableList<Pacjent> lista;
	public ObservableList<Wizyta> lista1;
	public ObservableList<Skierowanie> lista2;
	public ObservableList<Recepta> lista3;
	
	
	
	//////////////////////////////////////////////////////////////FILTR/////////////////////////////////////////////////////////////////////////////

	public void fuck(){
		List<Pacjent> tmp1 = new ArrayList<>();
		List<Wizyta> tmp2 = new ArrayList<>();
		List<Skierowanie> tmp3 = new ArrayList<>();
		List<Recepta> tmp4 = new ArrayList<>();

		

			for(Pacjent a:Centrala.getInstance().getPacjenci()) {
				if(a.getPesel().startsWith(pesel_filtr.getText()))
					tmp1.add(a);
			
			}
			for(Wizyta a:Centrala.getInstance().getWizyty()) {
				if(a.getPesel_pacjenta().startsWith(pesel_filtr.getText()) && a.getId_lekarza() == id)
					tmp2.add(a);
			
			}
			for(Skierowanie a:Centrala.getInstance().getSkierowania() ) {
				if(a.getPesel_pacjenta().startsWith(pesel_filtr.getText()) && a.getId_lekarza() == id)
					tmp3.add(a);
			
			}
			for(Recepta a:Centrala.getInstance().getRecepty()) {
				if(a.getPesel_pacjenta().startsWith(pesel_filtr.getText())&& a.getId_lekarza() == id)
					tmp4.add(a);
			
			}
			lista = FXCollections.observableArrayList(tmp1);
			lista1 = FXCollections.observableArrayList(tmp2);
			lista2 = FXCollections.observableArrayList(tmp3);
			lista3 = FXCollections.observableArrayList(tmp4);

			pacjenci.setItems(lista);
			wizyty.setItems(lista1);
			skierowania.setItems(lista2);
			recepty.setItems(lista3);
		
		
		

			
		
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
	
		
											////////////UZUPELNIENIE TABELEK///////////////
		ObservableList<Wizyta> lista1 =  FXCollections.observableArrayList();
		for(Wizyta a:Centrala.getInstance().getWizyty()) {
			if(a.getId_lekarza() == id)
				lista1.add(a);
			
		}
		
		ObservableList<Skierowanie> lista3 =  FXCollections.observableArrayList();
		for(Skierowanie a:Centrala.getInstance().getSkierowania()) {
			if(a.getId_lekarza() == id)
				lista3.add(a);
			
		}
		
		ObservableList<Recepta> lista4 =  FXCollections.observableArrayList();
		for(Recepta a:Centrala.getInstance().getRecepty()) {
			if(a.getId_lekarza() == id)
				lista4.add(a);
			
		}
		
		
		ObservableList<Pacjent> lista = FXCollections.observableArrayList(Centrala.getInstance().getPacjenci());
		
		
		
		pacjenci.setItems(lista);
		wizyty.setItems(lista1);
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
                try {
					w_szczegoly();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		skierowania.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
					s_szczegoly();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		recepty.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                try {
					r_szczegoly();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dodawanie_pacjenta.fxml"));
			Pane root = (Pane)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//Nalezy stworzyæ referencjê do drugiego kontrolera w celu przekazania istniejacej listy//
			Dodawanie_pacjentaController controller = fxmlLoader.getController();
		    controller.setItems(pacjenci.getItems());
			stage.setScene(scene);
		    stage.setTitle("Dodaj pacjenta");	
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
		    
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void p_edytuj() {
		System.out.println(pacjenci.getSelectionModel().getSelectedItem().getPesel());
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Edytowanie_pacjenta.fxml"));
			Pane root = (Pane)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//Nalezy stworzyæ referencjê do drugiego kontrolera w celu przekazania istniejacej listy//
			Edytowanie_pacjentaController controller = fxmlLoader.getController();
			Edytowanie_pacjentaController controller2 = fxmlLoader.getController();
			Edytowanie_pacjentaController controllerIndex = fxmlLoader.getController();
			//Odbiór i przekazanie danych
			controllerIndex.setIndex(pacjenci.getSelectionModel().selectedIndexProperty().get());
			controller2.setItems(pacjenci.getItems());
		    controller.getItems(pacjenci.getSelectionModel().getSelectedItem());
			stage.setScene(scene);
		    stage.setTitle("Dodaj pacjenta");	
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
		    
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Pane root = (Pane)fxmlLoader.load();
		Szczegoly_pacjentaController controller = fxmlLoader.<Szczegoly_pacjentaController>getController();
		controller.set_labels(pacjenci.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root);
		System.out.println("COstam");
		Stage stage = new Stage();
		stage.setScene(scene);
	    stage.setTitle("Szczegó³y");	
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
	    
	    stage.show();
	    
	}
	//////////////////////////////////////////////////////////////DLA WIZYT/////////////////////////////////////////////////////////////////////////////

	public void w_dodaj() {
		System.out.println("bbbbb");
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dodawanie_wizyty.fxml"));
			Pane root = (Pane)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//Nalezy stworzyæ referencjê do drugiego kontrolera w celu przekazania istniejacej listy//
			Dodawanie_wizytyController controller = fxmlLoader.getController();
		    controller.setItems(wizyty.getItems());
			stage.setScene(scene);
		    stage.setTitle("Dodaj wizytê");	
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
		    
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void w_edytuj() {
		System.out.println(wizyty.getSelectionModel().getSelectedItem().getPesel_pacjenta());
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Edytowanie_wizyty.fxml"));
			Pane root = (Pane)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//Nalezy stworzyæ referencjê do drugiego kontrolera w celu przekazania istniejacej listy//
			Edytowanie_wizytyController controller = fxmlLoader.getController();
			Edytowanie_wizytyController controller2 = fxmlLoader.getController();
			Edytowanie_wizytyController controllerIndex = fxmlLoader.getController();
			//Odbiór i przekazanie danych
			controllerIndex.setIndex(wizyty.getSelectionModel().selectedIndexProperty().get());
			controller2.setItems(wizyty.getItems());
		    controller.getItems(wizyty.getSelectionModel().getSelectedItem());
			stage.setScene(scene);
		    stage.setTitle("Edytuj wizytê");	
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
		    
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void w_szczegoly() throws IOException {
		System.out.println("cccc");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Szczegoly_wizyty.fxml"));
		Pane root = (Pane)fxmlLoader.load();
		Szczegoly_wizytyController controller = fxmlLoader.<Szczegoly_wizytyController>getController();
		controller.set_labels(wizyty.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root);
		System.out.println("COstam");
		Stage stage = new Stage();
		stage.setScene(scene);
	    stage.setTitle("Szczegó³y");	
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
	    
	    stage.show();
		
	}
	//////////////////////////////////////////////////////////////DLA SKIEROWAN/////////////////////////////////////////////////////////////////////////////

	public void s_dodaj() {
		System.out.println("bbbbb");
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dodawanie_skierowania.fxml"));
			Pane root = (Pane)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//Nalezy stworzyæ referencjê do drugiego kontrolera w celu przekazania istniejacej listy//
			Dodawanie_skierowaniaController controller = fxmlLoader.getController();
		    controller.setItems(skierowania.getItems());
			stage.setScene(scene);
		    stage.setTitle("Dodaj skierowanie");	
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));

		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void s_szczegoly() throws IOException {
		System.out.println("cccc");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Szczegoly_skierowania.fxml"));
		Pane root = (Pane)fxmlLoader.load();
		Szczegoly_skierowaniaController controller = fxmlLoader.<Szczegoly_skierowaniaController>getController();
		controller.set_labels(skierowania.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root);
		System.out.println("COstam");
		Stage stage = new Stage();
		stage.setScene(scene);
	    stage.setTitle("Szczegó³y");	
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
	    
	    stage.show();
	    
		
	}
	//////////////////////////////////////////////////////////////DLA RECEPT/////////////////////////////////////////////////////////////////////////////
	public void r_dodaj() {
		System.out.println("bbbbb");
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dodawanie_recepty.fxml"));
			Pane root = (Pane)fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//Nalezy stworzyæ referencjê do drugiego kontrolera w celu przekazania istniejacej listy//
			Dodawanie_receptyController controller = fxmlLoader.getController();
		    controller.setItems(recepty.getItems());
			stage.setScene(scene);
		    stage.setTitle("Dodaj pacjenta");	
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
		    
		    stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
	public void r_szczegoly() throws IOException {
		System.out.println("cccc");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Szczegoly_recepty.fxml"));
		Pane root = (Pane)fxmlLoader.load();
		Szczegoly_receptyController controller = fxmlLoader.<Szczegoly_receptyController>getController();
		controller.set_labels(recepty.getSelectionModel().getSelectedItem());
		Scene scene = new Scene(root);
		System.out.println("COstam");
		Stage stage = new Stage();
		stage.setScene(scene);
	    stage.setTitle("Szczegó³y");	
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner((Stage) ((Stage)bar.getScene().getWindow()));
	    
	    stage.show();
		
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
			
		} catch (NullPointerException e) {
			
			System.out.println("");
			
			
		}
	}
	
	
	

}
