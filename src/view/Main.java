package view;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import server.Server;
import service.ServerHandler;
import service.ui.CBRCInitalProblem;
import service.ui.TestCaseBuilder;

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
	private static Main m = null;
	private boolean CBRCStatus = false;
	private boolean CBRCFirst = false;
	GridBagLayout Layout = new GridBagLayout();
	
	final JFileChooser fileChooser = new JFileChooser();
	FileNameExtensionFilter cFilter = new FileNameExtensionFilter(
	     "PDF (*.PDF)", "pdf");

	public static Main getInstance(){
		if(m == null)
		{
			m = new Main();
		}
		return m;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main m = Main.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	private Main(){
	  ServerHandler sHandler = new ServerHandler();
	  sHandler.runServer();
		initialize();
	}

	private void initialize() {
		try
		{
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) 
		{
	       // handle exception
	    }
	    catch (ClassNotFoundException e) 
		{
	       // handle exception
	    }
	    catch (InstantiationException e) 
		{
	       // handle exception
	    }
	    catch (IllegalAccessException e) 
		{
	       // handle exception
	    }
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
		studentListPanel = StudentList.getInstance();
		dynamicMainPanel.add(studentListPanel, "name_420733528968378");
		
		/*Panel2*/
		DeliverableList submissionsPanel;
		submissionsPanel = DeliverableList.getInstance();
		dynamicMainPanel.add(submissionsPanel, "name_420733536558155");
		
		/*Add Student Panel*/
		addStudent addStudentPanel;
		addStudentPanel = new addStudent();
		dynamicMainPanel.add(addStudentPanel);
		
		studentListPanel.setVisible(false);
		submissionsPanel.setVisible(false);
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
		
		/*ACTIONS*/
		viewStudentLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList_OC.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				uploadFilePanel.setVisible(false);
				studentListPanel.setVisible(true);
				submissionsPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		viewSubmissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT.jpg"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub_OC.png"));
				uploadFilePanel.setVisible(false);
				submissionsPanel.setVisible(true);
				studentListPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		uploadActivityBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uploadActivityBtn.setIcon(new ImageIcon("resource/assets/uploadACT_OC.png"));
				viewStudentLBtn.setIcon(new ImageIcon("resource/assets/studentList.png"));
				viewSubmissionBtn.setIcon(new ImageIcon("resource/assets/sub.png"));
				uploadFilePanel.setVisible(true);
				studentListPanel.setVisible(false);
				submissionsPanel.setVisible(false);
				addStudentPanel.setVisible(false);
			}
		});
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		int confirmed = JOptionPane.showConfirmDialog(null, 
		        "Activate CBR-C?", "Question",
		        JOptionPane.YES_NO_OPTION);
		if(confirmed == JOptionPane.YES_OPTION)
		{
			setCBRCStatus(true);
			setCBRCFirst(true);
			new CBRCInitalProblem();
			System.out.println("CBR-C used");
		}
		else
		{
			System.out.println("CBR-C Not used");
		}
			
	}



	public boolean isCBRCFirst() {
		return CBRCFirst;
	}



	public void setCBRCFirst(boolean cBRCFirst) {
		CBRCFirst = cBRCFirst;
	}



	public boolean isCBRCStatus() {
		return CBRCStatus;
	}

	public void setCBRCStatus(boolean cBRCStatus) {
		CBRCStatus = cBRCStatus;
	}
	
}

