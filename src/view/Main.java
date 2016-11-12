package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.*;
import java.awt.CardLayout;

import javax.security.auth.login.CredentialExpiredException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Main {
	
	private JFrame frame;
	GridBagLayout Layout = new GridBagLayout();
	
	final JFileChooser fileChooser = new JFileChooser();
	FileNameExtensionFilter cFilter = new FileNameExtensionFilter(
	     "PDF (*.PDF)", "pdf");

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public Main(){
		initialize();
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel dynamicMainPanel = new JPanel();
		dynamicMainPanel.setBounds(100, 100, 800, 600);
		frame.getContentPane().add(dynamicMainPanel, BorderLayout.CENTER);
		
		dynamicMainPanel.setLayout(new CardLayout(0, 0));
		/*Panel uploadFile*/
		uploadFile uploadFilePanel;
		uploadFilePanel = new uploadFile();
		uploadFilePanel.setAlignmentX(uploadFilePanel.CENTER_ALIGNMENT);
		uploadFilePanel.setBackground(Color.WHITE);
		dynamicMainPanel.add(uploadFilePanel);
		
		
		/*Panel1*/
		StudentList studentListPanel;
		studentListPanel = new StudentList();
		dynamicMainPanel.add(studentListPanel, "name_420733528968378");
		
		/*Panel2*/
		DeliverableList submissionsPanel;
		submissionsPanel = new DeliverableList();
		dynamicMainPanel.add(submissionsPanel, "name_420733536558155");
		
		/*Panel3*/
		submitScoresPanel submitScoresPanel;
		submitScoresPanel = new submitScoresPanel();
		dynamicMainPanel.add(submitScoresPanel, "name_420733543961847");
		
		/*Add Student Panel*/
		addStudent addStudentPanel;
		addStudentPanel = new addStudent();
		dynamicMainPanel.add(addStudentPanel);
		
		studentListPanel.setVisible(false);
		submissionsPanel.setVisible(false);
		submitScoresPanel.setVisible(false);
		uploadFilePanel.setVisible(true);
		addStudentPanel.setVisible(false);
		
		Color backgroundLeftMenu = Color.decode("0xa0a0a0");
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(backgroundLeftMenu);
		frame.getContentPane().add(panelButtons, BorderLayout.WEST);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[]{89, 0};
		gbl_panelButtons.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelButtons.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		JButton uploadActivityBtn = new JButton("");
		uploadActivityBtn.setBorder(BorderFactory.createEmptyBorder());  //for design purposes
		uploadActivityBtn.setContentAreaFilled(false);
		uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));

		GridBagConstraints gbc_uploadActivityBtn = new GridBagConstraints();
		gbc_uploadActivityBtn.anchor = GridBagConstraints.WEST;
		gbc_uploadActivityBtn.fill = GridBagConstraints.VERTICAL;
		gbc_uploadActivityBtn.insets = new Insets(0, 0, 5, 0);
		gbc_uploadActivityBtn.gridx = 0;
		gbc_uploadActivityBtn.gridy = 1;
		panelButtons.add(uploadActivityBtn, gbc_uploadActivityBtn);
		
		JButton viewStudentLBtn = new JButton();
		viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
		viewStudentLBtn.setBorder(BorderFactory.createEmptyBorder());  //for design purposes
		viewStudentLBtn.setContentAreaFilled(false);

		GridBagConstraints gbc_viewStudentLBtn = new GridBagConstraints();
		gbc_viewStudentLBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewStudentLBtn.insets = new Insets(0, 0, 5, 0);
		gbc_viewStudentLBtn.gridx = 0;
		gbc_viewStudentLBtn.gridy = 2;
		panelButtons.add(viewStudentLBtn, gbc_viewStudentLBtn);
		
		JButton viewSubmissionBtn = new JButton();
		viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
		viewSubmissionBtn.setBorder(BorderFactory.createEmptyBorder());  //for design purposes
		viewSubmissionBtn.setContentAreaFilled(false);

		GridBagConstraints gbc_viewSubmissionBtn = new GridBagConstraints();
		gbc_viewSubmissionBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewSubmissionBtn.insets = new Insets(0, 0, 5, 0);
		gbc_viewSubmissionBtn.gridx = 0;
		gbc_viewSubmissionBtn.gridy = 3;
		panelButtons.add(viewSubmissionBtn, gbc_viewSubmissionBtn);
		
		JButton submitScoresBtn = new JButton();
		submitScoresBtn.setBorder(BorderFactory.createEmptyBorder());  //for design purposes
		submitScoresBtn.setContentAreaFilled(false);
		submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore.png"));
		submitScoresBtn.setVisible(false);

		GridBagConstraints gbc_submitScoresBtn = new GridBagConstraints();
		gbc_submitScoresBtn.insets = new Insets(0, 0, 5, 0);
		gbc_submitScoresBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_submitScoresBtn.gridx = 0;
		gbc_submitScoresBtn.gridy = 4;
		panelButtons.add(submitScoresBtn, gbc_submitScoresBtn);
		
		JButton addStudentButton = new JButton();
		addStudentButton.setBorder(BorderFactory.createEmptyBorder());  //for design purposes
		addStudentButton.setContentAreaFilled(false);
		addStudentButton.setIcon(new ImageIcon("resource/assets/add.png"));
		addStudentButton.setVisible(false);

		GridBagConstraints gbc_addStudentButton = new GridBagConstraints();
		gbc_addStudentButton.insets = new Insets(0, 0, 5, 0);
		gbc_addStudentButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_addStudentButton.gridx = 0;
		gbc_addStudentButton.gridy = 5;
		panelButtons.add(addStudentButton, gbc_addStudentButton);
		
		JButton testCasesButton = new JButton("");
		testCasesButton.setBorder(BorderFactory.createEmptyBorder());  //for design purposes
		testCasesButton.setContentAreaFilled(false);
		testCasesButton.setIcon(new ImageIcon("resource/assets/test.png"));
		testCasesButton.setVisible(false);
		
		
		/*ACTIONS*/
		viewStudentLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList_OC.png"));
				submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				addStudentButton.setIcon(new ImageIcon("resource/assets/add.png"));
				testCasesButton.setIcon(new ImageIcon("resource/assets/test.png"));
				uploadFilePanel.setVisible(false);
				studentListPanel.setVisible(true);
				submissionsPanel.setVisible(false);
				submitScoresPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		viewSubmissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub_OC.png"));
				addStudentButton.setIcon(new ImageIcon("resource/assets/add.png"));
				testCasesButton.setIcon(new ImageIcon("resource/assets/test.png"));
				uploadFilePanel.setVisible(false);
				submissionsPanel.setVisible(true);
				submitScoresPanel.setVisible(false);
				studentListPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		submitScoresBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore_OC.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				addStudentButton.setIcon(new ImageIcon("resource/assets/add.png"));
				testCasesButton.setIcon(new ImageIcon("resource/assets/test.png"));
				uploadFilePanel.setVisible(false);
				submitScoresPanel.setVisible(true);
				submissionsPanel.setVisible(false);
				studentListPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				addStudentButton.setIcon(new ImageIcon("resource/assets/add_OC.png"));
				testCasesButton.setIcon(new ImageIcon("resource/assets/test.png"));
				uploadFilePanel.setVisible(false);
				submissionsPanel.setVisible(false);
				submitScoresPanel.setVisible(false);
				studentListPanel.setVisible(false);
				addStudentPanel.setVisible(true);
			}
		});
		
		testCasesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				addStudentButton.setIcon(new ImageIcon("resource/assets/add.png"));
				testCasesButton.setIcon(new ImageIcon("resource/assets/test_OC.png"));
				TestCaseBuilder b = new TestCaseBuilder();
				b.showFrame();
			}
		});
		
		uploadActivityBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT_OC.png"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				submitScoresBtn.setIcon(new ImageIcon("resource/assets/sScore.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				addStudentButton.setIcon(new ImageIcon("resource/assets/add.png"));
				testCasesButton.setIcon(new ImageIcon("resource/assets/test.png"));
				uploadFilePanel.setVisible(true);
				studentListPanel.setVisible(false);
				submissionsPanel.setVisible(false);
				submitScoresPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		GridBagConstraints gbc_testCasesButton = new GridBagConstraints();
		gbc_testCasesButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_testCasesButton.gridx = 0;
		gbc_testCasesButton.gridy = 6;
		panelButtons.add(testCasesButton, gbc_testCasesButton);
		
	}
}

