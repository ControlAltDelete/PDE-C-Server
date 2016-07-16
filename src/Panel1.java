import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Panel1 extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Panel1() {
        
        String[] columnNames = {
                                "ID Number",
                                "First Name",
                                "Last Name",
                                "Section",
                              
                                };
 
        Object[][] data = {
        {"11220538", "Raymund", "Pua","S12", new Boolean(true)},
        {"11220538", "Zaymund", "Pua","S12", new Boolean(true)},
        {"11220538", "Aaymund", "Pua","S12",  new Boolean(true)},
        {"11220538", "Raymund", "Pua","S12",  new Boolean(true)}
        };
        setLayout(new BorderLayout(0, 0));
 
        final JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        table.setAutoCreateRowSorter(true);   //enables clicking the row for sort
        table.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(1.0);
        splitPane.setOneTouchExpandable(true);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        //Add the scroll pane to this panel.
        splitPane.setTopComponent(scrollPane);
        
        JPanel buttonContainer = new JPanel();
        splitPane.setBottomComponent(buttonContainer);
        
        JButton Add = new JButton("Add");
        buttonContainer.add(Add);
        
        JButton Edit = new JButton("Edit");
        buttonContainer.add(Edit);
        
        JButton Delete = new JButton("Delete");
        buttonContainer.add(Delete);
        
        add(splitPane);
        


	}

}
