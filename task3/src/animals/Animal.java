package animals;


import Olympics.Medal;
import graphics.CompetitionPanel;
import graphics.IAnimal;
import graphics.ICloneable;
import graphics.IDrawable;
import mobility.Mobile;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The abstract class Animal extends Mobile
 */
public abstract class Animal extends Mobile  implements IAnimal, ICloneable,IDrawable{
	private String date;
	public final static String SOFIX_IMAGES=".png";//image sofix
	public final int speedFac = 20;
	private String name;
	private  Gender gender;
	private double weight,distance;
	private int  speed,path;
	private Medal[]medals;
	private int size;
	private Orientation orientation;
	private   final int maxEnergy;
	private int energyPerMeter;
	private CompetitionPanel pan;
	private BufferedImage img1, img2, img3, img4;
	private boolean onPanel;
	private int energylevel;
	private int EnergyConsumption;
	private boolean ifClear;

	public Animal(String name, double weight, int speed, Point location, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(location);

		Date currentDate = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("d\\M\\yy  HH:mm:ss");

		date = dateFormat.format(currentDate);


		setOnPanel(false);

		ifClear=false;

		this.gender=gender;

		setMedals(medals);

		this.name=name;

		EnergyConsumption=0;

		if(!setWeight(weight)) {
			this.weight=10;
		}

		if(!setSpeed( speed)) {
			this.speed=0;
		}

		loadImages(imgName);

		orientation=Orientation.EAST;

		this.pan=pan;
		this.size=65;

		if(maxEnergy<=0)
			this.maxEnergy=10;

		else
			this.maxEnergy=maxEnergy;


		this.energyPerMeter=energyPerMeter;

		this.energylevel=maxEnergy;

		distance=0;

		path=1;

		EnergyConsumption=this.energylevel;

		pan.setEnabled(true);

	}
	/**
	 * constructor
	 */
	public Animal(String name, Gender gender, double weight, int speed, Medal[] medals, Point location, CompetitionPanel pan, int maxEnergy, int energyPerMeter, String imgName) {
		super(location);
		
		setOnPanel(false);

		this.gender=gender;

		setMedals(medals);

		this.name=name;

		EnergyConsumption=0;
		
		if(!setWeight(weight)) {
			this.weight=10;
		}
		
		if(!setSpeed( speed)) {
			this.speed=0;
		}

		loadImages(imgName);

		orientation=Orientation.EAST;
		
		this.pan=pan;
		this.size=65;

		this.maxEnergy=maxEnergy;

		this.energyPerMeter=energyPerMeter;

		this.energylevel=maxEnergy;

		distance=0;

		path=1;

		EnergyConsumption=this.energylevel;
		
		pan.setEnabled(true);

	}

	/**
	 * This func draw the animal pic to the 
	 * panel according to orientation
	 */
	public void drawObject (Graphics g)
	{
		if(orientation==Orientation.EAST)
			g.drawImage(img1, getLocation().getX(), getLocation().getY()-size/10, size*2, size,pan);

		else if(orientation==Orientation .SOUTH)
			g.drawImage(img2, getLocation().getX(), getLocation().getY()-size/10, size, size,pan);

		else if(orientation==Orientation.WEST)
			g.drawImage(img3, getLocation().getX(), getLocation().getY()-size/10, size*2, size,pan);

		else if(orientation==Orientation.NORTH)
			g.drawImage(img4, getLocation().getX()-size/2,getLocation().getY()-size/10, size,size*2, pan);
	}


	/**
	 * Increases the animal energy
	 * @return true for success
	 */
	public boolean eat(int energy) {
		if(energy<0) {
			return false;
		}

		if((this.energylevel+ energy)<=maxEnergy) {
			this.energylevel=this.energylevel+energy;
			this.EnergyConsumption=this.EnergyConsumption+energy;
		}

		else {
			this.EnergyConsumption=(this.maxEnergy-this.energylevel)+this.EnergyConsumption;
			this.energylevel=this.maxEnergy;
		}

		return true;
	}

	public boolean isIfClear() {
		return ifClear;
	}

	public void setIfClear(boolean ifClear) {
		this.ifClear = ifClear;
	}

	/**
	 * This func loads the image to the panel
	 */
	public void loadImages(String nm) {

		try {

			img1= ImageIO.read(new File(IDrawable.PICTURE_PATH + nm +"E"+ SOFIX_IMAGES));

			if(this instanceof TerrestrialAnimal) {
				img2= ImageIO.read(new File(IDrawable.PICTURE_PATH + nm +"S"+ SOFIX_IMAGES));
				img3= ImageIO.read(new File(IDrawable.PICTURE_PATH + nm +"W"+ SOFIX_IMAGES));
				img4= ImageIO.read(new File(IDrawable.PICTURE_PATH + nm +"N"+ SOFIX_IMAGES));
			}
			else {
				img2=null;
				img3=null;
				img4=null;
			}
		}
		catch (IOException e) { System.out.println("Cannot load image line 217 in class Animal"); }
	}

	/**
	 * set energy level method
	 * @param energylevel
	 */
	public void setEnergylevel(int energylevel) {
		if( energylevel>=0)
			this.energylevel = energylevel;
	}
	
	/**
	 *  get energy level method
	 * @return
	 */
	public int getMaxEnergy() {
		return maxEnergy;
	}

	/**
	 * save animal path on panel
	 * @param p
	 */
	public void updatePath(int p){
		path=p;
	}

	/**
	 * get animal path
	 * @return
	 */
	public int getPath() {
		return path;
	}

	/**
	 * update animal dis in every walk on panel
	 * @param dis
	 */
	public void updateDistance(double dis) {
		if(dis>0) {
			distance+=dis;
		}
	}
	
	/**
	 * The function  getMedals
	 * @return the array of medals
	 */
	public Medal[] getMedals() {
		return medals;

	}
	
	/**
	 * The function setMedals
	 * @param medals
	 */
	public void setMedals(Medal[] medals) {
		if(medals==null) {
			this.medals=null;
			return;
		}
		this.medals=new Medal[medals.length];
		for(int i=0;i<medals.length;++i) {
			this.medals[i]=medals[i];
		}
	}
	
	/**
	 * The function setWeight
	 * @param weight
	 * @return true if the weight chance else return false
	 */
	public boolean setWeight(double weight) {
		if(weight<=0) {
			return false;
		}
		this.weight=weight;
		return true;
	}
	
	/**
	 * The function setSpeed
	 * @param speed
	 * @return true if the speed chance else return false
	 */
	protected boolean setSpeed(int speed) {
		if(speed<0) {
			return false;
		}
		this.speed=speed;
		return true;
	}
	
	/**
	 * The function getName
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * the function getGender
	 * @return gender
	 */
	public Gender getGender() {
		return this.gender;
	}
	
	/**
	 * The function getWeight
	 * @return weight
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/**
	 * The function getSpeed
	 * @return speed
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	/**
	 * get coompetition panel
	 * @return
	 */
	public CompetitionPanel getPan() {
		return pan;
	}
	
	/**
	 * set coompetition panel
	 * @return
	 */
	public void setPan(CompetitionPanel pan) {
		this.pan = pan;
	}
	
	/**
	 * get animal orientation
	 * @return
	 */
	public Orientation getOrientation() {
		return orientation;
	}
	
	/**
	 * set animal orientation
	 * @return
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * set animal to be on panel
	 * @param onPanel
	 */
	public void setOnPanel(boolean onPanel) {
		this.onPanel = onPanel;
	}
	/**
	 * get animal type
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * set animal pic size
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * get energy per meter
	 * @return
	 */
	public int getEnergyPerMeter() {
		return energyPerMeter;
	}

	/**
	 * set animal energy per meter
	 * @param energyPerMeter
	 * @return
	 */
	public boolean setEnergyPerMeter(int energyPerMeter) {
		if(energyPerMeter>0 && energyPerMeter<=maxEnergy) {
			this.energyPerMeter = energyPerMeter;
			return true;
		}
		return false;
	}

	/**
	 * get energy consumption
	 * @return
	 */
	public int getEnergyConsumption() {
		return EnergyConsumption;
	}

	/**
	 * get energy level
	 * @return
	 */
	public int getEnergylevel() {
		return energylevel;
	}

	public abstract String  getType();//Returns animal type
	protected abstract String getSound();//Returns animal sound
	public abstract String getMyClass();//Returns animal class name
	///////////////////////////////////////////

	/**
	 * the function  toString
	 * @return a string of information of attribute on this object
	 */
	public String toString(){
		return "****"+getMyClass()+"****"+"\nName: " +this.name+"\nGender: "+this.gender+"\nWeight: "+this.weight+"\nSpeed: "+this.speed+"\n"+super.toString()+medalsString();
	}


	/**
	 * The function medalsString
	 * @return a string of the information on the array medals on this object
	 */
	private String medalsString(){
		int i;
		String str="\nThe  Medals are:\n";
		if(medals==null) {
			return "\n";
		}
		for(i=0;i<this.medals.length;++i) {
			str=str+(this.medals[i].toString());
		}
		str=str+"\n";
		return str;

	}

	/**
	 * print the sound of an animal
	 */
	public void makeSound() {
		System.out.println("Animal "+getMyClass() +" said "+getSound());
	}

	/**
	 * check if animal is in panel
	 * @return
	 */
	public boolean isOnPanel() {
		return onPanel;
	}
	
	private double rest = 0;
	
	public void MoveAnimal() {

		final Point[] endLocationWater= {new Point(938,651/5), new Point(938,1571/5),new Point(938,2*921/5+135),new Point(938,3*921/5+150)};
		final Point[] endLocationAir= {new Point(938,0),new Point(938,921/5+54),new Point(938,2*921/5+54),new Point(938,3*921/5+65),new Point(938,4*921/5+65)};
		final Point[] middleLocationTerrestrial= {new Point(50,30),new Point(1178-100,0),new Point(1178-100,4*921/5+70),new Point(50,4*921/5+70)};

		int d=getEnergylevel()/getEnergyPerMeter();

		int meter_to_time = speed/ speedFac;
		rest += ((double)speed/(double) speedFac)- meter_to_time;
		if (rest >= speedFac)
		{
			if (rest > speedFac)
			{
				meter_to_time += (int)rest/ speedFac;
				rest = rest - (int)rest/ speedFac;
			}
		}
		
		int x,y,n_x,n_y,new_x=getLocation().getX(),new_y =getLocation().getY() ;

		
			//new movement
			x=meter_to_time +getLocation().getX();//positive movement in x
			y=meter_to_time +getLocation().getY();//positive movement in y
			n_x=getLocation().getX()-meter_to_time ;//negative movement in y
			n_y=getLocation().getY()-meter_to_time ;//negative movement in x


			if(this instanceof IWaterAnimal ) {
				if(x>=endLocationWater[getPath()-1].getX()) {
					new_x=endLocationWater[getPath()-1].getX();

				}
				else {
					new_x=x;
				}
			}

			else {
				if(this instanceof ITerrestrialAnimal) {
					if(getLocation().getY()==middleLocationTerrestrial[0].getY()) {	
						if(x>=middleLocationTerrestrial[0].getX() && x<middleLocationTerrestrial[1].getX()) {
							new_x=x;
						}
						else
							if(x>=middleLocationTerrestrial[1].getX()){
								this.orientation=Orientation.SOUTH;
								new_x=middleLocationTerrestrial[1].getX();
								new_y=y;
							}	
					}

					if(getLocation().getX()==middleLocationTerrestrial[1].getX()) {	

						if(y>=middleLocationTerrestrial[1].getY() && y<middleLocationTerrestrial[2].getY()) {
							new_y=y;


						}
						else
							if(y>=middleLocationTerrestrial[2].getY()){
								this.orientation=Orientation.WEST;
								new_y=middleLocationTerrestrial[2].getY();
								new_x=n_x;
							}
					}

					if(getLocation().getY()==middleLocationTerrestrial[2].getY()) {	
						//third
						if(n_x<=middleLocationTerrestrial[2].getX() && n_x>middleLocationTerrestrial[3].getX()) {
							new_x=n_x;

						}
						else
							if(n_x<=middleLocationTerrestrial[3].getX()) {
								new_x=middleLocationTerrestrial[3].getX();
								this.orientation=Orientation.NORTH;
								new_y=n_y;
							}

					}

					if(getLocation().getX()==middleLocationTerrestrial[3].getX()) {
						if(n_y<=middleLocationTerrestrial[3].getY() && n_y>middleLocationTerrestrial[0].getY()) {
							new_y=n_y;

						}
						else 
							if(n_y<=middleLocationTerrestrial[0].getY()){
								this.orientation=Orientation.EAST;
								new_y=middleLocationTerrestrial[0].getY();
								new_x=x;

								

						}
						

					}

				}

				else {
					if(getType().equals("Air Animal")) {
						if(x>endLocationWater[getPath()-1].getX()) {
							new_x=endLocationWater[getPath()-1].getX();

						}
						else {
							new_x=x;
						}
					}
				}
			}
			
			move(new Point(new_x,new_y));
			
			getPan().repaint();
		
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
