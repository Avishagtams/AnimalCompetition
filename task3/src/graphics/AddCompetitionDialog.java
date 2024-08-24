package graphics;

import animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddCompetitionDialog extends JDialog implements ActionListener {
	private static final String DESCRIPTION = "Choose a type of competition";
	private ArrayList<ArrayList<Animal[]>> animals;
	private ButtonGroup group;
	private JButton btnAccept;
	private CompetitionPanel panel;
	private String competition;

	/**
	 * Constructor
	 */
	public AddCompetitionDialog(ArrayList<ArrayList<Animal[]>> animals, String competition, CompetitionPanel panel) {
		this.animals = animals;
		this.panel = panel;
		this.competition = competition;
		this.group = new ButtonGroup();

		setupDialog();
	}

	private void setupDialog() {
		setLayout(new BorderLayout());

		JPanel choicePanel = createChoicePanel();
		add(choicePanel, BorderLayout.CENTER);

		JPanel buttonPanel = createButtonPanel();
		add(buttonPanel, BorderLayout.SOUTH);

		setSize(300, 200); // Change size to your preference
		setLocation(450, 450);
		setVisible(true);
	}

	/**
	 * Create a panel with radio buttons for competition types.
	 * @return JPanel with radio buttons.
	 */
	private JPanel createChoicePanel() {
		JRadioButton[] radioButtons = createRadioButtons();
		JPanel panel = createPane(DESCRIPTION + ":", radioButtons);
		return panel;
	}

	/**
	 * Create radio buttons for different competition types.
	 * @return Array of JRadioButton.
	 */
	private JRadioButton[] createRadioButtons() {
		String[] options = {"Courier competition", "Regular competition"};
		JRadioButton[] radioButtons = new JRadioButton[options.length];

		for (int i = 0; i < options.length; i++) {
			radioButtons[i] = new JRadioButton(options[i]);
			radioButtons[i].setActionCommand(options[i]);
			group.add(radioButtons[i]);
		}

		radioButtons[0].setSelected(true);
		return radioButtons;
	}

	/**
	 * Create a panel with a single "Accept" button.
	 * @return JPanel with the button.
	 */
	private JPanel createButtonPanel() {
		btnAccept = new JButton("Accept");
		btnAccept.setFont(new Font("David", Font.BOLD, 20));
		btnAccept.addActionListener(this);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(btnAccept, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return panel;
	}

	/**
	 * Create a panel with a description label and radio buttons.
	 * @param description The text to display.
	 * @param radioButtons Array of JRadioButton to add.
	 * @return JPanel with the description and radio buttons.
	 */
	private JPanel createPane(String description, JRadioButton[] radioButtons) {
		JPanel box = new JPanel();
		JLabel label = new JLabel(description);
		label.setFont(new Font("David", Font.BOLD, 20));

		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		box.add(label);

		for (JRadioButton radioButton : radioButtons) {
			box.add(radioButton);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.CENTER);
		return pane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAccept) {
			handleAcceptAction();
		}
	}

	private void handleAcceptAction() {
		String command = group.getSelection().getActionCommand();
		setVisible(false);

		switch (command) {
			case "Courier competition":
				new CourierCompetition(animals, competition, panel);
				break;
			case "Regular competition":
				new RegularCompetition(animals, competition, panel);
				break;
			default:
				JOptionPane.showMessageDialog(this, "Unknown competition type selected.", "Error", JOptionPane.ERROR_MESSAGE);
				break;
		}
	}
}
