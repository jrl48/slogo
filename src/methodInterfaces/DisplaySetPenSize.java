package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;
import javafx.scene.paint.Color;

public class DisplaySetPenSize implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		// TODO Auto-generated method stub
		turtle.getPreferences().setPenWidth(args[0]);
		return args[0];
	}

}
