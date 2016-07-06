import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Panel1() {
        super(new GridLayout(1,0));
        
        String[] columnNames = {
                                "ID Number",
                                "First Name",
                                "Last Name",
                                "Section",
                              
                                };
 
        Object[][] data = {
        {"11220538", "Raymund", "Pua","S12"},
        {"11220538", "Raymund", "Pua","S12"},
        {"11220538", "Raymund", "Pua","S12"},
        {"11220538", "Raymund", "Pua","S12"}
        };
 
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);


	}

}
