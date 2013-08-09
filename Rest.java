public class Rest extends Ability{
	
	public Rest(Character c){
		name="Rest";
		description="Briefly rests, gaining health and increasing regeneration for a short while.";
		owner=c;
	}
	
	public void activate(){
		owner.modHP( (int) (owner.getMAXHP()*0.2));
		owner.addEffect(new Rested(owner));
	}
	
	private class Rested extends StatusEffect{
	
		public Rested(Character o){
			super(o, (int) (o.getMAXHP()*0.05), 3);
			affects="HPregen";
			name="Rested";
			description="This unit is well rested, increasing its HP regeneration for a short period of time!";
		}
		
		public int effect(int i){
			i+= strength;
			return i;			
		}
	}
}
