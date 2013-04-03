public class Student implements Informative
{
    private String school , name ;
    private int age ;
    
    public Student ( String school , String name , int age )
    {
        this . school = school ;
        this . name = name ;
        this . age = age ;
    }
    public String getName ()
    {
        return name ;
    }
    public String getInformation ()
    {
        return school + " student " + name + " ( " + age + " ) " ;
    }
    public String toString ()
    {
        return name + " attends " + school ;
    }
}