
import java.util.Set;


public class MalePerson extends Person 
{

  public MalePerson(String name)
  {
   super(name);
  }

  public MalePerson()
  {
    super();
  }

  public MalePerson(String name, Family chettiamKudiyil)
  {
    super(name,chettiamKudiyil);
  }

  public Set<Person> getBrotherOf()
  {
   return getSiblings();
  }

  public Set<Person> getFatherOf()
  {
    return getChildren();
  }

  public Person getHusbandOf()
  {
    return getSpouse();
  }
 
  
}