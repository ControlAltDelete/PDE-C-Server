import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class submitScoresPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 * 
	 * 
	 */
	public submitScoresPanel() {
		String	listData[] =
		{
			"Item 1",
			"Item 2",
			"Item 3",
			"Item 4"
		};
		
		JList list = new JList(listData);
		list.setBounds(10, 10, 194, 278);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisible(true);
		setLayout(null);
		add(list);
	
		
		JLabel IDnumber = new JLabel("ID Number");
		IDnumber.setBounds(214, 11, 87, 14);
		add(IDnumber);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(214, 36, 87, 14);
		add(lblName);
		
		JLabel lblActivityNo = new JLabel("Activity No.");
		lblActivityNo.setBounds(214, 61, 87, 14);
		add(lblActivityNo);
		
		textField = new JTextField();
		textField.setBounds(311, 8, 103, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(311, 33, 103, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(311, 58, 103, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(311, 82, 103, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setBounds(214, 86, 87, 14);
		add(lblScore);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(311, 115, 103, 23);
		add(btnSubmit);


	}
}
