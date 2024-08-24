package animals;

import graphics.CompetitionPanel;
/**
 * The class Snake extends TerrestrialAnimals implements IReptile 
 *
 */
public class Snake extends TerrestrialAnimal implements IReptile{

	private double length;
	private Poisonous poisonous; 

	
	/**
	 * constructor 
	 */
	public Snake(String name, double weight, int speed,CompetitionPanel pan,int maxEnergy,int energyPerMeter,String imgName) {
		
		super(name, weight, speed,pan,maxEnergy,energyPerMeter,imgName);
		if(!setLength(length)) {
			this.length=1;
		}
		setPoisonous(poisonous) ;

	}
	
	/**
	 * @Override
	 * The  function getSound
	 * @return sound of a Snake
	 */
	protected String getSound() {

		return "ssssssss";
	}

	/**
	 * @Override
	 * The  function getMyClass
	 * @return a string of the name class of object
	 */
	public String getMyClass() {

		return "Snake";
	}

	/**
	 * @Override
	 * The function speedUp make the reptile crawls faster , however, only it doesn't make it crawls faster than the MAX_SPEED value
	 */
	public void speedUp(int f) {
		if(f>this.getSpeed()&& f<=MAX_SPEED) {
			this.setSpeed(f);
		}

	}


	/**
	 * The function getLength
	 * @return length of Snake
	 */
	public double getLength() {
		return length;
	}
	/**
	 * The function setLength
	 * @param length
	 * @return true if length of Snake is chance else return false
	 */
	public boolean setLength(double length) {
		if(length>0) {
			this.length = length;
			return true;
		}
		return false;
	}
	/**
	 * The function getPoisonous
	 * @return poisonous
	 */
	public Poisonous getPoisonous() {
		return poisonous;
	}
	/**
	 * The function setPoisonous
	 * @param poisonous
	 */
	public void setPoisonous(Poisonous poisonous) {
		this.poisonous = poisonous;
	}
	/**
	 * the function  toString
	 * @return a string of information of attribute on this object
	 */
	public String toString(){
		return super.toString()+"Length: "+this.length+"\nPoisonous? "+this.poisonous;
	}
	
}
