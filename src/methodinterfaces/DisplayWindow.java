package methodinterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;

public class DisplayWindow implements TurtleInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		display.setWrap(false);
		return 2;
	}

}
