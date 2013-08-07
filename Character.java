                                                                     
                                                                     
                                                                     
                                             
public class Character{

	private int BASEATT, BASEDEF, BASESPD, BASEHP, BASEMAG, BASEMANA; //NO PUBLIC SETTER FOR BASE STATS
	private store hp, level, exp, att, def, spd, mag, mp
	private String name;	
	private Random gen;
	private Ability[] abl;
	private int[] expTable = {5,20,60,120,250}; //EXP REQUIRED TO LEVEL UP

	//BASIC RANDOM CHARACTER CONSTRUCTOR
	public Character(String n){ 
		name=n;
		level=1;
		gen = new Random(System.nanoTime());
		exp=0;
		BASEHP = hp = 20+gen.nextInt(11);
		BASEATT = att = 5+gen.nextInt(6);
		BASEDEF = def = 1+gen.nextInt(5);
		BASESPD = spd = 1+gen.nextInt(5);
		BASEMAG = mag = 1+gen.nextInt(8);
		BASEMANA = mana = 10+gen.nextInt(6)
		abl= Ability.default(this);  //LOADS DEFAULT ABILITIES
	}

	//ROBUST CHARACTER CONSTRUCTOR
	public Character(String n, int l, int e, int h, int a, int d, int s, int m, int mn Ability[] ab){
		name=n;
		level=l;
		exp=e;
		BASEHP = hp = h;
		BASEATT = att = a;
		BASEDEF = def = d;
		BASESPD = spd = s;
		BASEMAG = mag = m;
		BASEMANA = mana = mn
		gen = new Random(System.nanoTime());
		abl=ab;
		abilityOwner();
	}
	
	//USE FIRST ABILITY
	public boolean useAbility1(Character target){ 
		return abl[0].activate(target);		
	}
	
	//USE SECOND ABILITY
	public boolean useAbility2(Character target){
		return abl[1].activate(target);		
	}

	//USE THIRD ABILITY
	public boolean useAbility3(Character target){
		return abl[2].activate(target);		
	}

	//USE FOURTH ABILITY
	public boolean useAbility4(Character target){
		return abl[3].activate(target);		
	}

	//SETS CHARACTER AS OWNER OF ABILITES
	public boolean abilityOwner(){
		for(Ability a: abl)
			a.setOwner(this);
	}

	//PUBLIC GETTER
	public int getAttack(){
		return att;
	}

	//PUBLIC GETTER
	public int getDefense(){
		return def;
	}

	//PUBLIC GETTER
	public int getSpeed(){
		return spd;
	}

	//PUBLIC GETTER
	public int getExperience(){
		return exp;
	}

	//PUBLIC GETTER FOR %exp
	public int getExpPrcnt(){
		return (int) exp/expTable[level-1];

	//PUBLIC GETTER
	public int getHP(){
		return hp;
	}

	//PUBLIC GETTER
	public int getLevel(){
		return level;
	}

	//PUBLIC GETTER
	public int getMagic(){
		return mag;
	}

	//PUBLIC GETTER
	public int getMana(){
		retrun mana;
	}

	//PUBLIC GETTER FOR BASE STAT
	public int getBASEMAG(){
		return BASEMAG;
	}

	//PUBLIC GETTER FOR BASE STAT
	public int getBASEMANA(){
		return BASEMANA;
	}

	//PUBLIC GETTER FOR BASE STAT
	public int getBASEHP(){
		return BASEHP;
	}

	//PUBLIC GETTER FOR BASE STAT
	public int getBASEATT(){
		return BASEATT;
	}

	//PUBLIC GETTER FOR BASE STAT
	public int getBASEDEF(){
		return BASEDEF;	
	}

	//PUBLIC GETTER FOR BASE STAT
	public int getBASESPD(){
		return BASESPD;
	}

	//PUBLIC MODIFIER
	public int modHP(int x){
		hp+=x;
		if(hp>BASEHP)
			hp=BASEHP;
		if(hp<0)
			hp=0;
		return hp;
	}
	
	//PUBLIC SETTER
	public int setMagic(int x){
		mag=x;
		if(mag<0)
			mag=0;
		return mag;
	}

	//PUBLIC MODIFIER
	public int modMana(int x){
		mana+=x;
		if(mana>BASEMANA)
			mana=BASEMANA;
		if(mana<0)
			mana=0;
		return mana;
	}

	//PUBLIC SETTER, WILL SCALE CHARACTER UP TO A CERTAIN LEVEL
	//SHOULD ONLY RETURN FALSE IF ABOVE LEVEL CAP OF CHARACTER
	public boolean setLevel(int l){
		while(l<level)
			if(!levelUp())
				return false;
		return true;
	}

	//PUBLIC SETTER
	public int setAttack(int x){
		att=x;
		if(att<0)
			att=0;
		return att;
	}
	
	//PUBLIC SETTER
	public int setDefense(int x){
		def=x;
		if(def<0)
			def=0;
		return def;
	}
	
	//PUBLIC SETTER
	public int setSpeed(int x){
		spd=x;
		if(spd<0)
			spd=0;
		return spd;
	}

	//RETURNS EXP TO NEXT LEVEL, CAPS EXP AT MAX LEVEL
	public long gainExperience(int g){
		exp+=g;
		if(level<expTable.length())
			if(exp>=expTable[level-1])
				levelUp();
		if(exp>=expTable[level-1])
			exp=expTable[level-1];
	return expTable[level-1]-exp;
	}

	//HANDLES LEVEL UP LOGIC
	//RETURNS FALSE IF MAX LEVEL
	public boolean levelUp(){
		if(level==expTable.length())
			return false;
		level+=1;	
		exp=0;
		int gain;
		gain=2+gen.nextInt(4);
			BASEHP+=gain; hp+=gain;
		gain=1+gen.nextInt(2);
			BASEATT+=gain;att+=gain;
		gain=1+gen.nextInt(2);
			BASEDEF+=gain;def+=gain;
		gain=1+gen.nextInt(2);
			BASESPD+=gain;spd+=gain;
		gain=1+gen.nextInt(4);
			BASEMAG+=gain;mag+=gain;
		gain=2+gen.nextInt(3);
			BASEMANA+=gain;mana+=gain;
		return true;
	}


}
