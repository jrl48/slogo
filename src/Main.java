import frontend.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    private UserInterface myUserInterface = new UserInterface();
    @Override
    public void start (Stage s) throws Exception {
        myUserInterface.init(s);
        s.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
