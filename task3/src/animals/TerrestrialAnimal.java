package animals;


import graphics.CompetitionPanel;
import mobility.Point;

/**
 * The abstract class TerrestrialAnimals extends Animal and implements ITerrestrialAnimal 
 *
 */
public  class TerrestrialAnimal extends Animal implements ITerrestrialAnimal {
	private int noLegs;//number of legs
	
	/**
	 * constructor 
	 */
	public TerrestrialAnimal(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,new Point(0,20),pan,maxEnergy, energyPerMeter, imgName);
		if(!setNoLegs(noLegs)) {
			this.noLegs=0;
		}

	}
	/**
	 * The function setNoLegs
	 * @param noLegs
	 * @return true if the noLegs chance else return false
	 */
	public boolean setNoLegs(int noLegs) {
		if(noLegs<0)
			return false;
		this.noLegs=noLegs;
		return true;

	}
	/**
	 * The function getNoLegs
	 * @return the value of noLegs
	 */
	public int getNoLegs() {
		return noLegs;
	}
	/**
	 * The function toString
	 *  @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+"Number legs:"+noLegs+"\n";
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
		return "Terrestrial Animal";
	}
}
