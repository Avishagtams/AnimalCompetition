package Scores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;
import java.util.Map;

/**
 * Provides a graphical user interface to display the competition scores in a table.
 */
public class ScoresDisplay {

    private Scores scores;

    /**
     * Constructs a ScoresDisplay object with the given Scores object.
     *
     * @param scores The Scores object that contains the names and finish times to be displayed.
     */
    public ScoresDisplay(Scores scores) {
        this.scores = scores;
    }

    /**
     * Creates and displays a window showing the scores in a table format.
     */
    public void showScores() {
        Map<String, Date> allScores = scores.getAll();

        // Define the columns for the table
        String[] columnNames = {"Name", "Finish Time"};

        // Convert the scores map to a table model
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Map.Entry<String, Date> entry : allScores.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        // Create a table to display the scores
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create and set up the window
        JFrame frame = new JFrame("Competition Scores");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);

        // Display the window
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
