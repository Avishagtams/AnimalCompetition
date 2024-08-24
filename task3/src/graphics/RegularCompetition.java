package graphics;

import Scores.RegularTournament;
import animals.Animal;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents a dialog for a regular competition where users can start the competition or add animals.
 * It also displays a table with the list of animal groups and their names.
 */
public class RegularCompetition extends JDialog implements ActionListener {
	private ArrayList<ArrayList<Animal[]>> animals;
	private volatile ArrayList<Animal[]> myAnimalCompetition = null;
	private JButton start;
	private String competition;
	private CompetitionPanel pan;
	private String[] column;
	private JButton btmAddAnimal;
	private JPanel imagePanel;
	private JPanel buttonPanel = new JPanel();

	/**
	 * Constructs a RegularCompetition dialog.
	 *
	 * @param animals     the list of lists of animal groups
	 * @param competition the type of competition (e.g., Terrestrial, Water, Air)
	 * @param pan         the competition panel associated with this dialog
	 */
	public RegularCompetition(ArrayList<ArrayList<Animal[]>> animals, String competition, CompetitionPanel pan) {
		setLayout(new BorderLayout());
		this.animals = animals;
		this.pan = pan;
		this.competition = competition;

		// Set up buttons
		start = new JButton("Start");
		start.setFont(new Font("Arial", Font.BOLD, 20));
		start.setBackground(new Color(70, 130, 180));
		start.setForeground(Color.WHITE);
		start.setFocusPainted(false);
		start.addActionListener(this);

		btmAddAnimal = new JButton("Add Animal");
		btmAddAnimal.setFont(new Font("Arial", Font.BOLD, 20));
		btmAddAnimal.setBackground(new Color(70, 130, 180));
		btmAddAnimal.setForeground(Color.WHITE);
		btmAddAnimal.setFocusPainted(false);
		btmAddAnimal.addActionListener(this);

		buttonPanel.setLayout(new GridLayout(1, 2, 10, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buttonPanel.setBackground(new Color(240, 240, 240));
		buttonPanel.add(start);
		buttonPanel.add(btmAddAnimal);

		imagePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon("task3/src/Images/regularCompitition.jpeg");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		imagePanel.setLayout(new BorderLayout());

		// Add panels to dialog
		add(imagePanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		setSize(1200, 1000);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		if (e.getSource() == btmAddAnimal) {
			if (myAnimalCompetition == null) {
				myAnimalCompetition = new ArrayList<>();
				Animal[] a = new Animal[1];
				myAnimalCompetition.add(a);
				new AddAnimalDialog(myAnimalCompetition.get(0), competition, pan, (JDialog) this);
			} else {
				Animal[] a = new Animal[1];
				myAnimalCompetition.add(a);
				new AddAnimalDialog(myAnimalCompetition.get(myAnimalCompetition.size() - 1), competition, pan, (JDialog) this);
			}
		}

		if (e.getSource() == start) {
			if (myAnimalCompetition == null) {
				JOptionPane.showMessageDialog(start, "A competition cannot be started when there are no animals in the system");
				return;
			} else {
				animals.add(myAnimalCompetition);
				long num = 0;
				boolean validInput = false;

				while (!validInput) {
					String distance = null;
					if (competition.equals("Terrestrial animals")) {
						distance = JOptionPane.showInputDialog(start, "Enter a distance of 3608 [m] for a full competition ");
					} else if (competition.equals("Water animals")) {
						distance = JOptionPane.showInputDialog(start, "Enter a distance of 1178 [m] for a full competition ");
					} else if (competition.equals("Air animals")) {
						distance = JOptionPane.showInputDialog(start, "Enter a distance of 1178 [m] for a full competition ");
					}
					try {
						num = Long.parseLong(distance);

						if (competition.equals("Terrestrial animals")) {
							int h = pan.getHeight();
							int w = pan.getWidth();
							int dMax = 2 * h + 2 * w - 590;

							if (num > 0 && num <= dMax) {
								validInput = true;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid input. Max distance for Terrestrial animals is: " + dMax + ".");
							}
						} else if (competition.equals("Water animals")) {
							int dMax = 1178;

							if (num > 0 && num <= dMax) {
								validInput = true;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid input. Max distance for Water animals is: " + dMax + ".");
							}
						} else if (competition.equals("Air animals")) {
							int dMax = 1178;

							if (num > 0 && num <= dMax) {
								validInput = true;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid input. Max distance for Air animals is: " + dMax + ".");
							}
						} else {
							if (num > 0 && num <= pan.getWidth()) {
								validInput = true;
							} else {
								JOptionPane.showMessageDialog(null, "Invalid input. Max distance is: " + pan.getWidth() + ".");
							}
						}

					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Sorry, invalid input. Please enter a valid number.");
					}
				}

				RegularTournament c = new RegularTournament(num);
				c.setup(myAnimalCompetition);
			}
		}
	}

	/**
	 * Displays a table with the list of animal groups and their names.
	 */
	public void tableAnimal() {
		imagePanel.removeAll();
		JTable jt;
		int i;
		String[][] data = new String[1][myAnimalCompetition.size()];
		column = new String[myAnimalCompetition.size()];
		for (i = 0; i < column.length; ++i) {
			column[i] = "Group " + (i + 1);
		}

		for (int j = 0; j < myAnimalCompetition.size(); ++j) {
			data[0][j] = myAnimalCompetition.get(j)[0].getName();
		}

		jt = new JTable(data, column);
		jt.setGridColor(Color.BLACK);
		jt.setFont(new Font("Arial", Font.PLAIN, 14));
		jt.setBounds(50, 50, 200, 300);

		JTableHeader tbh = jt.getTableHeader();
		tbh.setDefaultRenderer(new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setFont(new Font("Arial", Font.BOLD, 16));

				// צבעים שונים לכל עמודה (GROUP)
				Color[] colors = {
						new Color(173, 216, 230), // Light Blue
						new Color(250, 200, 200), // Light Pink
						new Color(152, 251, 152), // Light Green
						new Color(255, 228, 196), // Light Beige
						new Color(240, 230, 140)  // Light Yellow
				};

				c.setBackground(colors[column % colors.length]);
				return c;
			}
		});

		JScrollPane sp = new JScrollPane(jt);
		sp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		imagePanel.setLayout(new BorderLayout());
		imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		imagePanel.add(sp, BorderLayout.CENTER);
		imagePanel.paintAll(imagePanel.getGraphics());
	}
}
