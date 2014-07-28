import java.util.HashSet;
import java.util.Set;

public class Person
{
  String name;
  int age;
  String gender;
  Set<Person> siblings;
  Set<Person> children;
  Person spouse;
  Set<Person> parents;
  Family family;
  
  public Person(String name){
    this.name = name;
  }
  
  public Person(String name,String familyName){
    this(name,new Family(familyName));
  }
  
  public Person()
  {
  }

  public Person(String name, Family family)
  {
    this.name = name;
    this.family = family;
  }

  public Person getSpouse()
  {
    return spouse;
  }
  
  /**
   * Add spouse and also transfer the children.
   * @param spouse
   */
  public void addSpouse(Person spouse)
  {
    this.spouse = spouse;
    if(null == spouse.getSpouse()){
      spouse.addSpouse(this);
    }
    Set<Person> thisChildren = this.children;
    Set<Person> spouseChildren = spouse.getChildren();
    
    if(null == thisChildren && null != spouseChildren){//Spouse has kids and this doesn't
      copyChildren(spouseChildren,this);
    }else if(null != thisChildren && null == spouseChildren){//If this has kids and spouse doesn't
      copyChildren(thisChildren,spouse);
    }else if(null != thisChildren && null != spouseChildren){
      if(thisChildren.size() > spouseChildren.size()){
        copyChildren(thisChildren,spouse);
      }else if(thisChildren.size() < spouseChildren.size()){
        copyChildren(spouseChildren,this);
      }
    }
    
  }


  /*
   * Copy the set of fromChildren to person toPerson.
   * @param fromChildren
   * @param toPerson
   */
  private void copyChildren(Set<Person> fromChildren,Person toPerson)
  {
    for(Person child:fromChildren){
      toPerson.addChild(child);
    }
  }
  
  private void setParents(Set<Person> parent){
    this.parents = parent;
  }
  
  /**
   * Set this person as child of parent.
   * also set the same for this person's siblings.
   * also add this child to parent as a child.
   * @param parent
   */
  public void addParent(Person parent){
    if(null != parents) {
      if(!isPersonAlreadyAdded(parents, parent) && parents.size() < 2){
        parents.add(parent);
      }else{
        return;
      }
    }else{
      parents = new HashSet<Person>(2);
      parents.add(parent);
    }
    
    Set<Person> tempSiblings = getSiblings();
    if(null != tempSiblings)
      for(Person sib:tempSiblings){
        sib.addParent(parent);
      }
    
    parent.addChild(this);
    
    if(null != parent.getSpouse()){
      parent.getSpouse().addChild(this);
    }
  }

  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public int getAge()
  {
    return age;
  }
  public void setAge(int age)
  {
    this.age = age;
  }
  public String getGender()
  {
    return gender;
  }
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  /**
   * Add person as a sibling also add person as a child to this person's parent.
   * @param person
   */
  public void addSibling(Person person){
    if(null != siblings){
      if(!isPersonAlreadyAdded(siblings,person)){
        siblings.add(person);
        for(Person sib:siblings){
          if(!sib.equals(person))
            sib.addSibling(person);
        }
      }else{
        return;
      }
    }else{
      siblings = new HashSet<Person>();
      siblings.add(person);
    }
    
    if(null != this.parents){
      if(null != person.getParents()){
        if(this.parents.size() > person.getParents().size()){
          person.setParents(this.parents);
        }else if(this.parents.size() < person.getParents().size()){
          this.setParents(person.parents);
        }
      }else{
        for(Person parent:this.parents){
          person.addParent(parent);
        }
      }
    }
    
    person.addSibling(this);
  }
  
  public Set<Person> getParents(){
   return parents; 
  }
  
  /**
   * Check if the person is alread there in the set of relations.
   * @param relations
   * @param person
   * @return boolean
   */
  private boolean isPersonAlreadyAdded(Set<Person> relations,Person person)
  {
    boolean isAdded = false;
    for(Person per:relations){
      if(per.equals(person)){
        isAdded = true;
        break;
      }
    }
    return isAdded;
  }
  
  
  /**
   * Add person as this person's child 
   * and the person's siblings as this person's child.
   * @param person
   */
  public void addChild(Person person){
    if(null != children){
      if(!isPersonAlreadyAdded(children, person)){
        children.add(person);
      }else{
        return;
      }
    }else{
      children = new HashSet<Person>();
      children.add(person);
    }

    person.addParent(this);
  
    if(null != this.getSpouse()){
      this.getSpouse().addChild(person);
    }
  }
  
  public Set<Person> getSiblings(){
    return siblings;
  }
  
  public Set<Person> getChildren(){
    return children;
  }
  
  public Person getMother(){
    if(null != parents){
      for(Person parent:parents){
        if(parent instanceof FemalePerson){
          return parent;
        }
      }
    }
    return null;
  }
  
  public Person getFather(){
    if(null != parents){
      for(Person parent:parents){
        if(parent instanceof MalePerson){
          return parent;
        }
      }
    }
    return null;
  }
  
  @Override
  public String toString()
  {
    if(null != getName()){
      return getName();
    }
    return super.toString();
  }
}
