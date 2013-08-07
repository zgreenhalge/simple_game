public class Store{

	private int stat, timer;
	
	public Store(){
		stat = timer = 0;
	}
	
	public Store(int i){
		stat = i;
		timer=0;
	}
	
	public int get(){
		return stat;
	}
	
	public int getTime(){
		return timer;
	}
	
	public int age(){
		return --timer;
