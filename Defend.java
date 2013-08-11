public class Defend extends Ability{
	
	public Defend(Character c){
		name="Defend";
		description="Blocks incoming attacks, raising defense briefly";
		owner=c;
	}
	
	public void activate(){
		owner.addEffect(new Defending(owner));
	}
	
	private class Defending extends StatusEffect{
	
		public Defending(Character o){
			super(o, (int) (o.getDefStruct().BASE*.5), 2);
			affects="def";
			name="Defending";
			description="This unit is defending, increasing it's defense for a short period of time!";
		}
		
		public int effect(int i){
			i+= strength;
			return i;			
		}
	}
}
