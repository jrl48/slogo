package frontend;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorMessage {
    private String errorTitle = "ERROR";
    Alert errAlert;
    public ErrorMessage(String error){
        initAlert(error);
    }

    private void initAlert (String error) {
        errAlert = new Alert(AlertType.ERROR);
        errAlert.setTitle(errorTitle);
        errAlert.setHeaderText(error);
    }

    public void showError(){
        errAlert.showAndWait();
    }
}
