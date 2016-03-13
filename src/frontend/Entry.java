package frontend;

/**
 * Entry interface. All entries in the UI's tables contain similar functionalities, so the interface
 * is used to be implemented by them.
 * 
 * @author JoeLilien
 *
 */
public interface Entry {

	Object getFirstValue ();

	void setFirstValue (Object command);

	Object getSecondValue ();

	void setSecondValue (Object result);
}
