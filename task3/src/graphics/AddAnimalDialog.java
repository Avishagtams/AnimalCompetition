package graphics;

import animals.*;
import mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * Adding a specific animal according to user request.
 */
public class AddAnimalDialog extends JDialog implements ActionListener{

	final static String[] ANIMAL_KIND = {"Terrestrial animals", "Water animals", "Air animals"};
	final static String[] TERRESTRIAL_ANIMAL = {"Cat", "Dog", "Snake"};
	final static String[] WATER_ANIMAL = {"Alligator", "Dolphin", "Whale"};
	final static String[] AIR_ANIMAL = {"Eagle", "Pigeon"};

	final static String[] CAT_NAMES = {"Cat1", "Cat2"};
	final static String[] DOG_NAMES = {"Dog1", "Dog2", "Dog3", "Dog4", "Dog5", "Dog6"};
	final static String[] SNAKE_NAMES = {"Snake1", "Snake2", "Snake3"};
	final static String[] ALLIGATOR_NAMES = {"Alligator1", "Alligator2", "Alligator3"};
	final static String[] DOLPHIN_NAMES = {"Dolphin1", "Dolphin2", "Dolphin3"};
	final static String[] WHALE_NAMES = {"Whale1", "Whale2"};
	final static String[] EAGLE_NAMES = {"eagle1", "eagle2", "eagle3"};
	final static String[] PIGEON_NAMES = {"pigeon"};

	private JComboBox<String> animalChoices, animalKindChoices;
	private JLabel lb_animal, lb_pic, lblName, lblGender, lblWeight, lblSpeed, lblMaxEnergy, lblEnergyPerMeter, lblLocation;
	private JTextField txtName, txtWeight, txtSpeed, txtMaxEnergy, txtEnergyPerMeter;
	private JComboBox<Integer> cbLocationWater, cbLocationAir;
	private final static Integer[] arrLocationAir = {1, 2, 3, 4, 5};
	private final static Integer[] arrLocationWater = {1, 2, 3, 4};


	private String kindCompetition;
	private JButton btmaccept;
	private Animal[] animals;
	private CompetitionPanel pan = null;
	private JDialog g;
	String animalKind = null, animalType = null, imganimal = null;

	// Variables to store user inputs
	private String enteredName;
	private String enteredWeight;
	private String enteredSpeed;
	private String enteredMaxEnergy;
	private String enteredEnergyPerMeter;
	private Integer pathWater;
	private Integer pathAir;

	/**
	 * Add animal dialog constructor
	 */
	public AddAnimalDialog(Animal[] arr, String competition, CompetitionPanel pan, JDialog g) {
		this.g = g;
		this.pan = pan;
		this.animals = arr;
		this.kindCompetition = competition;

		setSize(500, 350);
		setLocation(450, 400);
		setSize(450, 600);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(11, 2, 0, 10)); // Increased rows to accommodate new components
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		// Initialize labels
		lb_animal = new JLabel("Choose animal: ");
		lb_pic = new JLabel("Choose a specific animal:");
		lblName = new JLabel("Name:");
		lblGender = new JLabel("Gender:");
		lblWeight = new JLabel("Weight:");
		lblSpeed = new JLabel("Speed:");
		lblMaxEnergy = new JLabel("Max energy:");
		lblEnergyPerMeter = new JLabel("Energy per meter:");

		// Initialize text fields
		txtName = new JTextField();
		txtWeight = new JTextField();
		txtSpeed = new JTextField();
		txtMaxEnergy = new JTextField();
		txtEnergyPerMeter = new JTextField();

		// Initialize combo boxes
		animalChoices = new JComboBox<>(new DefaultComboBoxModel<>());
		animalChoices.setSelectedItem(null);
		animalChoices.addActionListener(this);
		animalChoices.setEnabled(true);

		animalKindChoices = new JComboBox<>(new DefaultComboBoxModel<>());
		animalKindChoices.addActionListener(this);
		animalKindChoices.setEnabled(false);

		// Initialize optional components for water animals
		lblLocation = new JLabel("Path number:");
		cbLocationWater = new JComboBox<>(arrLocationWater);
		cbLocationAir = new JComboBox<>(arrLocationAir);

		setJcbAnimalChoices(kindCompetition);

		p.add(lb_animal);
		p.add(animalChoices);
		p.add(lb_pic);
		p.add(animalKindChoices);
		p.add(lblName);
		p.add(txtName);
		p.add(lblWeight);
		p.add(txtWeight);
		p.add(lblSpeed);
		p.add(txtSpeed);
		p.add(lblMaxEnergy);
		p.add(txtMaxEnergy);
		p.add(lblEnergyPerMeter);
		p.add(txtEnergyPerMeter);

		// Add optional components if competition is water animals
		if (kindCompetition.equals("Water animals")) {
			p.add(lblLocation);
			p.add(cbLocationWater);
		}
		if (kindCompetition.equals("Air animals")) {
			p.add(lblLocation);
			p.add(cbLocationAir);
		}

		p1.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		p1.add(p, BorderLayout.NORTH);
		add(p1);

		// Accept button to add animal
		btmaccept = new JButton("Accept");
		btmaccept.setFont(new Font("David", Font.BOLD, 20));
		btmaccept.addActionListener(this);

		JPanel p2 = new JPanel();
		p2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		p2.add(btmaccept);
		btmaccept.setVisible(false);

		p1.add(p2, BorderLayout.EAST);

		setVisible(true);
	}

	/**
	 * Set the second combo box according to each competition kind
	 */
	private void setJcbAnimalChoices(String KindAnimal) {
		animalChoices.setEnabled(true);

		DefaultComboBoxModel<String> model1 = (DefaultComboBoxModel<String>) animalChoices.getModel();
		model1.removeAllElements();

		String[] arr = null;

		switch (KindAnimal) {
			case "Terrestrial animals":
				arr = TERRESTRIAL_ANIMAL;
				break;
			case "Water animals":
				arr = WATER_ANIMAL;
				break;
			case "Air animals":
				arr = AIR_ANIMAL;
				break;
		}

		if (arr != null) {
			for (String s : arr) {
				model1.addElement(s);
			}
			animalKindChoices.setEnabled(false);
		}
	}
	/**
	 * Handles action events for the dialog. It updates the animal type selection,
	 * loads and displays an image based on the selected animal kind, and processes
	 * the input values to create an animal of the selected type.
	 *
	 * @param event the action event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == animalChoices) {
			animalType = String.valueOf(animalChoices.getSelectedItem());
			setJCombobox2(animalType);
		}

		if (event.getSource() == animalKindChoices) {
			if (animalKindChoices.isEnabled()) {
				imganimal = animalKindChoices.getItemAt(animalKindChoices.getSelectedIndex());
				Image image = null;

				try {
					image = ImageIO.read(new File(IDrawable.PICTURE_PATH + imganimal + "E" + Animal.SOFIX_IMAGES));
				} catch (IOException e) {
					System.out.println("Cannot load image-line 200 in class AddAnimalDialog");
				}

				this.getGraphics().clearRect(20, 400, 200, 100);
				this.getGraphics().drawImage(image, 20, 400, 200, 100, this);

				if (!btmaccept.isVisible()) {
					btmaccept.setVisible(true);
				}
			}
		}

		if (event.getSource() == btmaccept) {
			int speed;
			double weight;
			int maxEnergy;
			int energyPerMeter;

			// Store the values from text fields and combo boxes
			enteredName = txtName.getText();
			enteredWeight = txtWeight.getText();
			enteredSpeed = txtSpeed.getText();
			enteredMaxEnergy = txtMaxEnergy.getText();
			enteredEnergyPerMeter = txtEnergyPerMeter.getText();

			if (kindCompetition.equals("Water animals")) {
				pathWater = (Integer) cbLocationWater.getSelectedItem();
			} else if (kindCompetition.equals("Air animals")) {
				pathAir = (Integer) cbLocationAir.getSelectedItem();
			}

			try {
				speed = Integer.parseInt(txtSpeed.getText());
				weight = Double.parseDouble(txtWeight.getText());
				maxEnergy = Integer.parseInt(txtMaxEnergy.getText());
				energyPerMeter = Integer.parseInt(txtEnergyPerMeter.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Please enter valid numbers for speed, weight, max energy, and energy per meter.");
				return;
			}

			switch (kindCompetition) {
				case "Terrestrial animals":
					createAnimalT(enteredName, imganimal, weight, speed, maxEnergy, energyPerMeter);
					break;
				case "Water animals":
					createAnimalW(enteredName, imganimal, weight, speed, maxEnergy, energyPerMeter, pathWater);
					break;
				case "Air animals":
					createAnimalA(enteredName, imganimal, weight, speed, maxEnergy, energyPerMeter, pathAir);
					break;
			}

			setVisible(false);
		}
	}

	/**
	 * Creates a terrestrial animal and adds it to the panel.
	 *
	 * @param name the name of the animal
	 * @param imganimal the image name of the animal
	 * @param weight the weight of the animal
	 * @param speed the speed of the animal
	 * @param maxEnergy the maximum energy of the animal
	 * @param energyPerMeter the energy consumption per meter
	 */
	private void createAnimalT(String name, String imganimal, double weight, int speed, int maxEnergy, int energyPerMeter) {
		Animal animal = null;

		// Create animal according to its kind and set its location on panel path
		switch (animalType) {
			case "Cat":
				animal = new Cat(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(new Point(50, 30));
				break;
			case "Dog":
				animal = new Dog(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(new Point(50, 30));
				break;
			case "Snake":
				animal = new Snake(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(new Point(50, 30));
				break;
		}

		animal.setOnPanel(true);
		// Add animal to array
		animals[animals.length - 1] = animal;
		// Draw animal
		g.setVisible(true);
		if (g instanceof CourierCompetition) {
			((CourierCompetition) g).tableAnimal();
		}
		if (g instanceof RegularCompetition) {
			((RegularCompetition) g).tableAnimal();
		}
	}

	/**
	 * Creates an air animal and adds it to the panel.
	 *
	 * @param name the name of the animal
	 * @param imganimal the image name of the animal
	 * @param weight the weight of the animal
	 * @param speed the speed of the animal
	 * @param maxEnergy the maximum energy of the animal
	 * @param energyPerMeter the energy consumption per meter
	 * @param pathAir the path location for the air animal
	 */
	private void createAnimalA(String name, String imganimal, double weight, int speed, int maxEnergy, int energyPerMeter, int pathAir) {
		Point[] startLocationAir = {
				new Point(80, 20),
				new Point(80, pan.getHeight() / 5 + 54),
				new Point(80, 2 * pan.getHeight() / 5 + 54),
				new Point(80, 3 * pan.getHeight() / 5 + 65),
				new Point(80, 4 * pan.getHeight() / 5 + 65)
		};
		Animal animal = null;

		switch (animalType) {
			case "Pigeon":
				animal = new Pigeon(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(startLocationAir[pathAir - 1]);
				break;
			case "Eagle":
				animal = new Eagle(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(startLocationAir[pathAir - 1]);
				break;
		}

		animal.setOnPanel(true);
		// Update her panel path
		animal.updatePath(pathAir);
		// Add animal to array
		animals[animals.length - 1] = animal;
		// Draw animal
		g.setVisible(true);
		if (g instanceof CourierCompetition) {
			((CourierCompetition) g).tableAnimal();
		}
		if (g instanceof RegularCompetition) {
			((RegularCompetition) g).tableAnimal();
		}
	}

	/**
	 * Creates a water animal and adds it to the panel.
	 *
	 * @param name the name of the animal
	 * @param imganimal the image name of the animal
	 * @param weight the weight of the animal
	 * @param speed the speed of the animal
	 * @param maxEnergy the maximum energy of the animal
	 * @param energyPerMeter the energy consumption per meter
	 * @param pathWater the path location for the water animal
	 */
	private void createAnimalW(String name, String imganimal, double weight, int speed, int maxEnergy, int energyPerMeter, int pathWater) {
		Point[] startLocationWater = {
				new Point(100, pan.getHeight() / 5 - 54),
				new Point(100, pan.getHeight() / 5 + 130),
				new Point(100, 2 * pan.getHeight() / 5 + 135),
				new Point(100, 3 * pan.getHeight() / 5 + 150)
		};
		Animal animal = null;

		switch (animalType) {
			case "Dolphin":
				animal = new Dolphin(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(startLocationWater[pathWater - 1]);
				break;
			case "Whale":
				animal = new Whale(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(startLocationWater[pathWater - 1]);
				break;
			case "Alligator":
				animal = new Alligator(name, weight, speed, pan, maxEnergy, energyPerMeter, imganimal);
				animal.setLocation(startLocationWater[pathWater - 1]);
				break;
		}

		animal.setOnPanel(true);
		// Update her panel path
		animal.updatePath(pathWater);
		// Add animal to array
		animals[animals.length - 1] = animal;
		// Draw animal
		g.setVisible(true);
		if (g instanceof CourierCompetition) {
			((CourierCompetition) g).tableAnimal();
		}
		if (g instanceof RegularCompetition) {
			((RegularCompetition) g).tableAnimal();
		}
	}



	/**
	 * Setting the next comboBox according to animal choice
	 */
	private void setJCombobox2(String parm) {
		DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) animalKindChoices.getModel();
		model.removeAllElements();

		String[] arr = null;

		switch (parm) {
			case "Dog":
				arr = DOG_NAMES;
				break;
			case "Cat":
				arr = CAT_NAMES;
				break;
			case "Alligator":
				arr = ALLIGATOR_NAMES;
				break;
			case "Pigeon":
				arr = PIGEON_NAMES;
				break;
			case "Snake":
				arr = SNAKE_NAMES;
				break;
			case "Whale":
				arr = WHALE_NAMES;
				break;
			case "Dolphin":
				arr = DOLPHIN_NAMES;
				break;
			case "Eagle":
				arr = EAGLE_NAMES;
				break;
		}

		if (arr != null) {
			for (String s : arr) {
				model.addElement(s);
			}
			animalKindChoices.setEnabled(true);
		}
	}
}
