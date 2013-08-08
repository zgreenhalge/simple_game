public class Rest extends Ability{

	private String name, description;
	private Character owner;
	
	public Rest(Character c){
		name="Rest";
		description="Briefly rests, lowering defense but regenerating health and mana";
		owner=c;
	}

}
