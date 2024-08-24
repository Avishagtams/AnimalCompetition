package animals;

public interface IWaterAnimal {
	/**
	 * The function  Dive – make the animal dive deeper, however, only it doesn't cause it to dive deeper than its MAX_DIVE value
	 * @param diveDept
	 * @return true if diveDept chance else return false
	 */
	public boolean Dive(double diveDept);
	/**
	 * The function getDiveDept
	 * @return diveDept
	 */
	public double getDiveDept();
	/**
	 * The function setDiveDept 
	 * @param diveDept
	 */
	public void setDiveDept(double diveDept);
	/**
	 * The static function getMaxDive
	 * @return MAX_DIVE
	 */
	public int getMaxDive();
}
