package application;

import javafx.scene.control.TextField;

public class PeselValidator extends TextField {
	   public PeselValidator(){
	       this.setPromptText("Podaj Pesel");
	   }

	   @Override
	   public void replaceText(int i, int il, String string){
	       if(string.matches("[0-9]") || string.isEmpty()){
	          super.replaceText(il, il, string);
	       }
	   }

	    @Override
	    public void replaceSelection(String string) {
	        super.replaceSelection(string); 
	    }



	}