# API_EXERCISE


Joseph Lilien (jrl48), Virginia Cheng (vc54), Alan Cavalcanti (akm50), Gaurav Kumar (gsk6)


### API Critique

* We analyzed the API for CellSociety Group 23.  We separated the public methods shown into internal and external categories for each of the major project parts and also noted which methods we felt did not belong in the API.

#### External Simulation API

* public Cell(int state, int x, int y, Board parent)

* public FireCell(int s, int x, int y, Board p, double POS) 

* public GameOfLifeCell(int s, int x, int y, Board p) 

* public FireBoard(int size, int[][] state, Game game, boolean toroidal) 

* public SegregationBoard(int size, int[][] state, Game game, boolean toroidal) 

* public SegregationCell(int state, int x, int y, SegregationBoard parent, double satisfaction) 

* public int getNextState() 

* public int getNextState() 

* public GOLBoard(int size, int[][] s, Game g, boolean toroidal) 

* public Cell makeCell(int x, int y);

* public PredatorPreyBoard(int size, int[][] s, Game g, boolean toroidal) 

* public Cell makeCell(int x, int y)

* public void getNewCell()

* public PredatorPreyCell[][] getTentative() 

* public PredatorPreyCell(int s, int x, int y, Board p, int e, int f, int r, int b) 

* public PredatorPreyCell(int s, int x, int y, Board p) 

* public int getNextState() 

* public abstract class Cell { 

    public int getCurrentState()

    public void setState(int s)

    public int getX()

    public int getY()

    public boolean isSameCell(int x, int y) 

}

public class Game  

{

    public void setFilePath(String filepath) 

    public void initialize() 

    public static String getGameType()

    public void readXML(String filePath)

}

In the Game class:

*public void updateBoard()

public abstract class Board 

{ 

      public Board(int size, int[][] s, Game game, int numStates, boolean toroidal) 

      public boolean isToroidal() 

    public void getNewCell()

    public void setState(int x, int y, int newState)

    public void setNextState(int x, int y, int newState) 

    public void setStates()

    public int getState(int x, int y)

    public boolean inBounds(int x, int y) 

    public int getNextState(int x, int y)

    public int getNumStates()

    public int getNumCells()

    public void setSingleState(int x, int y, int newState)

    public List<Map<String, Integer>> getCurrentStateAsMap()

}



#### Internal Simulation API

* public Cell(int state, int x, int y, Board parent)

* public abstract int getNextState()

* public abstract Cell makeCell(int x, int y);

* public abstract class Cell { 

    public int getCurrentState()

    public void setState(int s)

    public int getX()

    public int getY()

    public boolean isSameCell(int x, int y) 

}

* public abstract class Board 

{ 

      public Board(int size, int[][] s, Game game, int numStates, boolean toroidal) 

      public boolean isToroidal() 

    public void getNewCell()

    public void setState(int x, int y, int newState)

    public void setNextState(int x, int y, int newState) 

    public void setStates()

    public int getState(int x, int y)

    public boolean inBounds(int x, int y) 

    public int getNextState(int x, int y)

    public int getNumStates()

    public int getNumCells()

    public void setSingleState(int x, int y, int newState)

    public List<Map<String, Integer>> getCurrentStateAsMap()

}


#### External Configuration API

In the Game Class:

*public void writeCurrentStateXML() 

*public double[] gameCount()

*public double getBoardNumCells()

*public int getNumStates()

*public void changeSingleCell(int x, int y, int newState)

*public int getBoardSize()

public class XMLWriter { 

      public XMLWriter(String location, String gameType) 

    public void writeBasicInfo(String name, String title, String author) 

    public void writeGlobal(boolean toroidal) 

    public void writeInitialState(int width, int height, int numCells, int numOtherCells) 

    public void writeCell(int x, int y, int state) 

    public void finishWriting() 

    public void writeCurrentBoardState(List<Map<String, Integer>> states, int width, int height) 

}

public class XMLParser 

{

    public XMLParser(String location) throws Exception()

    public Map<String, String> getBasicInfo() 

    public Map<String, String> getGlobalInfo() 

    public int[] getSize() 

    public List<Map<String, Integer>> getCells() 

    public static int[][] convertMapToArray(int size, List<Map<String, Integer>> allCells) 

}


#### Internal Configuration API


#### External Visualization API

In the UI Class:

*public void initNewBoard(int numOfCells)

*public void updateBoard()


#### Internal Visualization API

In the UI Class:

*public Scene init()

*public void step(double elapsedTime)

*public String getTitle () 

*public void setTitle(String s) 

*public void setGame(Game g) 

*public Game getGame() 

*public Properties openPropertiesFile (String fileName) throws Exception()

*public String getErrors(String error)


#### Not in API

public class Main extends Application

      public void start (Stage s);

}

In the Game Class:

* public void writeInitialXML(String gametype)

public void setUI(UI ui) 


