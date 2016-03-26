// This code is part of my masterpiece
// Virginia Cheng
// I chose this code because I think it shows my current understand of reflection (in the makeMap() method) :
// it utilizes a resource files and the while loop instead of having a hardcoded map, which hides a layer of the program from the user, as well as keeping all data values in a centralized resource file.
// Also it manages all the single turtle objects in a centralized location, which goes with the idea of encapsulation.
// Here the only methods accessible by on outside class are the executeCommands() and the add methods(), but the creation of the turtle map and how the single turtles are accessed is hidden.
// This class also allows the program to access any specific turtle command on any active turtle or chosen turtle.


package frontend.turtle;

import java.util.*;
import java.lang.reflect.InvocationTargetException;
import frontend.Display;
import frontend.DisplayPreferences;
import frontend.Entry;
import frontend.EntryManager;
import frontend.StringObjectEntry;
import javafx.scene.layout.Pane;
import methodinterfaces.DisplayInterface;
import methodinterfaces.MultiTurtleInterface;
import methodinterfaces.TurtleInterface;

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
    private AnimationController animationController;
    private EntryManager colorManager;
    private EntryManager shapeManager;
    private static final String RESOURCE_STRING = "resources/backendResources/";


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
        
        ResourceBundle myTurtleResources = ResourceBundle.getBundle(RESOURCE_STRING+ "turtleCommands");
        
        try {
			createMap(myTurtleResources);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
   
    public void addTurtle () {       
        addTurtle(turtleManager.getEntryList().size()+1);
    }
    
    public void addTurtle (int id) {      
        Turtle turtle = new SingleTurtle(myDisplayPane, animationController);
        turtleManager.addEntry(new StringObjectEntry("Turtle " + id, turtle), false);
        myDisplayPane.getChildren().add(turtle.getBody());
    }
    
    private void createMap(ResourceBundle myResource) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
    	Enumeration<String> myKeys = myResource.getKeys();
    	while (myKeys.hasMoreElements()) {
            String name = myKeys.nextElement();
            String className = myResource.getString(name);
            Class<?> myClass = Class.forName(className);
            Object instance = myClass.newInstance();
            turtleInstructions.put(name, (TurtleInterface) instance);
       }
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
    		    	else if(displayInstructions.containsKey(s)){
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
            if (activeTurtles.contains(id)) {
              	currentTurtles.add(id);
                SingleTurtle turtle = (SingleTurtle) turtleManager.getEntryList().get(i).getSecondValue();
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
