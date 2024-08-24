package animals;
import graphics.CompetitionPanel;

/**
 * The  class Eagle extends AirAnimal
 *
 */
public class Eagle extends AirAnimal {
	private  double altitudeOfFlight;
	static final int MAX_ALTITUDE=1000;
	
	/**
	 * constructor
	 */
	public Eagle(String name, double weight, int  speed, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(name, weight, speed,pan,maxEnergy, energyPerMeter,imgName );
		if(!setAltitudeOfFlight( altitudeOfFlight)) {
			this.altitudeOfFlight=0;
		}
	}
	/**
	 * The function getAltitudeOfFlight
	 * @return altitudeOfFlight
	 */
	public double getAltitudeOfFlight() {
		return altitudeOfFlight;
	}
	/**
	 * The function setAltitudeOfFlight
	 * @param altitudeOfFlight
	 * @return true if the files altitudeOfFlight chance else return false
	 */
	public boolean setAltitudeOfFlight(double altitudeOfFlight) {
		if( altitudeOfFlight>=0 &&  altitudeOfFlight<= MAX_ALTITUDE) {
			this.altitudeOfFlight = altitudeOfFlight;
			return true;
		}
		return false;
	}
	/**
	 * @Override
	 * The function getSound
	 * @return a string of the name class of object
	 */
	protected String getSound() {
		return "Clack-wack-chack";
	}
	/**
	 * @Override
	 * The  function getMyClass
	 * @return a string of the name class of object
	 */

	public String getMyClass() {
		return "Eagle";
	}
	/**
	 * the function  toString
	 * @return a string of information of attribute on this object
	 */
	public String toString() {
		return super.toString()+" altitude of flight: "+ altitudeOfFlight+"\n";
	}
	
}
