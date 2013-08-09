public class Store{

	public int BASE, stat, timer;
	
	public Store(){
		this(0);
	}
	
	public Store(int i){
		stat = BASE = i;
		timer=0;
	}
	
	public int age(){
		if(timer>0)	
			timer--;
		return timer;
	}
		
}
