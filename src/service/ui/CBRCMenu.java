package service.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;

public class CBRCMenu extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CBRCMenu frame = new CBRCMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CBRCMenu() {
		setTitle("CBR-C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{162, 771, 0};
		gbl_contentPane.rowHeights = new int[]{23, 2, 23, 23, 312, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnStartNewProblem = new JButton("Start New Problem");
		GridBagConstraints gbc_btnStartNewProblem = new GridBagConstraints();
		gbc_btnStartNewProblem.anchor = GridBagConstraints.NORTH;
		gbc_btnStartNewProblem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStartNewProblem.insets = new Insets(0, 0, 5, 5);
		gbc_btnStartNewProblem.gridx = 0;
		gbc_btnStartNewProblem.gridy = 0;
		contentPane.add(btnStartNewProblem, gbc_btnStartNewProblem);
		
		JPanel pnlDetails = new JPanel();
		GridBagConstraints gbc_pnlDetails = new GridBagConstraints();
		gbc_pnlDetails.fill = GridBagConstraints.BOTH;
		gbc_pnlDetails.gridheight = 5;
		gbc_pnlDetails.gridx = 1;
		gbc_pnlDetails.gridy = 0;
		contentPane.add(pnlDetails, gbc_pnlDetails);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"Problem Title", "Problem Description", "Test Cases Input", "Test Cases Output", "First Solution File"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(0).setMinWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(320);
		table.getColumnModel().getColumn(1).setMinWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(158);
		table.getColumnModel().getColumn(3).setPreferredWidth(154);
		table.getColumnModel().getColumn(4).setPreferredWidth(103);
		pnlDetails.setLayout(new BorderLayout(0, 0));
		pnlDetails.add(table);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JButton button = new JButton("Register Student ID");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.fill = GridBagConstraints.HORIZONTAL;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 2;
		contentPane.add(button, gbc_button);
		
		JButton button_1 = new JButton("Recover Student List");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.anchor = GridBagConstraints.NORTH;
		gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 3;
		contentPane.add(button_1, gbc_button_1);
		
		JButton button_2 = new JButton("Print GDT");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.anchor = GridBagConstraints.NORTH;
		gbc_button_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_2.insets = new Insets(0, 0, 0, 5);
		gbc_button_2.gridx = 0;
		gbc_button_2.gridy = 4;
		contentPane.add(button_2, gbc_button_2);
	}

}
