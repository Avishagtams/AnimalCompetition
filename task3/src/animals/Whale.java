package animals;

import graphics.CompetitionPanel;
/**
 * The  class Whale extends WaterAnimal 
 */
public class Whale extends WaterAnimal {
	private String foodType;

	/**
	 * constructor
	 */
	public Whale(String name, double weight,int speed,	CompetitionPanel pan, int maxEnergy,int energyPerMeter,String imgName) {
		
		super(name, weight, speed,pan , maxEnergy,energyPerMeter,imgName);
	}

	/**
	 * @Override
	 *  The  function getSound
	 * @return sound of a Whale
	 */
	protected String getSound() {

		return "Splash";
	}
	/**
	 * @Override
	 * The function getMyClass
	 * @return a string of the name class of object
	 */
	public String getMyClass() {

		return "Whale" ;
	}
	/**
	 * The function getFoodType
	 * @return foodType
	 */
	public String getFoodType() {
		return foodType;
	}
	/**
	 * The function setFoodType
	 * @param foodType
	 */
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	/**
	 * the function  toString
	 * @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+" foodType: "+ foodType+"\n";
	}

}
