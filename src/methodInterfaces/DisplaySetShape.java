package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.SingleTurtle;
import javafx.scene.image.ImageView;

public class DisplaySetShape implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		turtle.getPreferences().setImageProperty((ImageView)shapeManager.getValue(Double.toString(args[0])));
		return args[0];
	}

}
