package graphics;

import animals.Animal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents a dialog that allows the user to select an existing animal group or add a new group.
 * This dialog is used to manage groups of animals for a competition.
 */
public class Group extends JDialog implements ActionListener {

	private int myChoice;
	private ArrayList<Animal[]> animals;
	private String simpleDialogDesc = "Choose group from combo: ";
	private String competition;
	private JComboBox<String> comboBox;
	private JButton btnAddAnimal;
	private JDialog dialog;
	private CompetitionPanel panel;

	/**
	 * Constructs a Group dialog.
	 *
	 * @param animals     the list of animal groups
	 * @param dialog      the parent dialog
	 * @param competition the type of competition
	 * @param panel       the competition panel associated with this dialog
	 */
	public Group(ArrayList<Animal[]> animals, JDialog dialog, String competition, CompetitionPanel panel) {
		this.dialog = dialog;
		this.myChoice = -1;
		this.panel = panel;
		this.competition = competition;
		this.animals = animals;

		setLayout(new BorderLayout());
		add(createComboBoxPanel(), BorderLayout.NORTH);
		add(createButtonPanel(), BorderLayout.SOUTH);

		setSize(400, 350);
		setLocation(450, 450);
		setVisible(true);
	}

	/**
	 * Gets the JComboBox used to select an animal group.
	 *
	 * @return the JComboBox with group options
	 */
	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	/**
	 * Creates a JPanel containing the JComboBox for selecting an animal group.
	 *
	 * @return the JPanel with the JComboBox
	 */
	private JPanel createComboBoxPanel() {
		comboBox = new JComboBox<>(getComboBoxOptions());
		comboBox.setFont(new Font("David", Font.BOLD, 20));
		comboBox.setSelectedIndex(0);

		JPanel panel = new JPanel();
		JLabel label = new JLabel(simpleDialogDesc);
		label.setFont(new Font("David", Font.BOLD, 20));

		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(label);
		panel.add(comboBox);

		return panel;
	}

	/**
	 * Creates a JPanel containing the button to accept the selected group.
	 *
	 * @return the JPanel with the accept button
	 */
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 1, 5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		btnAddAnimal = new JButton("Accept");
		btnAddAnimal.setFont(new Font("David", Font.BOLD, 20));
		btnAddAnimal.addActionListener(this);
		panel.add(btnAddAnimal);

		return panel;
	}

	/**
	 * Generates options for the JComboBox based on the current list of animal groups.
	 *
	 * @return an array of options for the JComboBox
	 */
	private String[] getComboBoxOptions() {
		String[] options = new String[animals.size() + 1];
		for (int i = 0; i < animals.size(); ++i) {
			options[i] = "group num " + (i + 1);
		}
		options[animals.size()] = "Add new Group";
		return options;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddAnimal) {
			handleAddAnimalAction();
		}
	}

	/**
	 * Handles the action of adding an animal to the selected group or creating a new group.
	 */
	private void handleAddAnimalAction() {
		setVisible(false);
		myChoice = comboBox.getSelectedIndex();

		try {
			if (dialog instanceof CourierCompetition) {
				addAnimalToCompetition();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Sorry, invalid input\n");
		}
	}

	/**
	 * Gets the index of the selected group.
	 *
	 * @return the index of the selected group
	 */
	public int getMyChoice() {
		return myChoice;
	}

	/**
	 * Adds an animal to the selected group or creates a new group if necessary.
	 * If the competition is terrestrial and the selected group already contains 2 animals,
	 * a new group will be created instead.
	 */
	private void addAnimalToCompetition() {
		if (competition.equals("Terrestrial animals")) {
			if (getMyChoice() < animals.size()) {
				if (animals.get(myChoice).length >= 2) {
					JOptionPane.showMessageDialog(this, "please open new group.");
					setVisible(true);
					return;
				}
				Animal[] a = new Animal[animals.get(myChoice).length + 1];
				System.arraycopy(animals.get(myChoice), 0, a, 0, animals.get(myChoice).length);
				animals.set(myChoice, a);
			} else {
				Animal[] a = new Animal[1];
				animals.add(a);
			}
		} else {
			if (getMyChoice() < animals.size()) {
				Animal[] a = new Animal[animals.get(myChoice).length + 1];
				System.arraycopy(animals.get(myChoice), 0, a, 0, animals.get(myChoice).length);
				animals.set(myChoice, a);
			} else {
				Animal[] a = new Animal[1];
				animals.add(a);
			}
		}

		new AddAnimalDialog(animals.get(getMyChoice()), competition, panel, dialog);
	}
}
