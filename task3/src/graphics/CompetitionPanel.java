package graphics;

import animals.Animal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Initialize main panel for each competition
 * CompetitionPanel extends JPanel
 */
public class CompetitionPanel extends JPanel implements ActionListener {

	private JButton btmCompetition, btmExit, btmClear, info, btmScores, btmEat;
	private int kindOfCompetition = -1;
	private BufferedImage image = null;

	final String[] options = {"Terrestrial animals", "Water animals", "Air animals"}; // competition options
	private ArrayList<ArrayList<Animal[]>> arrAnimals = null; // animal array

	/**
	 * Constructor
	 */
	public CompetitionPanel() {

		arrAnimals = new ArrayList<ArrayList<Animal[]>>(); // initialize array

		// Initialize buttons
		btmCompetition = new JButton("Competition");
		btmCompetition.addActionListener(this);

		btmClear = new JButton("Clear");
		btmClear.addActionListener(this);

		info = new JButton("Info");
		info.addActionListener(this);

		btmExit = new JButton("Exit");
		btmExit.addActionListener(this);

		// Initialize the Scores button
		btmScores = new JButton("Scores");
		btmScores.addActionListener(this);
		btmEat = new JButton("Eat");
		btmEat.addActionListener(this);

		// Create new panel for buttons and fill it
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 6));

		p1.add(btmCompetition);
		p1.add(btmClear);
		p1.add(info);
		p1.add(btmScores);
		p1.add(btmEat);
		p1.add(btmExit);

		this.setLayout(new BorderLayout());
		this.add(p1, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));


		repaint();
	}

	/**
	 * This function loads images and draws them to the panel, draws only
	 * animals that are on the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Load image
		try {
			image = ImageIO.read(new File("task3/src/Images/competitionBackground.png"));
		} catch (Exception e) {
			System.out.println("Cannot load image-line 82 in class CompetitionPanel");
		}

		// Draw image
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

		// Check if animals are on the panel or not
		if (arrAnimals != null) {
			for (ArrayList<Animal[]> i : arrAnimals) {
				for (Animal[] j : i) {
					for (Animal k : j) {
						if (k.isOnPanel() && !k.isIfClear())
							k.drawObject(g);
					}
				}
			}
		}
	}

	/**
	 * Action performed function
	 */
	public void actionPerformed(ActionEvent e) {

		// User chose to create a competition
		if (e.getSource() == btmCompetition) {
			showCompetitionDialog();
			repaint();
		}

		// User chose to clear animals
		if (e.getSource() == btmClear) {
			openClearDialog();
		}

		// User chose to exit
		if (e.getSource() == btmExit) {
			System.exit(0);
		}

		// User clicked the Info button
		if (e.getSource() == info) {
			showInfoDialog();
		}

		// User clicked the Scores button
		if (e.getSource() == btmScores) {
			showScoresDialog();
		}
		if (e.getSource() == btmEat) {
			openEatDialog();
		}
	}

	/**
	 * Show a custom dialog with radio buttons for competition selection
	 */
	private void showCompetitionDialog() {
		JRadioButton terrestrial = new JRadioButton(options[0]);
		JRadioButton water = new JRadioButton(options[1]);
		JRadioButton air = new JRadioButton(options[2]);

		// Group the radio buttons
		ButtonGroup group = new ButtonGroup();
		group.add(terrestrial);
		group.add(water);
		group.add(air);

		// Set default selection
		terrestrial.setSelected(true);

		// Create a panel to hold the radio buttons
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(new JLabel("Choose a kind of competition:"));
		panel.add(terrestrial);
		panel.add(water);
		panel.add(air);

		int result = JOptionPane.showConfirmDialog(this, panel, "Choosing a new animal competition", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			if (terrestrial.isSelected()) {
				kindOfCompetition = 0;
			} else if (water.isSelected()) {
				kindOfCompetition = 1;
			} else if (air.isSelected()) {
				kindOfCompetition = 2;
			}

			if (kindOfCompetition != -1) {
				new AddCompetitionDialog(arrAnimals, options[kindOfCompetition], this);
			}
		}
	}

	/**
	 * Open clear dialog for animals
	 */
	private void openClearDialog() {
		ArrayList<Animal> animalsOnPanel = new ArrayList<>();
		for (ArrayList<Animal[]> list : arrAnimals) {
			for (Animal[] animalArray : list) {
				for (Animal animal : animalArray) {
					if (animal.isOnPanel()) {
						animalsOnPanel.add(animal);
					}
				}
			}
		}
		if (!animalsOnPanel.isEmpty()) {
			new ClearAnimalDialog(animalsOnPanel, animalsOnPanel.size());
		} else {
			JOptionPane.showMessageDialog(this, "No animals on the panel to clear.", "Clear Animals", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Show a dialog with information about all animals on the panel
	 */
	private void showInfoDialog() {
		ArrayList<Animal> animalsOnPanel = new ArrayList<>();
		for (ArrayList<Animal[]> list : arrAnimals) {
			for (Animal[] animalArray : list) {
				for (Animal animal : animalArray) {
					if (animal.isOnPanel()) {
						animalsOnPanel.add(animal);
					}
				}
			}
		}

		String[] columnNames = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy Consumption"};
		Object[][] data = new Object[animalsOnPanel.size()][7];

		for (int i = 0; i < animalsOnPanel.size(); i++) {
			Animal animal = animalsOnPanel.get(i);
			data[i][0] = animal.getName();
			data[i][1] = animal.getType();
			data[i][2] = animal.getMyClass();
			data[i][3] = animal.getSpeed();
			data[i][4] = animal.getEnergyConsumption();
			data[i][5] = animal.getTotalDistance();
			data[i][6] = animal.getEnergyConsumption();
		}

		JTable table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);

		JTableHeader header = table.getTableHeader();
		header.setBackground(UIManager.getColor("Button.background"));
		header.setForeground(UIManager.getColor("Button.foreground"));
		header.setFont(new Font("Calibri", Font.BOLD, 14));
		header.setBorder(UIManager.getBorder("Button.border"));

		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setFont(new Font("Calibri", Font.PLAIN, 12));
				setHorizontalAlignment(CENTER);
				return c;
			}
		};
		table.setDefaultRenderer(Object.class, cellRenderer);
		JScrollPane scrollPane = new JScrollPane(table);

		JFrame infoFrame = new JFrame("Animals Information");
		infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		infoFrame.setSize(1000, 400);
		infoFrame.add(scrollPane);
		infoFrame.setLocationRelativeTo(null);
		infoFrame.setVisible(true);
	}

	/**
	 * Show a dialog with the Scores table
	 */
	private void showScoresDialog() {
		ArrayList<Animal> animalsOnPanel = new ArrayList<>();
		for (ArrayList<Animal[]> list : arrAnimals) {
			for (Animal[] animalArray : list) {
				for (Animal animal : animalArray) {
					if (animal.isOnPanel()) {
						animalsOnPanel.add(animal);
					}
				}
			}
		}

		String[] columnNames = {"Animal", "Date"};
		Object[][] data = new Object[animalsOnPanel.size()][2];

		// מציאת השורה עם הזמן המוקדם ביותר
		SimpleDateFormat dateFormat = new SimpleDateFormat("d\\M\\yy  HH:mm:ss");
		Date earliestDate = null;
		int earliestIndex = -1;

		for (int i = 0; i < animalsOnPanel.size(); i++) {
			Animal animal = animalsOnPanel.get(i);
			data[i][0] = animal.getName();
			data[i][1] = animal.getDate();

			try {
				Date animalDate = dateFormat.parse(animal.getDate());
				if (earliestDate == null || animalDate.before(earliestDate)) {
					earliestDate = animalDate;
					earliestIndex = i;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		JTable table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);

		JTableHeader header = table.getTableHeader();
		header.setBackground(UIManager.getColor("Button.background"));
		header.setForeground(UIManager.getColor("Button.foreground"));
		header.setFont(new Font("Calibri", Font.BOLD, 14));
		header.setBorder(UIManager.getBorder("Button.border"));

		int finalEarliestIndex = earliestIndex;
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setFont(new Font("Calibri", Font.PLAIN, 12));
				setHorizontalAlignment(CENTER);

				if (row == finalEarliestIndex) {
					c.setBackground(Color.YELLOW);
				} else {
					c.setBackground(table.getBackground());
				}
				return c;
			}
		};
		table.setDefaultRenderer(Object.class, cellRenderer);
		JScrollPane scrollPane = new JScrollPane(table);

		JFrame scoresFrame = new JFrame("Scores");
		scoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		scoresFrame.setSize(600, 300);
		scoresFrame.add(scrollPane);
		scoresFrame.setLocationRelativeTo(null);
		scoresFrame.setVisible(true);
	}

	private void openEatDialog() {
		ArrayList<Animal> animalsOnPanel = new ArrayList<>();
		for (ArrayList<Animal[]> list : arrAnimals) {
			for (Animal[] animalArray : list) {
				for (Animal animal : animalArray) {
					if (animal.isOnPanel()) {
						animalsOnPanel.add(animal);
					}
				}
			}
		}
		if (!animalsOnPanel.isEmpty()) {
			new EatAnimal(animalsOnPanel, animalsOnPanel.size());
		} else {
			JOptionPane.showMessageDialog(this, "No animals on the panel to eat.", "Eat Animals", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}


