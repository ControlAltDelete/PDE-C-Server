import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
 
        //Add the scroll pane to this panel.
        add(scrollPane);
        
        JPanel panel = new JPanel();
        scrollPane.setRowHeaderView(panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{93, 0};
        gbl_panel.rowHeights = new int[]{23, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        JButton btnNewButton = new JButton("Add");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        	}
        });
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 0;
        panel.add(btnNewButton, gbc_btnNewButton);
        
        JButton btnDelete = new JButton("Delete");
        GridBagConstraints gbc_btnDelete = new GridBagConstraints();
        gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
        gbc_btnDelete.gridx = 0;
        gbc_btnDelete.gridy = 1;
        panel.add(btnDelete, gbc_btnDelete);
        
        JButton btnEdit = new JButton("Edit");
        GridBagConstraints gbc_btnEdit = new GridBagConstraints();
        gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
        gbc_btnEdit.gridx = 0;
        gbc_btnEdit.gridy = 2;
        panel.add(btnEdit, gbc_btnEdit);


	}

}
