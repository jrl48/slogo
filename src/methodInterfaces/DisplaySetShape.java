package methodInterfaces;

import java.util.ResourceBundle;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.ErrorMessage;
import frontend.UserInterface;
import frontend.turtle.SingleTurtle;
import javafx.scene.image.ImageView;

public class DisplaySetShape implements DisplayInterface {
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		try{	
			turtle.getPreferences().setImageProperty((ImageView)shapeManager.getValue(Integer.toString((int)args[0])));
			return args[0];
		}
	   catch(IllegalArgumentException e){
		    ErrorMessage err = new ErrorMessage(sceneResources.getString("INVALID_NUM"));
		    err.showError();
		    return 0.0;
		}
	}

}
