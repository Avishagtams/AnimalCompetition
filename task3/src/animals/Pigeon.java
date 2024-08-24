package animals;
import graphics.CompetitionPanel;

/**
 * The class Pigeon extends AirAnimal 
 */
public class Pigeon extends AirAnimal {

	/**
	 * constructor
	 */
	public Pigeon(String name, double weight, int speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,pan,maxEnergy, energyPerMeter,imgName );
	}

	protected String getSound() {

		return "Arr-rar-rar-rar-raah";
	}
	/**
	 * @Override
	 * The  function getMyClass
	 * @return a string of the name class of object
	 */
	public String getMyClass() {

		return "Pigeon";
	}
	/**
	 * the function  toString
	 * @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+"\n";
	}
	

}
