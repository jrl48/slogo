package frontend;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;



/**
 * This class contains all of the controls that allows the user to interactively alter properties of
 * the turtle. It extends the Preferences superclass
 * 
 * @author JoeLilien
 *
 */
public class TurtlePreferences extends Preferences {
    private double penWidth = 1.0;
    private ResourceBundle prefResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + "Pref");
    private ColorPicker penColor = new ColorPicker(Color.BLACK);
    private ObjectProperty<Image> imageProperty =
            new SimpleObjectProperty<Image>(new Image(getClass().getClassLoader()
                    .getResourceAsStream(UserInterface.DEFAULT_RESOURCE_PACKAGE + "turtle.png")));
    private Button chooseImage = new Button(prefResources.getString("IMAGE_CHOICE_TITLE"));
    private ContextMenu prefWindow;
    private TextField penWidthField = new TextField("Set Pen Width");
    private CheckBox penDash = new CheckBox("Dashed Line");
    private CheckBox penActive = new CheckBox("Pen Down");

    // List of Menu Options in ContextMenu
    private List<Menu> myOptions =
            new ArrayList<>(Arrays.asList(new Menu("Turtle Image"), new Menu("PenProperties")));

    // List of Options Specific to Turtle Image Setting
    private ArrayList<MenuItem> turtleImageSubOptions =
            new ArrayList<>(Arrays.asList(new CustomMenuItem(chooseImage)));

    // Lists of Options Specific to Pen Properties Settings (need both because of bug inherit in
    // javafx.customCell)
    private ArrayList<MenuItem> penPropertiesSubOptions =
            new ArrayList<>(Arrays.asList(new CustomMenuItem(penDash),
                                                  new CustomMenuItem(penActive)));
    private List<Node> additionalPenProperties =
            new ArrayList<>(Arrays.asList(penWidthField, penColor));

    // Combined List of All SubOptions Lists
    private List<ArrayList<MenuItem>> myControls =
            new ArrayList<ArrayList<MenuItem>>(Arrays.asList(turtleImageSubOptions,
                                                             penPropertiesSubOptions));

    /**
     * Class Constructor, calls init method
     * 
     */
    public TurtlePreferences () {
        initDisplayPreferences();
    }

    private void initDisplayPreferences () {
        prefWindow = new ContextMenu();
        initFileChooser(new FileChooser());

        // Need to do this separately because of bug in Java FX Custom Cells
        super.addMoreOptions(additionalPenProperties, penPropertiesSubOptions);

        initPenPrefrences();
        super.initOptions(myOptions, myControls);
        prefWindow.getItems().addAll(myOptions);
    }

    /**
     * Sets action response for text field and initializes check boxes to unSelected
     * 
     */
    private void initPenPrefrences () {
        penWidthField.setOnAction(e -> updatePenThickness(penWidthField.getText()));
        penDash.setSelected(false);
        penActive.setSelected(false);
    }

    /**
     * Sets Pen Width Property according to user input, if input is invalid, throws an error to the
     * user. Method is called upon User clicking ENTER in the text field area
     * 
     * @param width
     */
    private void updatePenThickness (String width) {
        try {
            setPenWidth(Double.parseDouble(width));
        }
        catch (NumberFormatException e) {
            ErrorMessage err = new ErrorMessage("Legit Value");
            err.showError();
            penWidthField.setText(prefResources.getString("DOUBLE_CONVERSION_ERR"));
        }
    }

    private void initFileChooser (FileChooser imageChoice) {
        imageChoice.setTitle(prefResources.getString("IMAGE_CHOICE_TITLE"));
        imageChoice.getExtensionFilters()
                .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        chooseImage.setOnAction(e -> openImageChoice(imageChoice));
    }

    /**
     * Open the window.
     * 
     * @param owner
     * @param x
     * @param y
     */
    public void openPreferences (Node owner, double x, double y) {
        prefWindow.show(owner, x, y);
    }

    /**
     * Lets the user pick an image to show as turtle.
     * Throws exception if not found.
     * 
     * @param imageChoice
     */
    private void openImageChoice (FileChooser imageChoice) {
        File file = imageChoice.showOpenDialog(prefWindow.getOwnerWindow());
        if (file != null) {
            try {
                String fileName = file.toURI().toURL().toString();
                imageProperty.setValue(new Image(fileName));
            }
            catch (MalformedURLException e) {
                ErrorMessage err = new ErrorMessage(prefResources.getString("BAD_URL_ERROR"));
                err.showError();
            }

        }
    }

    public double getPenWidth () {
        return this.penWidth;
    }

    public boolean isDashed () {
        return this.penDash.isSelected();
    }

    public BooleanProperty isPenActive () {
        return penActive.selectedProperty();
    }

    public void setPenWidth (double width) {
        this.penWidth = width;
    }

    public Color getPenColor () {
        return penColor.getValue();
    }

    public void setPenColor (Color col) {
        this.penColor.setValue(col);
    }

    public ObjectProperty<Image> getImageProperty () {
        return imageProperty;
    }

    public void setImageProperty (ImageView image) {
        imageProperty.setValue(image.getImage());
    }

}
