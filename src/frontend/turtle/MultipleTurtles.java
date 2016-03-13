package frontend.turtle;

import java.util.*;
import java.util.List;
import java.util.Map;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.Entry;
import frontend.EntryManager;
import frontend.StringObjectEntry;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import methodInterfaces.DisplayInterface;
import methodInterfaces.DisplayPenColor;
import methodInterfaces.DisplaySetBackground;
import methodInterfaces.DisplaySetPalette;
import methodInterfaces.DisplaySetPenColor;
import methodInterfaces.DisplaySetPenSize;
import methodInterfaces.DisplaySetShape;
import methodInterfaces.DisplayShape;
import methodInterfaces.DisplayStamp;
import methodInterfaces.MultiTurtleInterface;
import methodInterfaces.TurtleBackward;
import methodInterfaces.TurtleClearScreen;
import methodInterfaces.TurtleForward;
import methodInterfaces.TurtleHeading;
import methodInterfaces.TurtleHideTurtle;
import methodInterfaces.TurtleHome;
import methodInterfaces.TurtleID;
import methodInterfaces.TurtleInterface;
import methodInterfaces.TurtleIsPenDown;
import methodInterfaces.TurtleIsShowing;
import methodInterfaces.TurtleLeft;
import methodInterfaces.TurtlePenDown;
import methodInterfaces.TurtlePenUp;
import methodInterfaces.TurtleRight;
import methodInterfaces.TurtleSetHeading;
import methodInterfaces.TurtleSetPosition;
import methodInterfaces.TurtleSetTowards;
import methodInterfaces.TurtleShowTurtle;
import methodInterfaces.TurtleTell;
import methodInterfaces.TurtleTellMulti;
import methodInterfaces.TurtleTurtles;
import methodInterfaces.TurtleXCor;
import methodInterfaces.TurtleYCor;


public class MultipleTurtles {
    private EntryManager turtleManager;
    private Pane myDisplayPane;
    private Display myDisplay;
    private DisplayPreferences myDisplayPreferences;
    private Map<String, TurtleInterface> turtleInstructions =
            new HashMap<String, TurtleInterface>();
    private Map<String, MultiTurtleInterface> multiTurtleInstructions =
            new HashMap<String, MultiTurtleInterface>();
    private Map<String, DisplayInterface> displayInstructions = new HashMap<String, DisplayInterface>();
    private ObjectProperty<Image> defaultTurtleImage;
    private AnimationController animationController;
    private EntryManager colorManager;
    private EntryManager shapeManager;

    public MultipleTurtles (EntryManager turtleManager,
                            Pane displayPane, EntryManager colorManager, EntryManager shapeManager, Display myDisplay, DisplayPreferences myDisplayPreferences,
                            AnimationController animationController) {
    	
    	this.myDisplay = myDisplay;
        this.turtleManager = turtleManager;
        this.myDisplayPane = displayPane;
        this.animationController = animationController;
        this.myDisplayPreferences = myDisplayPreferences;
        this.colorManager = colorManager;
        this.shapeManager = shapeManager;

        addTurtle();
        createTurtleMap();
    }

    public void addTurtle () {       
        Turtle turtle = new SingleTurtle(myDisplayPane, animationController);
        turtleManager.addEntry(new StringObjectEntry("Turtle " + turtleManager.getEntryList().size()+1, turtle), false);
        myDisplayPane.getChildren().add(turtle.getBody());
    }
    
    public void addTurtle (int id) {      
        Turtle turtle = new SingleTurtle(myDisplayPane, animationController);
        turtleManager.addEntry(new StringObjectEntry("Turtle " + id, turtle), false);
        myDisplayPane.getChildren().add(turtle.getBody());
    }

    private void createTurtleMap () { // could make this a loop with a resource file and reflection
                                      // <<< do if there is time
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
        displayInstructions.put("SetBackground", new DisplaySetBackground());
        displayInstructions.put("SetPenColor", new DisplaySetPenColor());
        displayInstructions.put("SetPenSize", new DisplaySetPenSize());
        displayInstructions.put("SetShape", new DisplaySetShape());
        displayInstructions.put("SetPalette", new DisplaySetPalette());
        displayInstructions.put("GetPenColor", new DisplayPenColor());
        displayInstructions.put("GetShape", new DisplayShape());
        displayInstructions.put("Stamp", new DisplayStamp());
        multiTurtleInstructions.put("ID", new TurtleID());
        multiTurtleInstructions.put("Turtles", new TurtleTurtles());
        multiTurtleInstructions.put("Tell", new TurtleTellMulti());
      //  multiTurtleInstructions.put("Ask", new TurtleAsk());
        //multiTurtleInstructions.put("AskWith", new TurtleAskWith());
    }

    public double executeCommand (String s, double[] args, MultipleTurtles myTurtles) {
    		double value = 0.0;
    		for (Entry t : turtleManager.getEntryList()) {
    			SingleTurtle turtle = (SingleTurtle) t.getSecondValue();
    			if (turtle.isActive()) {
    		    	if(turtleInstructions.containsKey(s)){
    		    		TurtleInterface turtleCommand = turtleInstructions.get(s);
    		    		value = turtleCommand.executeCommand(args, turtle);
    		    	}
    		    	else if(turtleInstructions.containsKey(s)){
    		    		System.out.println("??");
    		    		DisplayInterface displayCommand = displayInstructions.get(s);
    		    		value = displayCommand.executeCommand(args, turtle, myDisplay, myDisplayPreferences, colorManager, shapeManager);
    		    	}
    			}
    		}
    		
    		if(multiTurtleInstructions.containsKey(s)){
    			MultiTurtleInterface turtleCommand = multiTurtleInstructions.get(s);
	    		value = turtleCommand.executeCommand(args, turtleManager, myTurtles);
    		}
    		return value;
    }

    public double executeCommand (String s, double[] args, List<Integer> activeTurtles) {
    	if(!turtleInstructions.containsKey(s)){
		    return 0.0;
    	}
        TurtleInterface turtleCommand = turtleInstructions.get(s);
        double value = 0.0;
        Set<Integer> currentTurtles = new HashSet<Integer>();
        
        for (int i = 0; i < turtleManager.getEntryList().size(); i++) {
        	int id = Integer.parseInt(((String) turtleManager.getEntryList().get(i).getFirstValue()).split("\\s+")[1]);
        	System.out.println(id);
            if (activeTurtles.contains(id)) {
              	currentTurtles.add(id);
                SingleTurtle turtle =
                        (SingleTurtle) turtleManager.getEntryList().get(i).getSecondValue();
                value = turtleCommand.executeCommand(args, turtle);
            }
        }
        
        for(int x: activeTurtles){
        	if(!currentTurtles.contains(x)){
        		addTurtle( (int) x);
        	}
        }
        
        return value;
    }
}
