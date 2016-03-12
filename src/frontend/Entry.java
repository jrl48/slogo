package frontend;

/**
 * Entry interface. All entries in the UI's tables contain similar functionalities, so the interface
 * is used to be implemented by them.
 * 
 * @author JoeLilien
 *
 */
public interface Entry {
    
    public Object getFirstValue ();

    public void setFirstValue (Object command);

    public Object getSecondValue ();

    public void setSecondValue (Object result);
}
