package service.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.cbrc.CBRCEvent;
import service.cbrc.model.CBRCProblem;
import service.cbrc.model.TestCase;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.awt.event.ActionEvent;

/**
 * Shows the Test Case Builder <code>JFrame</code>.
 * 
 * @author In Yong S. Lee
 */
public class TestCaseBuilder {

	private JFrame frmTestCaseUploader;
	private JTextField tciFileTextField;
	private JTextField tcoFileTextField;


	/**
	 * Shows the <code>TestCaseBuilder</code> frame. It will show the Test Case Uploader for the Professor to use.
	 */
	public void showFrame()
	{
		frmTestCaseUploader.setVisible(true);
	}

	/**
	 * Create the frame <code>ViewSource</code>.
	 */
	public TestCaseBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTestCaseUploader = new JFrame();
		frmTestCaseUploader.setResizable(false);
		frmTestCaseUploader.setTitle("Test Case Uploader");
		frmTestCaseUploader.setBounds(100, 100, 522, 137);
		frmTestCaseUploader.getContentPane().setLayout(null);
		frmTestCaseUploader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tciFileTextField = new JTextField();
		tciFileTextField.setEditable(false);
		tciFileTextField.setBounds(170, 11, 240, 20);
		frmTestCaseUploader.getContentPane().add(tciFileTextField);
		tciFileTextField.setColumns(10);
		
		JLabel tciLabel = new JLabel("Test Case Input File Name:");
		tciLabel.setBounds(10, 14, 150, 14);
		frmTestCaseUploader.getContentPane().add(tciLabel);
		
		JButton tciBrowseButton = new JButton("Browse...");
		tciBrowseButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				String tci = new CBRCEvent().importTestCase(frmTestCaseUploader);
				if(!tci.isEmpty())
					tciFileTextField.setText(tci);
			}
		});
		tciBrowseButton.setBounds(420, 10, 88, 23);
		frmTestCaseUploader.getContentPane().add(tciBrowseButton);
		
		tcoFileTextField = new JTextField();
		tcoFileTextField.setEditable(false);
		tcoFileTextField.setColumns(10);
		tcoFileTextField.setBounds(170, 43, 240, 20);
		frmTestCaseUploader.getContentPane().add(tcoFileTextField);
		
		JLabel tcoLabel = new JLabel("Test Case Output File Name:");
		tcoLabel.setBounds(10, 46, 150, 14);
		frmTestCaseUploader.getContentPane().add(tcoLabel);
		
		JButton tcoBrowseButton = new JButton("Browse...");
		tcoBrowseButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String tco = new CBRCEvent().importTestCase(frmTestCaseUploader);
				if(!tco.isEmpty())
					tcoFileTextField.setText(tco);
			}
		});
		tcoBrowseButton.setBounds(420, 42, 88, 23);
		frmTestCaseUploader.getContentPane().add(tcoBrowseButton);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CBRCMenu c = CBRCMenu.getInstance();
				TestCase tc = new TestCase(Paths.get(tciFileTextField.getText()), Paths.get(tcoFileTextField.getText()));
				CBRCProblem prob = c.getProb();
				prob.getTc().add(tc);
				c.setProb(prob);
				frmTestCaseUploader.setVisible(false);
			}
		});
		btnUpload.setBounds(420, 76, 88, 23);
		frmTestCaseUploader.getContentPane().add(btnUpload);
	}
}
