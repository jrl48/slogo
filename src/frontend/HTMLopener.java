package frontend;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLopener 
{
	private Stage stage;
	private Group root;
	private Button openButton;
	private boolean isOpen;
	
	public HTMLopener()
	{
		openButton = new Button("Help"); // TODO: Get it from resource file
		openButton.setOnAction(e -> openHTML());
		stage = new Stage();
		stage.setOnCloseRequest(e -> helpClosed());
		isOpen = false;
	}

	public Button getButton()
	{
		return openButton;
	}

	public void openHTML()
	{
		if ( isOpen )
			return;
		
		isOpen = true;
		URL url = getClass().getResource("/"+ UserInterface.DEFAULT_RESOURCE_PACKAGE+ "help.html");
		System.out.println(url);
		WebView webview = new WebView();
		webview.getEngine().load(url
			    .toExternalForm()); 	// TODO: resource file!
		stage.setScene(new Scene(webview));
		stage.show();
	}

	private void helpClosed()
	{
		isOpen = false;
	}	

}
