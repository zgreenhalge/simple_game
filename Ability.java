import java.util.Random;

public class Ability{

	private String name, description;
	private Character owner;
	private Random crit;

	public Ability(){
		this(null);
	}
	
	public Ability(Character c){
		name="Basic ability";
		description="Does nothing";
		owner=c;
		crit=new Random(System.nanoTime());
	}
	
	public String getName(){
		return name;
	}

	public String getDesc(){
		return description;
	}

	public String toString(){
		return getName() + "\n  " + getDesc();
	}

	public void setOwner(Character c){
		owner=c;
	}

	public void activate(Character c){
	//SHOULD BE IMPLEMENTED IN EACH ABILITY
	}

	public void experience(Character c, boolean alive){
		if(alive)
			owner.gainExperience( (int) (owner.getReqExp()*.01) );
		else{
			if(c.getLevel()>owner.getLevel())
				owner.gainExperience( (int) ( 2+ ( c.getLevel()-owner.getLevel()+1 ) *crit.nextInt(3)*owner.getLevel() ) );
			else if (c.getLevel()==owner.getLevel())
				owner.gainExperience( (int) ( 1+ ( crit.nextInt(3)*owner.getLevel() ) ) );
			else
				owner.gainExperience( (int) ( 1+ ( 1/ ( owner.getLevel()-c.getLevel() ) ) *crit.nextInt(3)*owner.getLevel() ) );
		}
	}
	
	public void experience(){
		owner.gainExperience( (int) (owner.getReqExp()*.01) );
	}
}
