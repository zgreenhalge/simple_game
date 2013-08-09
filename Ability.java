import java.util.Random;

public class Ability{

	protected String name, description;
	protected Character owner;
	protected Random crit;

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
		return getName() + ": " + getDesc();
	}

	public void setOwner(Character c){
		owner=c;
	}

	public void activate(Character c){
	//SHOULD BE IMPLEMENTED IN EACH ABILITY
	}

	public void experience(Character c, boolean alive){
		if(alive)
			experience();
		else{
			if(c.getLevel()>owner.getLevel())
				owner.gainExperience(c.expReward()+((int) ((c.getLevel()-owner.getLevel())*.5)));
			else if (c.getLevel()==owner.getLevel())
				owner.gainExperience(c.expReward());
			else
				owner.gainExperience((int) (c.expReward()*(1/(owner.getLevel()-c.getLevel()))));
		}
	}
	
	public void experience(){
		owner.gainExperience( (int) (owner.getReqExp()*.01) );
	}
}
