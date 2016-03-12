package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.SingleTurtle;

public class DisplaySetShape implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		turtle.getPreferences().setImageProperty(shapeManager.getValue(Double.toString(args[0])));
		return args[0];
	}

}
