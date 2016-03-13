package frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * Defines and Opens HTML Styled Help Page
 * 
 * @author Alan
 *
 */
public class HTMLopener {
    private Stage stage;
    private Button openButton;
    private boolean isOpen;
    private URL helpPageURL = getClass().getResource("/" + UserInterface.DEFAULT_RESOURCE_PACKAGE + "help.html");
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);

    public HTMLopener () {
        openButton = new Button(sceneResources.getString("HELPBUTTON"));
        openButton.getStyleClass().add(sceneResources.getString("BUTTONID"));
        openButton.setOnAction(e -> openHTML());
        stage = new Stage();
        stage.setOnCloseRequest(e -> helpClosed());
        isOpen = false;
    }

    public Button getButton () {
        return openButton;
    }

    private void openHTML () {
        if (isOpen)
        {
        	return;
        }

        isOpen = true;
        WebView webview = new WebView();
        webview.getEngine().load(helpPageURL.toExternalForm());
        stage.setScene(new Scene(webview));
        stage.show();
    }

    private void helpClosed () {
        isOpen = false;
    }

}
