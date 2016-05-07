package methodinterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;

public class DisplayWrap implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.setWrap(true);
		return 1;
	}

}
