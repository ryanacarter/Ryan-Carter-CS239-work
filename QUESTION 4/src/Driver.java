public class Driver
{
    public static void main ( String args [])
    {
        Informative [] list ;
        JMUPerson person ;
        Student student ;
        person = new JMUFaculty ( " James " , " 05/15/1962 " , " CS " );
        student = new Student ( " Spotswood " , " Molly Smith " , 12);
        list = new Informative [2];
        list [0] = person ;
        list [1] = student ;
        
        for ( Informative p : list )
        {
            describe ( p );
        }
        describe ( person );
        describe ( student );
    }
    public static void describe ( JMUPerson somePerson )
    {
        System . out . println ( " Person description : " +
                somePerson . getInformation ());
    }
    public static void describe ( Informative someInformative )
    {
        System . out . println ( " Description : " +
                someInformative . getInformation ());
    }
}