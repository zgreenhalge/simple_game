public class Store{

	public int stat, timer;
	
	public Store(){
		stat = timer = 0;
	}
	
	public Store(int i){
		stat = i;
		timer=0;
	}
	
	public void age(){
		timer--;
	}
		
}
