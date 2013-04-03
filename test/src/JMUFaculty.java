public class JMUFaculty extends JMUPerson
{
    
    public String department ;
    private double salary ;
    
    public JMUFaculty ( String name , String birth , String dept )
    {
        super ( name , birth );
        department = dept ;
    }
    public String getInformation ()
    {
        return department + " : " + getName () + " " + getBirthDate ();
    }
    public void setSalary ( double amount )
    {
        this . salary = amount ;
    }
    
    public String getSalaryInfo ()
    {
        String result = " " ;
    // Statements go here .
        result = this.name;
        
        result += " " + getBirthDate();
        
        result += String.format(" $%.2f", salary);
        
        result += super.getInformation();
        
        result = getInformation();
        
        
        return result;
    }
}