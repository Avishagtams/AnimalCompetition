package graphics;

import Scores.CourierTournament;
import animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourierCompetition extends JDialog implements ActionListener {

	private ArrayList<ArrayList<Animal[]>> animals;
	private volatile ArrayList<Animal[]> myAnimalCompetition = null;
	private JButton start;
	private String competition;
	private CompetitionPanel pan;
	private String[] column;
	private JButton btmAddAnimal;
	private JPanel imagePanel;
	private JPanel buttonPanel = new JPanel();

	// Constructor
	public CourierCompetition(ArrayList<ArrayList<Animal[]>> animals, String competition, CompetitionPanel pan) {
		this.animals = animals;
		this.pan = pan;
		this.competition = competition;

		initializeComponents();
		configureLayout();

		setSize(1200, 1000);
		setVisible(true);
	}

	// Initialize components
	private void initializeComponents() {
		start = createButton("Start");
		btmAddAnimal = createButton("Add Animal");

		start.addActionListener(this);
		btmAddAnimal.addActionListener(this);

		buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buttonPanel.setBackground(new Color(240, 240, 240));
		buttonPanel.add(start);
		buttonPanel.add(btmAddAnimal);

		imagePanel = createImagePanel();
	}

	// Create a JButton with predefined styling
	private JButton createButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setBackground(new Color(70, 130, 180));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		return button;
	}

	// Create an image panel
	private JPanel createImagePanel() {
		return new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("task3/src/Images/courCompitition.jpeg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
	}

	// Configure the layout of the dialog
	private void configureLayout() {
		setLayout(new BorderLayout());
		imagePanel.setLayout(new BorderLayout());

		add(imagePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);

		if (e.getSource() == btmAddAnimal) {
			handleAddAnimal();
		} else if (e.getSource() == start) {
			handleStartCompetition();
		}
	}

	// Handle adding animals to the competition
	private void handleAddAnimal() {
		if (myAnimalCompetition == null) {
			initializeAnimalCompetition();
			new AddAnimalDialog(myAnimalCompetition.get(0), competition, pan, this);
		} else {
			new Group(myAnimalCompetition, this, competition, pan);
		}
	}

	// Initialize the animal competition list
	private void initializeAnimalCompetition() {
		myAnimalCompetition = new ArrayList<>();
		Animal[] a = new Animal[1];
		myAnimalCompetition.add(a);
	}

	// Handle the start of the competition
	private void handleStartCompetition() {
		if (myAnimalCompetition == null) {
			JOptionPane.showMessageDialog(start, "A competition cannot be started when there are no animals in the system.");
			return;
		}

		long distance = 0;
		boolean validInput = false;

		while (!validInput) {
			String dis = null;
			if (competition.equals("Terrestrial animals")) {
				dis = JOptionPane.showInputDialog(start, "Enter a distance of 3608 [m] for a full competition.");
			} else if (competition.equals("Water animals")) {
				dis = JOptionPane.showInputDialog(start, "Enter a distance of 1178 [m] for a full competition.");
			} else if (competition.equals("Air animals")) {
				dis = JOptionPane.showInputDialog(start, "Enter a distance of 1178 [m] for a full competition.");
			}

			try {
				long num = Long.valueOf(dis);
				distance = num;

				if (competition.equals("Terrestrial animals")) {

					if (num > 0 && num <= 3608) {
						validInput = true;
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input. Max distance for Terrestrial animals is: " + 3608 + ".");
					}
				} else if (competition.equals("Water animals") || competition.equals("Air animals")) {
					int dMax = 1178;

					if (num > 0 && num <= dMax) {
						validInput = true;
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input. Max distance for " + competition + " is: " + dMax + ".");
					}
				} else {
					if (num > 0 && num <= pan.getWidth()) {
						validInput = true;
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input. Max distance is: " + pan.getWidth() + ".");
					}
				}

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Sorry, invalid input. Please enter a valid number.");
			}
		}

		animals.add(myAnimalCompetition); // Add the animal after the distance is validated
		System.out.println(distance);
		startCourierTournament(distance);
	}

	// Show an invalid input message
	private void showInvalidInputMessage() {
		JOptionPane.showMessageDialog(null, "Sorry, invalid input. Please enter a positive number.");
	}

	// Start the CourierTournament with the specified distance
	private void startCourierTournament(long distance) {
		CourierTournament courierTournament = new CourierTournament(distance);
		courierTournament.setup(myAnimalCompetition);
	}

	// Display the table of animals in the competition
	public void tableAnimal() {
		if (myAnimalCompetition == null || myAnimalCompetition.isEmpty()) {
			return;
		}

		imagePanel.removeAll();
		updateAnimalTable();
		imagePanel.revalidate();
		imagePanel.repaint();
	}

	// Update the table with animal data
	private void updateAnimalTable() {
		int maxRows = getMaxRowsInCompetition();
		String[][] data = new String[maxRows][myAnimalCompetition.size()];
		column = new String[myAnimalCompetition.size()];

		fillAnimalTableData(data, maxRows);

		JTable animalTable = createAnimalTable(data);
		JScrollPane scrollPane = new JScrollPane(animalTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		imagePanel.add(scrollPane, BorderLayout.CENTER);
	}

	// Get the maximum number of rows in the competition table
	private int getMaxRowsInCompetition() {
		int max = 0;
		for (Animal[] group : myAnimalCompetition) {
			max = Math.max(max, group.length);
		}
		return max;
	}

	// Fill the table with animal data
	private void fillAnimalTableData(String[][] data, int maxRows) {
		for (int i = 0; i < column.length; i++) {
			column[i] = "Group " + (i + 1);
		}

		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < myAnimalCompetition.size(); j++) {
				data[i][j] = (myAnimalCompetition.get(j).length > i) ? myAnimalCompetition.get(j)[i].getName() : " ";
			}
		}
	}

	// Create a JTable for displaying animal data
	private JTable createAnimalTable(String[][] data) {
		JTable table = new JTable(data, column);
		table.setGridColor(Color.BLACK);
		table.setFont(new Font("Arial", Font.PLAIN, 14));
		table.setBounds(50, 50, 200, 300);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setDefaultRenderer(createHeaderRenderer());

		return table;
	}

	// Create a custom renderer for the table header
	private DefaultTableCellRenderer createHeaderRenderer() {
		return new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setFont(new Font("Arial", Font.BOLD, 16));
				c.setBackground(getColumnColor(column));
				return c;
			}
		};
	}

	// Get a color based on the column index
	private Color getColumnColor(int column) {
		Color[] colors = {
				new Color(173, 216, 230), // Light Blue
				new Color(250, 200, 200), // Light Pink
				new Color(152, 251, 152), // Light Green
				new Color(255, 228, 196), // Light Beige
				new Color(240, 230, 140)  // Light Yellow
		};
		return colors[column % colors.length];
	}
}
