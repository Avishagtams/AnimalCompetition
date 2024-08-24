package animals;

import graphics.CompetitionPanel;
import mobility.Point;

/**
 * The abstract class WaterAnimal extends Animal and implements IWaterAnimal 
 */
public class WaterAnimal extends Animal implements IWaterAnimal{
	static final int  MAX_DIVE= -800 ;
	private double diveDept=0 ;
	/**
	 * The function getDiveDept
	 * @return diveDept
	 */
	public double getDiveDept() {
		return diveDept;
	}
	
	/**
	 * The function setDiveDept 
	 * @param diveDept
	 */
	public void setDiveDept(double diveDept) {
		this.diveDept = diveDept;
	}
	/**
	 * The static function getMaxDive
	 * @return MAX_DIVE
	 */
	public int getMaxDive() {
		return MAX_DIVE;
	}
	/**
	 * constructor
	 * @param name
	 * @param gender
	 * @param weight
	 * @param speed
	 * @param medals
	 * @param diveDept
	 */
	public WaterAnimal(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,new Point(50,0),pan,maxEnergy,energyPerMeter, imgName);
		
		if(!Dive(diveDept)) {
			this.diveDept=0;
		}
	}
	/**
	 * The function  Dive � make the animal dive deeper, however, only it doesn't cause it to dive deeper than its MAX_DIVE value
	 * @param diveDept
	 * @return true if diveDept chance else return false
	 */
	public boolean Dive(double diveDept) {
		if(diveDept>this.diveDept || diveDept<MAX_DIVE ||  diveDept>0)
			return false;
		this.diveDept=diveDept;
		return true;
	}
	/**
	 * The function toString
	 *  @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+"Dive dept: "+diveDept+"\n";

	}

	@Override
	protected String getSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMyClass() {
		// TODO Auto-generated method stub
		return null;
	}
	public String  getType() {
		return "Water Animal";
	}


}
