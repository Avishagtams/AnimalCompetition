package graphics;

import animals.Animal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * The {@code EatAnimalDialog} class represents a dialog that allows users
 * to select an animal from a list of animals currently displayed on a panel
 * and feed the selected animal by adding a specified amount of food to its energy.
 *
 * The class provides a graphical user interface (GUI) with radio buttons
 * for selecting an animal, and a button to confirm the feeding action.
 */
public class EatAnimal extends JDialog implements ActionListener {

    private int count; // Number of animals on the panel
    private ArrayList<Animal> animals; // Array of animals
    private JButton voteButton = null; // Button to feed the animal
    private JRadioButton[] radioButtons; // Array of radio buttons for animal selection
    private final ButtonGroup group = new ButtonGroup(); // Group for radio buttons

    /**
     * Constructs an {@code EatAnimalDialog} with a list of animals and a count of animals.
     *
     * @param animals The list of animals to be displayed for selection.
     * @param count   The number of animals currently on the panel.
     */
    public EatAnimal(ArrayList<Animal> animals, int count) {
        this.animals = animals; // Initialize the array
        this.count = count; // Set the count

        // Create the choice panel with the radio buttons
        JPanel choicePanel = createSimpleDialogBox();

        // Create the button
        voteButton = new JButton("Eat");
        voteButton.setFont(new Font("David", Font.BOLD, 20));
        voteButton.addActionListener(this);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(choicePanel, BorderLayout.WEST);
        p.add(voteButton, BorderLayout.SOUTH);
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adding the panel to the dialog
        add(p);

        // Set the size of the dialog
        this.setSize(430, 400 + this.animals.size() * 10);
        setVisible(true);
    }

    /**
     * Creates a simple dialog box with radio buttons for selecting an animal.
     *
     * @return A JPanel containing the radio buttons and a description label.
     */
    private JPanel createSimpleDialogBox() {
        new Font("David", Font.BOLD, 20);
        int numButtons = this.count;
        radioButtons = new JRadioButton[this.count];
        int i = 0;
        Iterator<Animal> it = this.animals.iterator();

        while (it.hasNext()) {
            Animal animal = it.next();
            if (animal.isOnPanel()) {
                radioButtons[i] = new JRadioButton(animal.getMyClass() + " - " + animal.getName());
                radioButtons[i].setActionCommand(String.valueOf(i));
                group.add(radioButtons[i]);
                ++i;
            }
        }

        if (numButtons > 0) {
            radioButtons[0].setSelected(true);
        }

        return createPane("Choose animal to eat:", radioButtons);
    }

    /**
     * Creates a panel with a description and a set of radio buttons.
     *
     * @param description  The description label to be displayed above the radio buttons.
     * @param radioButtons The array of radio buttons to be added to the panel.
     * @return A JPanel containing the description and radio buttons.
     */
    private JPanel createPane(String description, JRadioButton[] radioButtons) {
        JPanel box = new JPanel();
        JLabel label = new JLabel(description + "\n");
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

    /**
     * Returns the {@code ButtonGroup} containing the radio buttons for animal selection.
     *
     * @return The {@code ButtonGroup} containing the radio buttons.
     */
    public ButtonGroup getGroup() {
        return this.group;
    }

    /**
     * Handles the action event triggered when the "Eat" button is clicked.
     * The method retrieves the selected animal and prompts the user to enter the
     * amount of food to be added to the animal's energy. If valid input is provided,
     * the selected animal's energy is increased by the specified amount.
     *
     * @param e The action event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voteButton) {
            setVisible(false);
            String command = getGroup().getSelection().getActionCommand();

            String amountFood = JOptionPane.showInputDialog(voteButton, "Enter the amount of food to add to the animal:");
            Integer num;

            try {
                num = Integer.valueOf(amountFood);
                if (num <= 0) {
                    JOptionPane.showMessageDialog(null, "Sorry, invalid input\n");
                    return;
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Sorry, invalid input\n");
                return;
            }

            int m = -1;
            for (int i = 0; i < count; ++i) {
                if (command.equals(String.valueOf(i))) {
                    for (Animal animal : animals) {
                        if (animal.isOnPanel()) {
                            m++;
                        }
                        if (m == i) {
                            setVisible(false);
                            animal.eat(num);
                            animal.MoveAnimal();
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}
