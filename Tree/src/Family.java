
public class Family
{
  String name;

  public Family(String name){
    this.name = name;
  }
  
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
  @Override
  public String toString()
  {
   if(null != name){
     return name;
   }
    return super.toString();
  }
}
