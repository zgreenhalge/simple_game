import java.util.*;
import java.lang.Math;

public class Character{

	private int wallet, level, exp, expMod;
	private Struct hp, mana, att, mag, def, spd, res;
	private ArrayList<StatusEffect> status = new ArrayList<StatusEffect>();
	private Item[] inventory = new Item[6];
	private Random gen;
	private Ability[] abl;
	private int[] expTable = {5,15,60,120,250,600}; //EXP REQUIRED TO LEVEL UP
	private String charName, charClass;
	
	
	//BASIC RANDOM CHARACTER CONSTRUCTOR
	public Character(){ 
		charName="Unknown Hero";
		charClass="Unknown";
		level=1;
		gen = new Random(System.nanoTime());
		exp=0;
		hp = new Struct(20+gen.nextInt(11));
		att = new Struct(5+gen.nextInt(6));
		def = new Struct(1+gen.nextInt(5));
		spd = new Struct(1+gen.nextInt(5));
		mag = new Struct(1+gen.nextInt(8));
		mana = new Struct(10+gen.nextInt(6));
		res = new Struct(1+gen.nextInt(5));
		abl[0] = new Attack(this);
		abl[1] = new Defend(this);
		abl[2] = new Throw(this);
		abl[3] = new Rest(this);
		expMod=2;
	}
	
	//CONSTRUCTOR ALLOWING RANDOM NAMED CHARACTERS
	public Character(String n, String c){
		this();
		charName=n;
		charClass=c;
	}
	
	//ROBUST CHARACTER CONSTRUCTOR
	public Character(String n, String c, int w, int l, int e, int h, int mn, int a, int d, int m, int r, int s, int eg, ArrayList<StatusEffect> se, Item[] inv, Ability[] ab){
		charName=n;
		charClass=c;
		wallet = w;
		level=l;
		exp=e;
		hp = new Struct(h);
		att = new Struct(a);
		def = new Struct(d);
		spd = new Struct(s);
		mag = new Struct(m);
		mana = new Struct(mn);
		res = new Struct(r);
		gen = new Random(System.nanoTime());
		expMod = eg;
		abl=ab;
		status=se;
		inventory=inv;
		owner();
	}
	
	//END OF TURN, RESPONSIBLE FOR AGING STATS
	public boolean age(){
		int regen= (int) (hp.BASE*0.05);
		for(StatusEffect s: status)
			if(s.age()==0)
				s=null;
		if(hp.stat>0){
			for(Item i: inventory)
				if(i.affects().equals("HPregen"))
					regen = i.effect(regen);
			for(StatusEffect s: status)
				if(s.affects().equals("HPregen"))
					regen = s.effect(regen);
			hp.stat += regen;
		}
		regen = (int) (mana.BASE*0.15);
		for(Item i: inventory)
				if(i.affects().equals("MPregen"))
					regen = i.effect(regen);
		for(StatusEffect s: status)
			if(s.affects().equals("MPregen"))
				regen = s.effect(regen);
		mana.stat += regen;
		if(mana.stat>getMAXMANA())
			mana.stat=getMAXMANA();
		if(hp.stat>getMAXHP())
			hp.stat=getMAXHP();
		return hp.stat>0;
	}

	
	//CONVERTS STATS TO STRING, SHOWING STAT CHANGES
	public String toString(){
		String s = title();
		s += "\nLevel: " + level + "Exp: " + exp + "(" + getExpPercent() + "%)";
		s += "\nHP: " + hp.stat + "/" + getMAXHP() + "  Mana: " + mana.stat + "/" + getMAXMANA();
		if(getAttack()<att.BASE)
			s += "\nAtt: " + att.BASE + "(-" + (att.BASE - getAttack()) +")";
		else
			s += "\nAtt: " + att.BASE + "(+" + (getAttack() - att.BASE)+ ")";
		if(getDefense()<def.BASE)
			s += "   Def: " + def.BASE + "(-" + (def.BASE - getDefense()) + ")";
		else
			s += "   Def: " + def.BASE + "(+" + (getDefense() - def.BASE) + ")";
		if(getMagic()<mag.BASE)
			s += "\nMag: " + mag.BASE + "(-" + (mag.BASE - getMagic()) + ")";
		else
			s += "\nMag: " + mag.BASE + "(+" + (getMagic() - mag.BASE) + ")";
		if(getResistance()<res.BASE)
			s += "   Res: " + res.BASE + "(-" + (res.BASE - getResistance()) + ")";
		else
			s += "   Res: " + res.BASE + "(+" + (getResistance() - res.BASE) + ")";
		if(getSpeed()<spd.BASE)
			s += "\nSpeed: " + spd.BASE + "(-" + (spd.BASE - getSpeed()) + ")";
		else
			s += "\nSpeed: " + spd.BASE + "(+" + (getSpeed() - spd.BASE) + ")";
		return s;	
	}
	
	//RETURNS CHARACTERS TITLE
	public String title(){
		return charName + " the " + charClass;
	}
		
	//ADD STATUS EFFECTS TO CHARACTER
	public boolean addEffect(StatusEffect s){
		if(status.contains(s))		//DISALLOWS STACKING OF EFFECTS (?)
			return false;
		status.add(s);
		return true;
	}

	//SETS CHARACTER AS OWNER
	public void owner(){
		for(Ability a: abl)
			a.setOwner(this);
		for(StatusEffect s: status)
			s.setOwner(this);
		for(Item i: inventory)
			i.setOwner(this);
	}

	//PUBLIC GETTER
	public int getAttack(){
		int ret = att.stat;
		for(Item i: inventory)
			if(i.affects().equals("att"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("att"))
				ret = s.effect(ret);
		return ret;
	}

	//PUBLIC GETTER
	public int getDefense(){
		int ret = def.stat;
		for(Item i: inventory)
			if(i.affects().equals("def"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("def"))
				ret = s.effect(ret);
		return ret;
	}

	//PUBLIC GETTER
	public int getSpeed(){
		int ret = spd.stat;
		for(Item i: inventory)
			if(i.affects().equals("spd"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("spd"))
				ret = s.effect(ret);
		return ret;
	}
	
	//PUBLIC GETTER
	public int getMagic(){
		int ret = mag.stat;
		for(Item i: inventory)
			if(i.affects().equals("mag"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("mag"))
				ret = s.effect(ret);
		return ret;
	}
	
	//PUBLIC GETTER
	public int getResistance(){
		int ret = res.stat;
		for(Item i: inventory)
			if(i.affects().equals("res"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("res"))
				ret = s.effect(ret);
		return ret;
	}

	
	//PUBLIC GETTER
	public int getMAXHP(){
		int ret = hp.BASE;
		for(Item i: inventory)
			if(i.affects().equals("MAXHP"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("MAXHP"))
				ret = s.effect(ret);
		return ret;
	}

	//PUBLIC GETTER
	public int getMAXMANA(){
		int ret = mana.BASE;
		for(Item i: inventory)
			if(i.affects().equals("MAXMANA"))
				ret = i.effect(ret);
		for(StatusEffect s: status)
			if(s.affects().equals("MAXMANA"))
				ret = s.effect(ret);
		return ret;
	}
	
	//PUBLIC GETTER
	public int getLevel(){
		return level;
	}

	//PUBLIC GETTER
	public int getMana(){
		return mana.stat;
	}

	//PUBLIC GETTER
	public int getExperience(){
		return exp;
	}

	//PUBLIC GETTER FOR %exp
	public int getExpPercent(){
		return (int) ((exp/expTable[level-1])*10);
	}
	
	//GIVES EXPERIENCE REQUIRED FOR LEVEL UP
	public int getReqExp(){
		return expTable[level-1];
	}

	//PUBLIC GETTER
	public int getHP(){
		return hp.stat;
	}
	
	//PUBLIC MODIFIER
	public int modHP(int x){
		hp.stat+=x;
		if(hp.stat>getMAXHP())
			hp.stat=getMAXHP();
		if(hp.stat<0)
			hp.stat=0;
		return hp.stat;
	}
	
	//PUBLIC SETTER
	public int setMagic(int x){
		mag.stat=x;
		if(mag.stat<0)
			mag.stat=0;
		return mag.stat;
	}

	//PUBLIC MODIFIER
	public int modMana(int x){
		mana.stat+=x;
		if(mana.stat>getMAXMANA())
			mana.stat=getMAXMANA();
		if(mana.stat<0)
			mana.stat=0;
		return mana.stat;
	}
	
	public Struct getAttStruct(){
		return att;
	}
	
	public Struct getDefStruct(){
		return def;
	}
	
	public Struct getMagStruct(){
		return mag;
	}
	
	public Struct getResStruct(){
		return res;
	}
	
	public Struct getHPStruct(){
		return hp;
	}
	
	public Struct getManaStruct(){
		return mana;
	}
	
	public Struct getSpdStruct(){
		return spd;
	}

	//PUBLIC SETTER, WILL SCALE CHARACTER UP TO A CERTAIN LEVEL
	//SHOULD ONLY RETURN FALSE IF ABOVE LEVEL CAP OF CHARACTER
	public boolean setLevel(int l){
		while(l<level)
			if(!levelUp(false))
				return false;
		return true;
	}


	//RETURNS EXP TO NEXT LEVEL, CAPS EXP AT MAX LEVEL
	public long gainExperience(int g){
		exp+=g;
		if(level<expTable.length)
			if(exp>=expTable[level-1])
				levelUp(true);
		if(exp>=expTable[level-1])
			exp=expTable[level-1];
	return expTable[level-1]-exp;
	}
	
	public void setExpMod(int i){
		expMod = i;
	}
	
	public int expReward(){
		int ex = (int) (Math.log(expMod)/Math.log(2));
		return (int) (Math.pow(level, ex))+1;
	}

	//HANDLES LEVEL UP LOGIC
	//RETURNS FALSE IF MAX LEVEL
	public boolean levelUp(boolean verbose){
		if(level==expTable.length)
			return false;
		if(verbose){
			//print out stat changes
		}
		level++;	
		exp=0;
		expMod++;
		int gain;
		gain=2+gen.nextInt(4);
			hp.BASE+=gain; hp.stat+=gain;
		gain=1+gen.nextInt(2);
			att.BASE+=gain;att.stat+=gain;
		gain=1+gen.nextInt(2);
			def.BASE+=gain;def.stat+=gain;
		gain=1+gen.nextInt(2);
			spd.BASE+=gain;spd.stat+=gain;
		gain=1+gen.nextInt(4);
			mag.BASE+=gain;mag.stat+=gain;
		gain=2+gen.nextInt(3);
			mana.BASE+=gain;mana.stat+=gain;
		return true;
	}


}
