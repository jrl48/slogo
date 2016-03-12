package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.SingleTurtle;
import javafx.scene.paint.Color;

public class DisplaySetPalette implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		colorManager.set(args[0],new Color(args[1],args[2],args[3],1.0));
		return args[0];
	}

}
