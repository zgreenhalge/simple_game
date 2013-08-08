public class Store{

	public int stat, timer;
	
	public Store(){
		this(0);
	}
	
	public Store(int i){
		stat = i;
		timer=0;
	}
	
	public void age(){
		if(timer>0)	
			timer--;
	}
		
}
