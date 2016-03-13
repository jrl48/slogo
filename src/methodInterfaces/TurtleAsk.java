package methodInterfaces;

import java.util.ArrayList;
import java.util.List;

import backend.CommandParser;
import frontend.EntryManager;
import frontend.MultipleTurtles;

public class TurtleAsk implements SpecialTurtleInterface {

	@Override
	public double executeCommand(String command, MultipleTurtles myTurtle, CommandParser parser) {
		String params = command.substring(command.indexOf('[')+1, command.indexOf(']')).trim();
		String[] commandArray = command.substring(command.indexOf('[', command.indexOf(']'))+1, command.length() -1).trim().split("\\s+");
		String newCommand = commandArray[0];
		double[] args = new double[commandArray.length - 1];
		
		for(int i = 1; i < commandArray.length;i++){
			args[i-1] = Double.parseDouble(commandArray[i]);
			
		}
		
		List<Integer> paramArray = new ArrayList<Integer>();
		for(String s: params.split("\\s+")){
			paramArray.add(Integer.parseInt(s));
		}
		
		return myTurtle.executeCommand(parser.parseCommand(newCommand), args, paramArray);
	}

}
