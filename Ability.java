public class Ability{

	private String name, description;
	private Character owner;
	private Random crit;

	public Ability(Character c){
		name="Basic ability";
		description="Does nothing";
		owner=c;
	}
	
	public String getName(){
		return name;
	}

	public String getDesc(){
		return description;
	}

	public toString(){
		return getName() + "\n  " + getDesc();
	}

	public void setOwner(Character c){
		owner=c;
	}

	public void do(Character c){
	//SHOULD BE IMPLEMENTED IN EACH ABILITY
	}

	//RETURNS ARRAY OF BASIC ABILITIES
	public static Ability[] default(Character c){
		Ability[] ret = new Ability[4];
		ret[1] = new Attack(owner);
		ret[2] = new Defend(owner);
		ret[3] = new Rest(owner);
		ret[4] = new Throw(owner);

	}

	public class Attack extends Ability{
	
		public Attack(Character c){
			name="Attack";
			description="A basic phsyical attack";
			owner=c;
		}

		public void do(Character c){
			crit = new Random(System.nanoTime());
			int dmg = owner.getAttack() - c.getDefense();
			if(dmg<0){
				dmg==0;
				return;
			}
			if( owner.getSpeed() < c.getSped())		//DODGE
				if( (float) c.getSpeed()/100 < crit.nextFloat() )
					return;
				else
					owner.modHP((int) dmg*(-0.3));
			if( (float) owner.getSpeed/30 < crit.nextFloat() )	//CRIT 
				dmg=dmg*3;
			if(c.modHP(-dmg)==0)			//KILL
				if(c.getLevel()>owner.getLevel())
					owner.gainExperience((int) (2+(c.getLevel()-owner.getLevel()+1)*crit.nextInt(3)*owner.getLevel()));
				else
					owner.gainExperience((int) (1+(1/(owner.getLevel()-c.getLevel()))*crit.nextInt(3)*owner.getLevel()));
		}
	}
}
