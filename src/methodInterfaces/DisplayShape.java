package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.SingleTurtle;

public class DisplayShape implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		// TODO Auto-generated method stub
		System.out.println("HERE?");
		
		System.out.println(turtle.getBody());
		return Double.parseDouble(shapeManager.getString(turtle.getBody()));
	}

}
