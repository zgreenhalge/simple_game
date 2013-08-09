public class Item{

	private Character owner;
	private String affects, name, description;

	public Item(Character c){
		owner=c;
	}
	
	//RETURNS STRING TO DESIGNATE WHAT ITEM MODIFIES
	public String affects(){
		return affects;
	}
	
	//EFFECT THAT ITEM HAS ON STAT
	public int effect(int i){
		//SHOULD BE DEFINED IN EVERY ITEM
		return i;
	}
	
	public void setOwner(Character c){
		owner=c;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String toString(){
		return name + ": " + description;
	}

}
