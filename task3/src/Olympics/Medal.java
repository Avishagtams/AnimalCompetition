package Olympics;
/**
 * The class Medal 
 *
 */
public class Medal {

	private Type type;
	private  String tournament;//Which tournament won the medal
	private int year;//Which year he won the medal
	/**
	 * constructor
	 * @param type
	 * @param tournament
	 * @param year
	 */
	public Medal(Type type, String tournament,int year){
		this.type=type;
		this.tournament=tournament;
		if(!setYear(year)) {
			this.year=2020;
		}
	}
	/**
	 * The function setYear() sets this  year as the parameter it received
	 * @param year
	 * @return true if this year is true and else return false
	 */
	public boolean setYear(int year) {
		if(year>=1900 && year<=2020) {
			this.year=year;
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * The function  toString() return a string of the value of this object
	 */
	public String toString() {
		return "Medal:\t"+this.type+","+this.tournament+","+this.year+"\n";
	}



}
