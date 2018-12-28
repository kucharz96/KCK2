package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Szczegoly_wizytyController extends MainController {
	@FXML
	private Label pacjent, lekarz, godzina, data, opis;
	@FXML
	private Button  p_ok;
	public void set_labels(Wizyta a) {
		for (Lekarz L: centrala.getLekarze())
		{
			System.out.println(L.getId());
			System.out.println(a.getId_lekarza());
			if(L.getId() == a.getId_lekarza())
			lekarz.setText((Integer.toString(a.getId_lekarza()) + " : "+L.getImie()+" "+L.getNazwisko()));
		}
		for (Pacjent L: centrala.getPacjenci())
		{

			if(L.getPesel().equals(a.getPesel_pacjenta()))
			pacjent.setText((a.getPesel_pacjenta() + " : "+L.getImie()+" "+L.getNazwisko()));
		}
		Date date = null;
		opis.setText(a.getOpis());
		String dataa = a.getData().toString();
		String dataaa[] = dataa.split(" ",6);
		String godzina_minuta = dataaa[3];
		String godzinka_minutka[] = godzina_minuta.split(":", 3);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			date = formatter.parse(dataaa[2]+"-"+dataaa[1]+ "-"+ dataaa[5]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setText(dataaa[2]+" "+dataaa[1]+ " "+ dataaa[5]);
		godzina.setText(dataaa[3]+":"+dataaa[4]);

		
		
	}
	public void zamknijOkno(ActionEvent event)
	{
		   Stage stage = (Stage) p_ok.getScene().getWindow();
		   stage.close();
	}


}