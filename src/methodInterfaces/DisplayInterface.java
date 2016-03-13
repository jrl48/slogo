package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;

public interface DisplayInterface {
	public double executeCommand(double[] args, SingleTurtle turtle, 
			Display display, DisplayPreferences displayPreferences, 
			EntryManager colorManager, EntryManager shapeManager);
}
