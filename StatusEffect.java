public class StatusEffect {
	
	protected Character owner;
	protected int strength, timer;
	protected String affects, name, description;
	
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
	
	public void setOwner(Character c){
		owner=c;
	}
	
	public int age(){
		if(timer>0)
			timer--;
		return timer;
	}
	
	public String affects(){
		return affects;
	}
	
	public int effect(int i){
		return i;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDesc(){
		return description;
	}
	
	public boolean equals(StatusEffect se){
		if (name.equals(se.getName()))
			return true;
		return false;
	}
}
