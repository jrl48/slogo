package backend;

import java.util.HashMap;
import java.util.Map;
import methodInterfaces.*;
import frontend.Display;

public class Commands{
	private Map<String, TurtleInterface> turtleInstructions = new HashMap<String, TurtleInterface>();
	private Map<String, MathInterface> instructions = new HashMap<String, MathInterface>();
	
	public Commands(){
		createInstructionMap();
		createTurtleMap();

		//instructions.put(, value)
	}
	
	private void createInstructionMap() {
		instructions.put("Sum", new MathSum());
		instructions.put("Difference", new MathDifference());
		instructions.put("Product", new MathProduct());
		instructions.put("Quotient", new MathQuotient());
		instructions.put("Remainder",  new MathRemainder());
		instructions.put("Minus", new MathMinus());
		instructions.put("Random", new MathRandom());
		instructions.put("Sine", new MathSin());
		instructions.put("Cosine", new MathCos());
		instructions.put("Tangent", new MathTan());
		instructions.put("ArcTangent", new MathAtan());
		instructions.put("NaturalLog", new MathLog());
		instructions.put("Power", new MathPow());
		instructions.put("Pi", new MathPi());
		instructions.put("LessThan", new BooleanLess());
		instructions.put("GreaterThan", new BooleanGreater());
		instructions.put("Equal", new BooleanEqual());
		instructions.put("NotEqual", new BooleanNotequal());
		instructions.put("And", new BooleanAnd());
		instructions.put("Or", new BooleanOr());
		instructions.put("Not", new BooleanNot());
	}
	
	private void createTurtleMap() {
		turtleInstructions.put("Forward", new TurtleForward());
		turtleInstructions.put("Backward", new TurtleBackward());
		turtleInstructions.put("Right", new TurtleRight());
		turtleInstructions.put("Left", new TurtleLeft());
		turtleInstructions.put("SetHeading", new TurtleSetHeading());
		turtleInstructions.put("SetTowards", new TurtleSetTowards());
		turtleInstructions.put("SetPosition", new TurtleSetPosition());
		turtleInstructions.put("PenUp", new TurtlePenUp());
		turtleInstructions.put("PenDown", new TurtlePenDown());
		turtleInstructions.put("ShowTurtle", new TurtleShowTurtle());
		turtleInstructions.put("HideTurtle", new TurtleHideTurtle());
		turtleInstructions.put("Home", new TurtleHome());
		turtleInstructions.put("ClearScreen", new TurtleClearScreen());
		turtleInstructions.put("XCoordinate", new TurtleXCor());
		turtleInstructions.put("YCoordinate", new TurtleYCor());
		turtleInstructions.put("Heading", new TurtleHeading());
		turtleInstructions.put("IsPenDown", new TurtleIsPenDown());
		turtleInstructions.put("IsShowing", new TurtleIsShowing());
		turtleInstructions.put("SetBackground", new DisplaySetBackground());
		turtleInstructions.put("SetPenColor", new DisplaySetPenColor());
		turtleInstructions.put("SetPenSize", new DisplaySetPenSize());
		turtleInstructions.put("SetShape", new DisplaySetShape());
		turtleInstructions.put("SetPalette", new DisplaySetPalette());
		turtleInstructions.put("PenColor", new DisplayPenColor());
		turtleInstructions.put("Shape", new DisplayShape());
		turtleInstructions.put("Stamp", new DisplayStamp());
//		turtleInstructions.put("Id", new TurtleID());
//		turtleInstructions.put("Turtles", new TurtleTurtles());
//		turtleInstructions.put("Tell", new TurtleTell());
//		turtleInstructions.put("Ask", new TurtleAsk());
//		turtleInstructions.put("AskWith", new TurtleAskWith());
		
		
		
	}
	
	public double callCommand(String s, double[] args, Display display){
		if ( instructions.containsKey(s)) {
			MathInterface command = instructions.get(s);
			return command.executeCommand(args);
		} else {
			TurtleInterface turtleCommand = turtleInstructions.get(s);
			return turtleCommand.executeCommand(args, display);
		}
		
	}
	
}