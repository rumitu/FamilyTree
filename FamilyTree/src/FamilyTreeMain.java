
public class FamilyTreeMain
{
  public static void main(String[] args){
    Family chettiamKudiyil = new Family("Chettiyamkudiyil");
    Family varuvakayil = new Family("varuvakalayil");
    MalePerson ajo = new MalePerson("ajo");
    MalePerson joy = new MalePerson("joy",chettiamKudiyil);
    MalePerson chacko = new MalePerson("chacko",varuvakayil);
  
    FemalePerson jomy = new FemalePerson("jomy");
    FemalePerson ammini = new FemalePerson("ammini");
    FemalePerson maya = new FemalePerson("maya");
    FemalePerson mini = new FemalePerson("mini");
    FemalePerson aleyama = new FemalePerson("aleyama");
    FemalePerson manju = new FemalePerson("manju");
    
    aleyama.addSpouse(chacko);
    ajo.addSibling(jomy);
    joy.addChild(ajo);
    joy.addSpouse(ammini);
    maya.addSpouse(ajo);
    maya.addParent(chacko);
    maya.addSibling(mini);
    mini.addSibling(manju);
   
    System.out.println("Created ");
  }
}
