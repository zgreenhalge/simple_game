import java.util.*;

public class Attack extends Ability{
		
	public Attack(Character c){
		super(c);
		name="Attack";
		description="A basic phsyical attack";
	}

	public void activate(Character c){
		int dmg = owner.getAttack() - c.getDefense();
		if(dmg<=0){														 //RETURN IF NO DAMAGE WILL BE DONE
			experience(c,false);
			return;
		}
		if( owner.getSpeed() < c.getSpeed())							 //SPEED CALCULATIONS
			if( (float) (c.getSpeed()/100) >= crit.nextFloat() ){		 //DODGE
				experience(c, false);
				return;
			}
			else if ( (float) (c.getSpeed()/50) >= crit.nextFloat() )	 //COUNTER ATTACK
				owner.modHP((int) (c.getAttack()*(-0.2)));				
		if( (float) (owner.getSpeed()/150) < crit.nextFloat() )			 //CRIT 
			dmg=dmg*3;
		if(c.modHP(-dmg)==0)											 //DO DAMAGE AND CHECK DEATH
			experience(c, false);
		else
			experience(c, true);
	}
}
