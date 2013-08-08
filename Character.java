public class Character{

	private int level, exp, BASEATT, BASEDEF, BASESPD, BASEHP, BASEMAG, BASEMANA, BASERES, hp, mana, att, mag, def, spd, res; //NO PUBLIC SETTER FOR BASE STATS
	private Store[] stats = new Store[7](); // hp, mana, att, mag, def, spd,
	private int[] BASESTATS = new int[9];
	private Random gen;
	private Ability[] abl;
	private int[] expTable = {5,20,60,120,250}; //EXP REQUIRED TO LEVEL UP
	private String charName, charClass;
	
	
	//BASIC RANDOM CHARACTER CONSTRUCTOR
	public Character(String n){ 
		charName=n;
		level=1;
		gen = new Random(System.nanoTime());
		exp=0;
		BASEHP = hp = 20+gen.nextInt(11);
		BASEATT = att = 5+gen.nextInt(6);
		BASEDEF = def = 1+gen.nextInt(5);
		BASESPD = spd = 1+gen.nextInt(5);
		BASEMAG = mag = 1+gen.nextInt(8);
		BASEMANA = mana = 10+gen.nextInt(6);
		BASERES = res = 1+gen.nextInt(5);
		loadStatArrays();
		abl= Ability.default(this);  //LOADS DEFAULT ABILITIES
	}

	//ROBUST CHARACTER CONSTRUCTOR
	public Character(String n, int l, int e, int h, int mn, int a, int d, int m, int r, int s, Ability[] ab){
		charName=n;
		level=l;
		exp=e;
		BASEHP = hp = h;
		BASEATT = att = a;
		BASEDEF = def = d;
		BASESPD = spd = s;
		BASEMAG = mag = m;
		BASEMANA = mana = mn;
		BASERES = res = r;
		gen = new Random(System.nanoTime());
		abl=ab;
		loadStatArrays();
		abilityOwner();
	}
	
	private void loadStatArrays(){
		stats[0]= new Store(BASEHP);
		stats[1]= new Store(BASEMANA);
		stats[2]= new Store(BASEATT);
		stats[3]= new Store(BASEDEF);
		stats[4]= new Store(BASEMAG);
		stats[5]= new Store(BASERES);
		stats[6]= new Store(BASESPD);
		BASESTATS[0]=BASEHP;
		BASESTATS[1]=BASEMANA;
		BASESTATS[2]=BASEATT;
		BASESTATS[3]=BASEDEF;
		BASESTATS[4]=BASEMAG;
		BASESTATS[5]=BASERES;
		BASESTATS[6]=BASESPD;
		BASESTATS[7]=level;
		BASESTATS[8]=exp;
	}
	
	//END OF TURN
	public boolean age(){
		updateStats();
		for(Store s: stats)
			s.age();
		return hp>0;
	}
	
	public void updateStats(){
		stats[0].stat=hp;
		stats[1].stat=mana;
		stats[2].stat=att;
		stats[3].stat=def;
		stats[4].stat=mag;
		stats[5].stat=res;
		stats[6].stat=spd;
	}
	
	public String toString(){
		String s = charName + " the " + charClass;
		s += "\nLevel: " + level + "Exp: " + exp + "(" + getExpPercent() + "%)";
		s += "\nHP: " + hp + "/" + BASEHP + "  Mana: " + mana + "/" + BASEMANA;
		s += "\nAtt: " + att + "   Def: " + def;
		s += "\nMag: " + mag + "   Res: " + res;
		s += "\nSpeed: " + spd;
		
		
		
	}

	//SETS CHARACTER AS OWNER OF ABILITES
	public boolean abilityOwner(){
		for(Ability a: abl)
			a.setOwner(this);
	}

	//PUBLIC GETTER
	public int getAttack(){
		return stats[2];
	}

	//PUBLIC GETTER
	public int getDefense(){
		return stats[4];
	}

	//PUBLIC GETTER
	public int getSpeed(){
		return stats[5];
	}

	//PUBLIC GETTER
	public int getExperience(){
		return BASESTATS[7];
	}

	//PUBLIC GETTER FOR %exp
	public int getExpPrcnt(){
		return (int) ((exp/expTable[level-1])*10);

	//PUBLIC GETTER
	public int getHP(){
		return stats[0];
	}
	
	public int getMaxHP(){
		return BASESTATS[0];
	}

	//PUBLIC GETTER
	public int getLevel(){
		return BASESTATS[6];
	}

	//PUBLIC GETTER
	public int getMagic(){
		return stats[3];
	}

	//PUBLIC GETTER
	public int getMana(){
		retrun stats[1];
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
