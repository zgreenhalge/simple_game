public class Struct{

	public int BASE, stat;
	
	public Struct(){		//A SIMPLE STRUCT FOR STATS
		this(0);		//BECAUSE FUCK JAVA
	}
	
	public Struct(int i){
		stat = BASE = i;
	}

	public Struct(int s, int b){
		stat=s;
		BASE=b;
	}
	
}
