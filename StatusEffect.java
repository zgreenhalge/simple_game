public class StatusEffect {
	
	private Character owner;
	private int strength, timer;
	
	public StatusEffect(){
		this(null, 0, 0);
	}
	
	public StatusEffect(Character c){
		this(c, 0, 0);
	}
	
	public StatusEffect(Character c, int s, int t){
		owner=c;
		strength=s;
		timer=t;
	}
	
	public void age(){
		if(timer>0)
			timer--;
		//DEFINE IN EACH EFFECT 
	}
}
