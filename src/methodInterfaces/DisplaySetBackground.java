package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;
import javafx.scene.paint.Color;

public class DisplaySetBackground implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		displayPreferences.setDisplayColor((Color) colorManager.getValue(Integer.toString((int)args[0])));
		return args[0];
	}
}
