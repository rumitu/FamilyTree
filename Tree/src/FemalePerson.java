
import java.util.Set;



public class FemalePerson extends Person 
{
  
  public FemalePerson(String name)
  {
    super(name);
  }

  public FemalePerson()
  {
  }

  
  public Set<Person> getSisterOf()
  {
    return getSiblings();
  }
 
  public Person getWifeOf()
  {
    return getSpouse();
  }

  public Set<Person> getMotherOf()
  {
    return getChildren();
  }
 
}