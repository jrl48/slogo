import frontend.UserInterface;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start (Stage s) throws Exception {
        UserInterface.getUserInterface().init(s);
        s.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
