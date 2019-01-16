package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	 private static Stage guiStage;
	 
	 public static Stage getStage() {
	        return guiStage;
	    }
	 @Override
	public void start(Stage primaryStage) {
		try {
			
			guiStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Logowanie.fxml"));
			GridPane root = (GridPane)loader.load();
			Scene scene = new Scene(root,400,160);
			
	
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Logowanie");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Centrala centrala = Centrala.getInstance();
		MainController.setCentrala(centrala);
		centrala.setStan(new Niezalogowany(centrala));
		
		launch(args);
	}
}
