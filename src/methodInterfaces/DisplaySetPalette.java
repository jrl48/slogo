package methodInterfaces;

import java.util.ResourceBundle;
import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.ErrorMessage;
import frontend.SingleTurtle;
import frontend.UserInterface;
import javafx.scene.paint.Color;

public class DisplaySetPalette implements DisplayInterface {
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		try{		   
		    colorManager.set((int)args[0],new Color(args[1]/255,args[2]/255,args[3]/255,1.0));
		    return args[0];
		}
		catch(IllegalArgumentException e){
		    ErrorMessage err = new ErrorMessage(sceneResources.getString("INVALID_RGB"));
		    err.showError();
		    return 0.0;
		}
	}

}
