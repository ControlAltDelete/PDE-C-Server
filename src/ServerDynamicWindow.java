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

public class ServerDynamicWindow {
	
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
					ServerDynamicWindow window = new ServerDynamicWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	
	public ServerDynamicWindow() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel dynamicMainPanel = new JPanel();
		dynamicMainPanel.setBounds(100, 100, 800, 600);
		frame.getContentPane().add(dynamicMainPanel, BorderLayout.CENTER);
		
		/*Panel1*/
		Panel1 p1;
		dynamicMainPanel.setLayout(new CardLayout(0, 0));
		p1 = new Panel1();
		dynamicMainPanel.add(p1, "name_420733528968378");
		
		/*Panel2*/
		Panel2 p2;
		p2 = new Panel2();
		dynamicMainPanel.add(p2, "name_420733536558155");
		
		/*Panel3*/
		submitScoresPanel p3;
		p3 = new submitScoresPanel();
		dynamicMainPanel.add(p3, "name_420733543961847");
		p1.setVisible(false);
		p2.setVisible(false);
		p3.setVisible(false);
		
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panelButtons, BorderLayout.WEST);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[]{89, 0};
		gbl_panelButtons.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0};
		gbl_panelButtons.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		JButton uploadActivityBtn = new JButton("Upload Activity");
		GridBagConstraints gbc_uploadActivityBtn = new GridBagConstraints();
		gbc_uploadActivityBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_uploadActivityBtn.insets = new Insets(0, 0, 5, 0);
		gbc_uploadActivityBtn.anchor = GridBagConstraints.NORTH;
		gbc_uploadActivityBtn.gridx = 0;
		gbc_uploadActivityBtn.gridy = 1;
		panelButtons.add(uploadActivityBtn, gbc_uploadActivityBtn);
		
		JButton viewStudentLBtn = new JButton("View Student List");
		viewStudentLBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p1.setVisible(true);
				p2.setVisible(false);
				p3.setVisible(false);
			}
		});
		GridBagConstraints gbc_viewStudentLBtn = new GridBagConstraints();
		gbc_viewStudentLBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewStudentLBtn.insets = new Insets(0, 0, 5, 0);
		gbc_viewStudentLBtn.gridx = 0;
		gbc_viewStudentLBtn.gridy = 2;
		panelButtons.add(viewStudentLBtn, gbc_viewStudentLBtn);
		
		JButton viewSubmissionBtn = new JButton("View Submissions");
		viewSubmissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p2.setVisible(true);
				p3.setVisible(false);
				p1.setVisible(false);
			}
		});
		GridBagConstraints gbc_viewSubmissionBtn = new GridBagConstraints();
		gbc_viewSubmissionBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_viewSubmissionBtn.insets = new Insets(0, 0, 5, 0);
		gbc_viewSubmissionBtn.gridx = 0;
		gbc_viewSubmissionBtn.gridy = 3;
		panelButtons.add(viewSubmissionBtn, gbc_viewSubmissionBtn);
		
		JButton submitScoresBtn = new JButton("Submit Scores");
		submitScoresBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p3.setVisible(true);
				p2.setVisible(false);
				p1.setVisible(false);
			}
		});
		GridBagConstraints gbc_submitScoresBtn = new GridBagConstraints();
		gbc_submitScoresBtn.insets = new Insets(0, 0, 5, 0);
		gbc_submitScoresBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_submitScoresBtn.gridx = 0;
		gbc_submitScoresBtn.gridy = 4;
		panelButtons.add(submitScoresBtn, gbc_submitScoresBtn);
		
	}
}

