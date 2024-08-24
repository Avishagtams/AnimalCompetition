package graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The competition frame
 */
public class CompetitionFrame extends JFrame implements ActionListener {

	private JMenuBar mb;
	private JMenu JMfile, JMhelp;
	private JMenuItem JMIexit, JMIHelp;
	private JFrame frame;

	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) {
		// Create the first competition
		new CompetitionFrame();
	}

	/**
	 * Constructor
	 */
	public CompetitionFrame() {
		// Create and initialize the main frame
		frame = new JFrame("Competition");
		frame.setSize(1200, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Disable window resizing
		frame.setResizable(false);

//		// Center the frame on the screen

		// Create the main panel
		CompetitionPanel myPanel = new CompetitionPanel();

		// Set up the menu bar
		mb = new JMenuBar();
		JMIexit = new JMenuItem("Exit");
		JMIHelp = new JMenuItem("Help");
		JMIexit.addActionListener(this);
		JMIHelp.addActionListener(this);
		JMfile = new JMenu("File");
		JMhelp = new JMenu("Help");
		JMhelp.add(JMIHelp);
		JMfile.add(JMIexit);

		mb.add(JMfile);
		mb.add(JMhelp);
		frame.setJMenuBar(mb);

		// Add the panel to the frame
		frame.add(myPanel);

		// Make the frame visible
		frame.setVisible(true);
	}

	/**
	 * Action performed method
	 */
	public void actionPerformed(ActionEvent e) {
		// User chose to exit program
		if (e.getSource() == JMIexit) {
			System.exit(0);
		}

		// User chose help
		if (e.getSource() == JMIHelp) {
			JOptionPane.showMessageDialog(JMIHelp, "Home Work 2\nGUI");
		}
	}
}
