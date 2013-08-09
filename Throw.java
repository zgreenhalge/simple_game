public class Throw extends Ability{

	public Throw(Character c){
		name="Throw";
		description="Throws your weapon, reducing damage but with no risk of counter damage";
		owner=c;
	}
	
	public void activate(Character c){
	int dmg = (int) (owner.getAttack()*0.6) - c.getDefense();
		if(dmg<=0){														 //RETURN IF NO DAMAGE WILL BE DONE
			experience(c,false);
			return;
		}
		if( owner.getSpeed() < c.getSpeed())							 //SPEED CALCULATIONS
			if( (float) (c.getSpeed()/100) >= crit.nextFloat() ){		 //DODGE
				experience(c, false);
				return;
			}
		if( (float) (owner.getSpeed()/300) < crit.nextFloat() )			 //CRIT 
			dmg=dmg*3;
		if(c.modHP(-dmg)==0)											 //DO DAMAGE AND CHECK DEATH
			experience(c, false);
		else
			experience(c, true);
	}
	
}
