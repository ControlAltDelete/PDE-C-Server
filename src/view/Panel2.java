package view;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel2 extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Panel2() {
        super(new GridLayout(1,0));
        
        String[] columnNames = {"Activity No.",
                                "ID Number",
                                "Last Name",
                                "Section",
                                "Grade",
                                "Date Submitted"
                                };
 
        Object[][] data = {
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"}
        };
 
        final JTable table = new JTable(data, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);

	}
}
