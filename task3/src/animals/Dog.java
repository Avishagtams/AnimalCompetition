package animals;

import graphics.CompetitionPanel;

/**
 * The class Dog extends TerrestrialAnimals
 *
 */
public class Dog extends TerrestrialAnimal{
	private  String breed;

	
	/**
	 * constructor
	 */
	public Dog(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,pan,maxEnergy,energyPerMeter,imgName);
	}
	

	
	/**
	 * @Override
	 * The function getSound
	 * @return a string "Woof Woof"
	 */
	protected  String getSound() {
		return "Woof Woof";
	}
	/**
	 * @Override
	 * The function  getMyClass
	 * @return a string of the name of class
	 */
	public  String getMyClass() {
		return "Dog";
	}
	/**
	 * The function getBreed
	 * @return breed
	 */
	public String getBreed() {
		return breed;
	}
	/**
	 * The function setBreed
	 * @param breed
	 */
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	
}
