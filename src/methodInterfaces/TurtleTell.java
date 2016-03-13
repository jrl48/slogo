package methodInterfaces;

import java.util.*;

import backend.CommandParser;
import frontend.Entry;
import frontend.EntryManager;
import frontend.turtle.MultipleTurtles;
import frontend.turtle.SingleTurtle;

public class TurtleTell implements SpecialTurtleInterface {

	@Override
	public double executeCommand(String command, MultipleTurtles myTurtles, CommandParser parser) {
		String[] params = command.substring(command.indexOf('[')+1, command.indexOf(']')).trim().split("\\s+");
		double args[] = new double[params.length];
		for(int i = 0; i< params.length;i++){
			args[i] = Double.parseDouble(params[i]);
		}
		// TODO Auto-generated method stub
		
		return myTurtles.executeCommand(parser.parseCommand(command.split("\\s+")[0]), args, myTurtles);
	}

}
