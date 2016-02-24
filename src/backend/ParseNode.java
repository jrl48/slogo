package backend;

import java.util.*;

public class ParseNode {
	    
	    private String myValue;
	    private ArrayList<ParseNode> myChildren;


	    public ParseNode(String value) {
	        myValue = value;
	        myChildren = new ArrayList<ParseNode>();
	    }
	    
//	    public ParseNode(String value, List<String> children ) {
//	        myValue = value;
//	        myChildren = children;
//	    }
	    
	    public void setValue(String value){
	    	myValue = value;
	    }

	    public void addChild(ParseNode node){
	        myChildren.add(node);
	    }
	    
	    public ArrayList<ParseNode> getChildren(){
	    	return myChildren;
	    }


}
