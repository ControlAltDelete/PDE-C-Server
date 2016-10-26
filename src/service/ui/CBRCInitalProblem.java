package service.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class CBRCInitalProblem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CBRCInitalProblem frame = new CBRCInitalProblem();
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
	public CBRCInitalProblem() {
		setResizable(false);
		setTitle("CBR-C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxDebugMode = new JCheckBox("Debug Mode");
		chckbxDebugMode.setBounds(6, 7, 97, 23);
		contentPane.add(chckbxDebugMode);
		
		JLabel lblProblemName = new JLabel("Problem Name:");
		lblProblemName.setBounds(6, 37, 128, 14);
		contentPane.add(lblProblemName);
		
		JLabel lblProblemDescription = new JLabel("Problem Description:");
		lblProblemDescription.setBounds(6, 62, 128, 14);
		contentPane.add(lblProblemDescription);
		
		textField = new JTextField();
		textField.setBounds(144, 34, 240, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(144, 59, 240, 20);
		contentPane.add(textField_1);
		
		JCheckBox chckbxRecoverStudentList = new JCheckBox("Recover Student List");
		chckbxRecoverStudentList.setBounds(6, 83, 200, 23);
		contentPane.add(chckbxRecoverStudentList);
		
		JLabel lblFirstSolutionFile = new JLabel("First Solution File:");
		lblFirstSolutionFile.setBounds(6, 113, 128, 14);
		contentPane.add(lblFirstSolutionFile);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(144, 110, 141, 20);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Browse...");
		btnNewButton.setBounds(295, 109, 89, 23);
		contentPane.add(btnNewButton);
		
		JPanel panelTCI = new JPanel();
		panelTCI.setBounds(6, 138, 189, 209);
		contentPane.add(panelTCI);
		panelTCI.setLayout(null);
		
		JLabel lblTestCaseInput = new JLabel("Test Case Input");
		lblTestCaseInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestCaseInput.setBounds(10, 11, 169, 14);
		panelTCI.add(lblTestCaseInput);
		
		JList list = new JList();
		list.setBounds(10, 36, 169, 128);
		panelTCI.add(list);
		
		JButton btnTCIAdd = new JButton("+");
		btnTCIAdd.setBounds(10, 175, 48, 23);
		panelTCI.add(btnTCIAdd);
		
		JButton btnTCIDel = new JButton("-");
		btnTCIDel.setBounds(131, 175, 48, 23);
		panelTCI.add(btnTCIDel);
		
		JPanel panelTCO = new JPanel();
		panelTCO.setBounds(195, 138, 189, 209);
		contentPane.add(panelTCO);
		panelTCO.setLayout(null);
		
		JLabel lblTestCaseOutput = new JLabel("Test Case Output");
		lblTestCaseOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestCaseOutput.setBounds(10, 11, 169, 14);
		panelTCO.add(lblTestCaseOutput);
		
		JList list_1 = new JList();
		list_1.setBounds(10, 36, 169, 128);
		panelTCO.add(list_1);
		
		JButton btnTCOAdd = new JButton("-");
		btnTCOAdd.setBounds(131, 175, 48, 23);
		panelTCO.add(btnTCOAdd);
		
		JButton btnTCODel = new JButton("+");
		btnTCODel.setBounds(10, 175, 48, 23);
		panelTCO.add(btnTCODel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(295, 7, 89, 23);
		contentPane.add(btnSubmit);
	}
}
