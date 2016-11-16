package view;

import java.awt.EventQueue;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.dao.ActivityDAO;
import database.dao.DeliverableDAO;
import database.dao.StudentDAO;
import database.objects.Activity;
import database.objects.Deliverable;
import database.objects.Student;

import javax.swing.SwingConstants;

public class StudentProfile {

	private JFrame studentProfileFrame;
	private JTextField txtIDNum;
	private JTextField txtName;
	private JTextField txtSection;
	private JTable activityList;
	private JTextField txtActivitiesSubmitted;
	private JTextField txtActivitiesOverall;
	final private ActivityDAO adao = new ActivityDAO();
	final private StudentDAO sdao = new StudentDAO();
	final private DeliverableDAO ddao = new DeliverableDAO();
	final private String[] columnNames =
		{
				"Activity Name",
				"Source Code",
				"Date Submitted",
				"Grade"
		};

	/**
	 * Create the application.
	 */
	public StudentProfile(Student s) throws IOException, SQLException{
		initialize(s);
		studentProfileFrame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Student s) throws IOException, SQLException{
		studentProfileFrame = new JFrame();
		studentProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		studentProfileFrame.setResizable(false);
		studentProfileFrame.setSize(640, 360);
		studentProfileFrame.setTitle(s.getStudentID() + " - " + s.getStudentLastName() + ", " + s.getStudentFirstName());
		studentProfileFrame.getContentPane().setLayout(null);
		
		JPanel generalInfoPanel = new JPanel();
		generalInfoPanel.setBounds(0, 0, 634, 72);
		studentProfileFrame.getContentPane().add(generalInfoPanel);
		generalInfoPanel.setLayout(null);
		
		JLabel lblIDNum = new JLabel("ID Number");
		lblIDNum.setBounds(10, 11, 86, 24);
		generalInfoPanel.add(lblIDNum);
		
		txtIDNum = new JTextField();
		txtIDNum.setEditable(false);
		txtIDNum.setBounds(106, 11, 86, 24);
		txtIDNum.setText(Integer.toString(s.getStudentID()));
		generalInfoPanel.add(txtIDNum);
		txtIDNum.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(202, 11, 36, 24);
		generalInfoPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(248, 11, 240, 24);
		txtName.setText(s.getStudentLastName() + ", " + s.getStudentFirstName());
		txtName.setColumns(10);
		generalInfoPanel.add(txtName);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setBounds(498, 11, 48, 24);
		generalInfoPanel.add(lblSection);
		
		txtSection = new JTextField();
		txtSection.setEditable(false);
		txtSection.setBounds(556, 11, 48, 24);
		txtSection.setText(s.getStudentSection());
		txtSection.setColumns(10);
		generalInfoPanel.add(txtSection);
		
		JLabel lblActivities = new JLabel("Activities:");
		lblActivities.setBounds(10, 46, 64, 24);
		generalInfoPanel.add(lblActivities);
		
		txtActivitiesSubmitted = new JTextField();
		txtActivitiesSubmitted.setToolTipText("Activities Done");
		txtActivitiesSubmitted.setHorizontalAlignment(SwingConstants.RIGHT);
		txtActivitiesSubmitted.setText(Integer.toString(ddao.getDeliverableCountByStudent(s.getStudentID())));
		txtActivitiesSubmitted.setEditable(false);
		txtActivitiesSubmitted.setColumns(10);
		txtActivitiesSubmitted.setBounds(106, 46, 26, 24);
		generalInfoPanel.add(txtActivitiesSubmitted);
		
		txtActivitiesOverall = new JTextField();
		txtActivitiesOverall.setToolTipText("Total Activities");
		txtActivitiesOverall.setText(Integer.toString(adao.getActivityCount()));
		txtActivitiesOverall.setHorizontalAlignment(SwingConstants.LEFT);
		txtActivitiesOverall.setEditable(false);
		txtActivitiesOverall.setColumns(10);
		txtActivitiesOverall.setBounds(156, 46, 26, 24);
		generalInfoPanel.add(txtActivitiesOverall);
		
		JLabel lblSeparator = new JLabel("/");
		lblSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeparator.setBounds(106, 46, 76, 24);
		generalInfoPanel.add(lblSeparator);
		
		JScrollPane activityPane = new JScrollPane();
		activityPane.setBounds(10, 83, 614, 237);
		studentProfileFrame.getContentPane().add(activityPane);
		ArrayList<Boolean> lateList = new ArrayList<Boolean>();
		Object[][] data = new Object[1][4];
		try
        {
            ArrayList<Deliverable> dArray = new ArrayList<Deliverable>();
        	dArray = ddao.getDeliverablesByStudent(s.getStudentID());
        	data = new Object[dArray.size()][4];
        	for(int d = 0; d < dArray.size(); d++)
        	{
        		Deliverable del = dArray.get(d);
        		ArrayList<Object> contents = new ArrayList<Object>();
        		Activity act = adao.getActivity(del.getActivityID());
        		contents.add(act.getActivityName());
        		contents.add(del.getDeliverableSourceCodeFileName());
        		contents.add(del.getDateSubmitted());
        		float grade = del.getGrade();
        		if(grade <= -1)
        			contents.add("NGS");
        		else
        			contents.add(grade);
        		lateList.add(ddao.isLate(s.getStudentID(), act.getActivityID()));
        		data[d] = contents.toArray();
        	}
        }
        catch (SQLException sqle)
        {
        	sqle.printStackTrace();
        	data = new Object[1][4];
        }
        catch (IOException ioe)
        {
        	ioe.printStackTrace();
        	data = new Object[1][4];
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	data = new Object[1][4];
        }
		DefaultTableModel deliverables = new DefaultTableModel(data, columnNames)
        {
        	
        	@Override
        	public boolean isCellEditable(int row, int column)
        	{
        		return false;
        	}
        	
        };
		
		activityList = new JTable(deliverables);
		activityList.getColumnModel().getColumn(0).setPreferredWidth(129);
		activityList.getColumnModel().getColumn(1).setPreferredWidth(226);
		activityList.getColumnModel().getColumn(2).setPreferredWidth(188);
		activityList.getColumnModel().getColumn(3).setPreferredWidth(40);
		activityList.getTableHeader().setReorderingAllowed(false);
		activityList.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (lateList.get(row)) {
                	setBackground(new Color(208, 53, 53));
                    setForeground(Color.WHITE);
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }       
                return this;
            }   
        });
		activityPane.setViewportView(activityList);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		studentProfileFrame = new JFrame();
		studentProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		studentProfileFrame.setResizable(false);
		studentProfileFrame.setSize(640, 360);
		studentProfileFrame.setTitle("Student Profile");
		studentProfileFrame.getContentPane().setLayout(null);
		studentProfileFrame.setLocationRelativeTo(null);
		
		JPanel generalInfoPanel = new JPanel();
		generalInfoPanel.setBounds(0, 0, 634, 72);
		studentProfileFrame.getContentPane().add(generalInfoPanel);
		generalInfoPanel.setLayout(null);
		
		JLabel lblIDNum = new JLabel("ID Number");
		lblIDNum.setBounds(10, 11, 86, 24);
		generalInfoPanel.add(lblIDNum);
		
		txtIDNum = new JTextField();
		txtIDNum.setEditable(false);
		txtIDNum.setBounds(106, 11, 86, 24);
		txtIDNum.setText("10987654");
		generalInfoPanel.add(txtIDNum);
		txtIDNum.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(202, 11, 36, 24);
		generalInfoPanel.add(lblName);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(248, 11, 240, 24);
		txtName.setText("Nielson, Niels");
		txtName.setColumns(10);
		generalInfoPanel.add(txtName);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setBounds(498, 11, 48, 24);
		generalInfoPanel.add(lblSection);
		
		txtSection = new JTextField();
		txtSection.setEditable(false);
		txtSection.setBounds(556, 11, 48, 24);
		txtSection.setText("S11A");
		txtSection.setColumns(10);
		generalInfoPanel.add(txtSection);
		
		JLabel lblActivities = new JLabel("Activities:");
		lblActivities.setBounds(10, 46, 64, 24);
		generalInfoPanel.add(lblActivities);
		
		txtActivitiesSubmitted = new JTextField();
		txtActivitiesSubmitted.setToolTipText("Activities Done");
		txtActivitiesSubmitted.setHorizontalAlignment(SwingConstants.RIGHT);
		txtActivitiesSubmitted.setText("5");
		txtActivitiesSubmitted.setEditable(false);
		txtActivitiesSubmitted.setColumns(10);
		txtActivitiesSubmitted.setBounds(106, 46, 26, 24);
		generalInfoPanel.add(txtActivitiesSubmitted);
		
		txtActivitiesOverall = new JTextField();
		txtActivitiesOverall.setToolTipText("Total Activities");
		txtActivitiesOverall.setText("10");
		txtActivitiesOverall.setHorizontalAlignment(SwingConstants.LEFT);
		txtActivitiesOverall.setEditable(false);
		txtActivitiesOverall.setColumns(10);
		txtActivitiesOverall.setBounds(156, 46, 26, 24);
		generalInfoPanel.add(txtActivitiesOverall);
		
		JLabel lblSeparator = new JLabel("/");
		lblSeparator.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeparator.setBounds(106, 46, 76, 24);
		generalInfoPanel.add(lblSeparator);
		
		JScrollPane activityPane = new JScrollPane();
		activityPane.setBounds(10, 83, 614, 237);
		studentProfileFrame.getContentPane().add(activityPane);
		
		activityList = new JTable();
		activityList.getTableHeader().setReorderingAllowed(false);
		activityList.setModel(new DefaultTableModel(
			new Object[][] {
				{"Basic Functions", "Nielson_Niels_BasicFunctions.c", "1/4/2016 1:56:29 PM UTC+0800", "NGS"},
				{"Conditional Statements", "Nielson_Niels_ConditionalStatements.c", "1/11/2016 2:29:56 PM UTC+0800", "83"},
				{"Loops", "Nielson_Niels_Loops.c", "1/18/2016 1:37:48 PM UTC+0800", "91"},
				{"Functions", "Nielson_Niels_Functions.c", "1/25/2016 1:08:35 PM UTC+0800", "82"},
				{"ASCII Art I", "Nielson_Niels_ASCIIArtI.c", "2/1/2016 2:10:10 PM UTC+0800", "60"},
				{null, null, "", null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Activity Name", "Source Code", "Date Submitted", "Grade"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		activityList.getColumnModel().getColumn(0).setPreferredWidth(129);
		activityList.getColumnModel().getColumn(1).setPreferredWidth(226);
		activityList.getColumnModel().getColumn(2).setPreferredWidth(188);
		activityList.getColumnModel().getColumn(3).setPreferredWidth(40);
		activityPane.setViewportView(activityList);
		
	}
}
