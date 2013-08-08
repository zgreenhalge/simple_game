import java.util.*;

public class Attack extends Ability{
	
	private String name, description;
	private Character owner;
	private Random crit;
	
	public Attack(Character c){
		name="Attack";
		description="A basic phsyical attack";
		owner=c;
	}

	public void activate(Character c){
		crit = new Random(System.nanoTime());
		int dmg = owner.getAttack() - c.getDefense();
		if(dmg<0){
			dmg=0;
			return;
		}
		if( owner.getSpeed() < c.getSpeed())				//DODGE
			if( (float) (c.getSpeed()/100) < crit.nextFloat() )
				return;
			else
				owner.modHP((int) (dmg*(-0.3)));
		if( (float) (owner.getSpeed()/30) < crit.nextFloat() )	//CRIT 
			dmg=dmg*3;
		if(c.modHP(-dmg)==0)							//DO DAMAGE AND CHECK DEATH
			experience(c, false);
		else
			experience(c, true);
	}
}
