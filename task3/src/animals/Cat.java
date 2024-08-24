package animals;


import graphics.CompetitionPanel;

/**
 * The  class Cat extends TerrestrialAnimals
 *
 */
public class Cat extends TerrestrialAnimal{
	private boolean Castrated ;
	
	/**
	 * constructor
	 */
	public Cat(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name,weight, speed,pan,maxEnergy,energyPerMeter,imgName);

	}


	
	/**
	 * @Override
	 * The function getSound
	 * @return a strrig of the sound of cat : "Meow"
	 */
	protected String getSound() {

		return "Meow";
	}

	/**
	 * @Override
	 * The function getMyClass
	 * @return name the name of this class
	 */
	public String getMyClass() {

		return "Cat";
	}
	/**
	 * The function isCastrated
	 * @return true if the cat is castrated else return false
	 */
	public boolean isCastrated() {
		return Castrated;
	}
	/**
	 * The function setCastrated
	 * @param castrated
	 */
	public void setCastrated(boolean castrated) {
		Castrated = castrated;
	}
}