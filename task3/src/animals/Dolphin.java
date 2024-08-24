package animals;
import graphics.CompetitionPanel;

/**
 * The class Dolphin extends WaterAnimal
 *
 */
public class Dolphin extends WaterAnimal {
	private Water water;
	
	/**
	 * constructor
	 */

	public Dolphin(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,pan ,maxEnergy,energyPerMeter,imgName);

	}
	/**
	 * @Override
	 * The  function getSound
	 * @return a string of sound of a  Dolphin 
	 */
	protected String getSound() {
		return "Click-click";
	}
	/**
	 * @Override
	 * The function getMyClass
	 * @return a string of the name class of object
	 */
	public String getMyClass() {

		return "Dolphin";
	}
	/**
	 * The function getWater
	 * @return water
	 */
	public Water getWater() {
		return water;
	}
	/**
	 * The function setWater
	 * @param water
	 */
	public void setWater(Water water) {
		this.water = water;
	}
	/**
	 * the function  toString
	 * @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+"Water: "+water+"\n";
	}



}
