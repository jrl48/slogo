import javafx.application.Application;
import javafx.stage.Stage;
import frontend.*;

public class Main extends Application{
    //private UserInterface myUserInterface = new UserInterface();
    private SLOGOScreen mySLOGOScreen = new SLOGOScreen();
    @Override
    public void start (Stage s) throws Exception {
        mySLOGOScreen.init(s);
        s.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
