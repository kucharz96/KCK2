package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoeadingController {
	@FXML
	private ProgressBar loading_bar;
	private int param = 0;
	private Centrala C;
	public void initialize() throws IOException
	{
		loading_bar.setMinWidth(200);
		loading_bar.setMaxWidth(Double.MAX_VALUE);
	        IntegerProperty seconds = new SimpleIntegerProperty();
	        loading_bar.progressProperty().bind(seconds.divide(5.0));
	        Timeline timeline = new Timeline(
	            new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
	            new KeyFrame(Duration.seconds(5), e-> {
	                // do anything you need here on completion...
	            	param = 1;
	            	//setItems();
	            	loading_bar.getScene().getWindow().hide();
	                System.out.println("Minute over");
	                return;
	            }, new KeyValue(seconds, 5))   
	        );
	      
	        timeline.play();
	        return;

	}

	public int setItems() {
		// TODO Auto-generated method stub
		
		return param;
	}
}
