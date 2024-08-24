package animals;


import graphics.CompetitionPanel;
import mobility.Point;

/**
 * The  abstract class AirAnimal extends Animal
 */
public abstract class AirAnimal extends Animal{
	private double wingspan ;
	
	
	/**
	 * constructor
	 */
	public AirAnimal(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,new Point(0,20),pan,maxEnergy, energyPerMeter, imgName);
		if(!setWingspan(wingspan)) {
			this.wingspan=1;
		}

	}
	/**
	 * The function setWingspan
	 * @param wingspan
	 * @return true if the wingspan chance else return false
	 */
	public boolean setWingspan(double wingspan) {
		if (this.wingspan<=0) {
			return false;
		}
		this.wingspan=wingspan;
		return true;
	}
	/**
	 * The function toString
	 * @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+"Wingspan: "+this.wingspan+"\n";
	}
	public String  getType() {
		return "Air Animal";
	}
}
