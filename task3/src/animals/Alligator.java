package animals;

import graphics.CompetitionPanel;
import mobility.Point;
/**
 * The class Alligator extends WaterAnimal  implements IReptile , ITerrestrialAnimal and IWaterAnimal
 */
public class Alligator extends Animal implements IReptile , ITerrestrialAnimal ,IWaterAnimal{
	//delegator
	private String AreaOfLiving;
	private WaterAnimal waterAnimal;
	private TerrestrialAnimal terrestrialAnimal;


	/**
	 * constructor
	 */
	public Alligator(String name, double weight, int speed,CompetitionPanel pan, int maxEnergy,int energyPerMeter,String imgName) {
		super(name,weight,speed,new Point(100,0),pan,maxEnergy,energyPerMeter,imgName);
		waterAnimal=new WaterAnimal(name,weight,speed,pan,maxEnergy,energyPerMeter,imgName);
		terrestrialAnimal=new TerrestrialAnimal(name,weight,speed,pan,maxEnergy,energyPerMeter,imgName);
	}



	/**
	 * @Override
	 * The function speedUp  make the reptile crawls faster , however, only it doesn't make it crawls faster than the MAX_SPEED value
	 * @param f
	 */
	public void speedUp(int f) {
		if(f>this.getSpeed()&& f<=MAX_SPEED) {
			this.setSpeed(f);
		}

	}

	/**
	 * @Override
	 * The function getSound
	 * @return a string of the sound of  Alligator "Roar"
	 */
	protected String getSound() {

		return "Roar";
	}

	/**
	 * @Override
	 * The function  getMyClass
	 * @return  a string of the name class of object
	 */
	public String getMyClass() {

		return " Alligator";
	}


	/**
	 * The function getAreaOfLiving
	 * @return AreaOfLiving
	 */
	public String getAreaOfLiving() {
		return AreaOfLiving;
	}


	/**
	 * The function setAreaOfLiving
	 * @param areaOfLiving
	 */
	public void setAreaOfLiving(String areaOfLiving) {
		AreaOfLiving = areaOfLiving;
	}
	/**
	 * The function toString
	 * @return  a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+"Area Of Living: "+this.AreaOfLiving+"Dive dept: "+getDiveDept()+"\n"+"Number legs:"+getNoLegs()+"\n";
	}
	/************************IwaterAnimal*************************************/
	/**
	 * The function  Dive � make the animal dive deeper, however, only it doesn't cause it to dive deeper than its MAX_DIVE value
	 * @param diveDept
	 * @return true if diveDept chance else return false
	 */
	public boolean Dive(double diveDept) {
		if(diveDept>waterAnimal.getDiveDept() || diveDept<WaterAnimal.MAX_DIVE ||  diveDept>0)
			return false;
		waterAnimal.setDiveDept(diveDept);
		return true;
	}
	/**
	 * The function getDiveDept
	 * @return diveDept
	 */
	public double getDiveDept() {
		return waterAnimal.getDiveDept();
	}
	/**
	 * The function setDiveDept
	 * @param diveDept
	 */
	public void setDiveDept(double diveDept) {
		waterAnimal.setDiveDept(diveDept);
	}
	/**
	 * The static function getMaxDive
	 * @return MAX_DIVE
	 */
	public int getMaxDive() {
		return waterAnimal.getMaxDive();
	}




	/************************IterrestrialAnimal*************************************/
	/**
	 * The function setNoLegs
	 * @param noLegs
	 * @return true if the noLegs chance else return false
	 */
	public boolean setNoLegs(int noLegs) {
		if(noLegs<0)
			return false;
		terrestrialAnimal.setNoLegs(noLegs);
		return true;
	}
	/**
	 * The function getNoLegs
	 * @return the value of noLegs
	 */
	public int getNoLegs() {
		return terrestrialAnimal.getNoLegs();
	}


	public String  getType() {
		return "Water and Terrestrial Animal";
	}

}
