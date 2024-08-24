package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dialog class to handle clearing selected animals from a list.
 * Allows the user to select an animal from a list of non-cleared animals and mark it as cleared.
 */
public class ClearAnimalDialog extends JDialog implements ActionListener {

	private int count;
	private ArrayList<Animal> animals;
	private JButton clearButton = null;
	private JRadioButton[] radioButtons;
	private ButtonGroup group = new ButtonGroup();

	/**
	 * Constructs a ClearAnimalDialog with the given list of animals and count.
	 * Initializes the components and displays the dialog.
	 *
	 * @param animals the list of animals to be displayed in the dialog
	 * @param count   the total number of animals
	 */
	public ClearAnimalDialog(ArrayList<Animal> animals, int count) {
		this.animals = animals;
		this.count = count;

		initializeComponents();
		setSize(430, 400 + getFilteredAnimals().size() * 10);
		setVisible(true);
	}

	/**
	 * Initializes the dialog components including radio buttons and the clear button.
	 */
	private void initializeComponents() {
		ArrayList<Animal> filteredAnimals = new ArrayList<>(getFilteredAnimals());
		createRadioButtons(filteredAnimals);
		clearButton = createClearButton();

		JPanel choicePanel = createChoicePanel(filteredAnimals.size());
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(choicePanel, BorderLayout.CENTER);
		p.add(clearButton, BorderLayout.SOUTH);
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		add(p);
	}

	/**
	 * Creates radio buttons for each filtered animal.
	 *
	 * @param filteredAnimals the list of filtered animals
	 */
	private void createRadioButtons(List<Animal> filteredAnimals) {
		radioButtons = new JRadioButton[filteredAnimals.size()];
		for (int i = 0; i < filteredAnimals.size(); i++) {
			Animal animal = filteredAnimals.get(i);
			radioButtons[i] = new JRadioButton(animal.getMyClass() + " - " + animal.getName());
			radioButtons[i].setActionCommand(String.valueOf(i));
			group.add(radioButtons[i]);
		}

		if (radioButtons.length > 0) {
			radioButtons[0].setSelected(true);
		}
	}

	/**
	 * Creates a panel with radio buttons for animal selection.
	 *
	 * @param numButtons the number of radio buttons to add to the panel
	 * @return a JPanel containing the radio buttons
	 */
	private JPanel createChoicePanel(int numButtons) {
		Font f = new Font("David", Font.BOLD, 20);

		JPanel box = new JPanel();
		JLabel label = new JLabel("Select an animal from the panel:");
		label.setFont(f);
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.add(label);

		for (int i = 0; i < numButtons; i++) {
			box.add(radioButtons[i]);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.CENTER);
		return pane;
	}

	/**
	 * Creates a button to clear the selected animal.
	 *
	 * @return a JButton for clearing the animal
	 */
	private JButton createClearButton() {
		JButton button = new JButton("Clear");
		button.setFont(new Font("David", Font.BOLD, 20));
		button.addActionListener(this);
		return button;
	}

	/**
	 * Handles action events for the dialog.
	 *
	 * @param e the action event to handle
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == clearButton) {
			handleClearButtonAction();
		}
	}

	/**
	 * Handles the action when the clear button is pressed.
	 * Updates the status of the selected animal and repaints the panel.
	 */
	private void handleClearButtonAction() {
		String command = group.getSelection().getActionCommand();

		for (int i = 0; i < count; ++i) {
			if (command.equals(String.valueOf(i))) {
				updateAnimalStatus(i);
				break;
			}
		}

		repaintPanel();
	}

	/**
	 * Updates the status of the selected animal to cleared.
	 *
	 * @param selectedIndex the index of the selected animal
	 */
	private void updateAnimalStatus(int selectedIndex) {
		int m = -1;
		for (Animal animal : animals) {
			if (animal.isOnPanel()) {
				m++;
			}
			if (m == selectedIndex) {
				animal.setIfClear(true);
				setVisible(false);
				break;
			}
		}
	}

	/**
	 * Repaints the panel of the first animal in the list, if it exists.
	 */
	private void repaintPanel() {
		if (!animals.isEmpty()) {
			animals.get(0).getPan().repaint();
		}
	}

	/**
	 * Filters the list of animals to include only those that are not cleared.
	 *
	 * @return a list of filtered animals
	 */
	private List<Animal> getFilteredAnimals() {
		return animals.stream()
				.filter(animal -> !animal.isIfClear())
				.collect(Collectors.toList());
	}
}
