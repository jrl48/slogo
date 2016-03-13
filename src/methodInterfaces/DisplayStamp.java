package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;

public class DisplayStamp implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		turtle.stamp();
		return 1;
	}

}
