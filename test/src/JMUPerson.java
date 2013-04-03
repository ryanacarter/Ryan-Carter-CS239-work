public abstract class JMUPerson implements Informative
{
    protected String name ;
    private String dateOfBirth ;
    protected final String school = " JMU " ;
    
    
    public JMUPerson ( String name , String birthdate )
    {
        this . name = name ;
        this . dateOfBirth = birthdate ;
    }
    public String information ()
    {
        return String . format ( school +
                " Person : % s \ tBirthdate : % s \ n " , name , dateOfBirth );
    }
    public String toString ()
    {
        return getInformation ();
    }
    public String getName ()
    {
        return name ;
    }
    public String getBirthDate ()
    {
        return dateOfBirth ;
    }
}