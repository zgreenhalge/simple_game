public class Throw extends Ability{

	private String name, description;
	private Character owner;

	public Throw(Character c){
		name="Throw";
		description="Throws your weapon, reducing damage but with no risk of counter damage";
		owner=c;
	}

}
