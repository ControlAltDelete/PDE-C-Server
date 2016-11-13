package view;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import database.dao.ActivityDAO;
import database.dao.DeliverableDAO;
import database.dao.StudentDAO;
import database.objects.Activity;
import database.objects.Deliverable;
import database.objects.Student;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class DeliverableList extends JPanel {
	public static DeliverableList deliverableInstance = null;
	private DefaultTableModel deliverableModel;
	private JTable tblDeliverable;
	private JScrollPane scrollPane; 
    private ArrayList<Boolean> lateList = new ArrayList<Boolean>();
	private DeliverableDAO ddao = new DeliverableDAO();
	final private static int FILTER_ALL = 0, FILTER_STUDENT = 1, FILTER_ACTIVITY = 2;
	private static int selectedActivity = 0, selectedStudent = 0, selectedMode = FILTER_ALL;
	final private String[] columnNames =
	{
		"Activity No.",
		"ID Number",
		"Last Name",
		"First Name",
		"Section",
		"Grade",
		"Date Submitted"
	};
	
	/**
	 * Create the panel.
	 */
	
	public static DeliverableList getInstance()
	{
		if(deliverableInstance == null)
		{
			deliverableInstance = new DeliverableList();
		}
		return deliverableInstance;
	}
	
	private void initialize()
	{
        setLayout(new GridLayout(1,0));
 
        Object[][] data = {
        		{"ACTIVITY NO.",
                    "ID NUMBER",
                    "LAST NAME",
                    "FIRST NAME",
                    "SECTION",
                    "GRADE",
                    "DATE SUBMITTED"
                    },
        		
        {"1", "11220538", "Pua", "Raymund", "S12", "83", "August"},
        {"1", "11220538", "Pua", "Raymund", "S12", "83", "August"},
        {"1", "11220538", "Pua", "Raymund", "S12", "83", "August"},
        {"1", "11220538", "Pua", "Raymund", "S12", "83", "August"},
        {"1", "11220538", "Pua", "Raymund", "S12", "83", "August"}
        };
        
        lateList = new ArrayList<Boolean>();
        try
        {
            ArrayList<Deliverable> dArray = new ArrayList<Deliverable>();
        	dArray = ddao.getDeliverables();
        	data = new Object[dArray.size()][8];
        	for(int s = 0; s < dArray.size(); s++)
        	{
        		Deliverable del = dArray.get(s);
        		ArrayList<Object> contents = new ArrayList<Object>();
        		contents.add(del.getActivityID());
        		contents.add(del.getStudentID());
        		StudentDAO sdao = new StudentDAO();
        		Student stud = sdao.getStudent(Integer.parseInt(contents.get(1).toString()));
        		contents.add(stud.getStudentLastName());
        		contents.add(stud.getStudentFirstName());
        		contents.add(stud.getStudentSection());
        		contents.add(del.getGrade());
        		contents.add(del.getDateSubmitted());
        		data[s] = contents.toArray();
        		lateList.add(ddao.isLate(Integer.parseInt(contents.get(1).toString()), Integer.parseInt(contents.get(0).toString())));
        	}
        }
        catch (SQLException sqle)
        {
        	System.out.println("No connection to MySQL.");
        	data = new Object[1][8];
        }
        catch (IOException ioe)
        {
        	ioe.printStackTrace();
        	data = new Object[1][8];
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	data = new Object[1][8];
        }
 
        deliverableModel = new DefaultTableModel(data, columnNames)
        {
        	
        	@Override
        	public boolean isCellEditable(int row, int column)
        	{
        		return false;
        	}
        	
        };
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        add(splitPane);
        splitPane.setResizeWeight(1.0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        JPanel buttonContainer = new JPanel();
        tblDeliverable = new JTable(deliverableModel);
        tblDeliverable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblDeliverable.setAutoCreateRowSorter(false); 
        tblDeliverable.setRowSelectionAllowed(true);
        tblDeliverable.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        tblDeliverable.setFillsViewportHeight(true);
        tblDeliverable.getColumn(columnNames[0]).setMinWidth(32);
        tblDeliverable.getColumn(columnNames[0]).setPreferredWidth(32);
        tblDeliverable.getColumn(columnNames[1]).setMinWidth(24);
        tblDeliverable.getColumn(columnNames[1]).setPreferredWidth(24);
        tblDeliverable.getColumn(columnNames[4]).setMinWidth(16);
        tblDeliverable.getColumn(columnNames[4]).setPreferredWidth(16);	
        tblDeliverable.getColumn(columnNames[5]).setMinWidth(16);
        tblDeliverable.getColumn(columnNames[5]).setPreferredWidth(16);
        tblDeliverable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
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
        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(tblDeliverable);
        //Add the scroll pane to this panel.
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(buttonContainer);
        
        JButton btnFilterByStudent = new JButton("Filter By Student");
        btnFilterByStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try
        		{
        			String sid = (String)JOptionPane.showInputDialog(null, "Input Student ID", "");
    				if(sid == null) { /* do nothing */ }
    				else if(sid.trim().isEmpty())
    				{
    					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
    				}
    				else
    				{
						Integer studID = 0;
						try
						{
							studID = Integer.parseInt(sid);
		        			manipulateDeliverables(ddao.getDeliverablesByStudent(studID));
		        	        tblDeliverable.getColumn(columnNames[0]).setMinWidth(32);
		        	        tblDeliverable.getColumn(columnNames[0]).setPreferredWidth(32);
		        	        tblDeliverable.getColumn(columnNames[1]).setMinWidth(24);
		        	        tblDeliverable.getColumn(columnNames[1]).setPreferredWidth(24);
		        	        tblDeliverable.getColumn(columnNames[4]).setMinWidth(16);
		        	        tblDeliverable.getColumn(columnNames[4]).setPreferredWidth(16);	
		        	        tblDeliverable.getColumn(columnNames[5]).setMinWidth(16);
		        	        tblDeliverable.getColumn(columnNames[5]).setPreferredWidth(16);
		        	        selectedActivity = 0;
		        	        selectedStudent = studID;
		        	        selectedMode = FILTER_STUDENT;
						}
						catch (NumberFormatException nfe)
						{
					        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
    				}
        		}
        		catch(Exception ex)
        		{
        			ex.printStackTrace();
        		}
        	}
        });
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try
        		{
        			refreshData();
        	        tblDeliverable.getColumn(columnNames[0]).setMinWidth(32);
        	        tblDeliverable.getColumn(columnNames[0]).setPreferredWidth(32);
        	        tblDeliverable.getColumn(columnNames[1]).setMinWidth(24);
        	        tblDeliverable.getColumn(columnNames[1]).setPreferredWidth(24);
        	        tblDeliverable.getColumn(columnNames[4]).setMinWidth(16);
        	        tblDeliverable.getColumn(columnNames[4]).setPreferredWidth(16);	
        	        tblDeliverable.getColumn(columnNames[5]).setMinWidth(16);
        	        tblDeliverable.getColumn(columnNames[5]).setPreferredWidth(16);
        	        selectedActivity = 0;
        	        selectedStudent = 0;
        	        selectedMode = FILTER_ALL;
        		}
        		catch(Exception ex)
        		{
        			ex.printStackTrace();
        		}
        	}
        });
        buttonContainer.add(btnRefresh);
        buttonContainer.add(btnFilterByStudent);
        
        JButton btnFilterByActivity = new JButton("Filter By Activity");
        btnFilterByActivity.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try
        		{
        			ActivityDAO adao = new ActivityDAO();
        			ArrayList<String> actLists = adao.getActivityNames();
        			actLists.add(0, "Select Activity");
        			String actName = (String)JOptionPane.showInputDialog(null, "Please select an activity to filter", "Select Activity", JOptionPane.QUESTION_MESSAGE, null, actLists.toArray(), "Select Activity");
    				if(actName == null) { /* do nothing */ }
    				else if(actName.equals("Select Activity"))
    				{
    					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
    				}
    				else
    				{
						Integer actID = 0;
						try
						{
							actID = adao.getActivity(actName).getActivityID();
		        			manipulateDeliverables(ddao.getDeliverablesByActivity(actID));
		        	        tblDeliverable.getColumn(columnNames[0]).setMinWidth(32);
		        	        tblDeliverable.getColumn(columnNames[0]).setPreferredWidth(32);
		        	        tblDeliverable.getColumn(columnNames[1]).setMinWidth(24);
		        	        tblDeliverable.getColumn(columnNames[1]).setPreferredWidth(24);
		        	        tblDeliverable.getColumn(columnNames[4]).setMinWidth(16);
		        	        tblDeliverable.getColumn(columnNames[4]).setPreferredWidth(16);	
		        	        tblDeliverable.getColumn(columnNames[5]).setMinWidth(16);
		        	        tblDeliverable.getColumn(columnNames[5]).setPreferredWidth(16);
		        	        selectedActivity = actID;
	        	        	selectedStudent = 0;
		        	        selectedMode = FILTER_ACTIVITY;
						}
						catch (NumberFormatException nfe)
						{
					        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
    				}
        		}
        		catch(Exception ex)
        		{
        			ex.printStackTrace();
        		}
        	}
        });
        buttonContainer.add(btnFilterByActivity);
        
        JButton btnViewSourceCode = new JButton("View Source Code");
        btnViewSourceCode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int r = tblDeliverable.getSelectedRow();
        		if(r > -1)
        		{
            		String sAID = tblDeliverable.getValueAt(r, 0).toString();
            		String sSID = tblDeliverable.getValueAt(r, 1).toString();
            		String sName = tblDeliverable.getValueAt(r, 2).toString() + ", " + tblDeliverable.getValueAt(r, 3).toString();
            		String sActivityName = "";
            		try
            		{
            			ActivityDAO adao = new ActivityDAO();
            			Activity a = adao.getActivity(Integer.parseInt(sAID));
            			sActivityName = a.getActivityName();
            		}
					catch (SQLException sqle)
					{
						sqle.printStackTrace();
					}
            		catch (IOException ioe)
            		{
            			ioe.printStackTrace();
            		}
					try
					{
						Deliverable d = ddao.getDeliverable(Integer.parseInt(sSID), Integer.parseInt(sAID));
    					String title = "Source code of " + sSID + " - " + sName + " for " + sActivityName + " - " + d.getDeliverableSourceCodeFileName();
						FileInputStream inputStream = new FileInputStream(d.getDeliverableSourceCode());
    					Scanner sc = new Scanner(inputStream);
    					StringBuilder sb = new StringBuilder();
    					while(sc.hasNextLine())
    					{
    						sb.append(sc.nextLine());
    						if(sc.hasNextLine()) sb.append(System.getProperty("line.separator"));
    					}
    					sc.close();
    					ViewSource vs = new ViewSource();
    					vs.setTitle(title);
    					vs.setTextArea(sb);
					}
					catch (NumberFormatException nfe)
					{
				        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch (SQLException sqle)
					{
						sqle.printStackTrace();
					}
            		catch (IOException ioe)
            		{
            			ioe.printStackTrace();
            		}
        		}
        		else
        		{
			        JOptionPane.showMessageDialog(null, "Please select a deliverable to proceed with source code preview.", "Error", JOptionPane.ERROR_MESSAGE);        			
        		}
        	}
        });
        buttonContainer.add(btnViewSourceCode);
        
        JButton btnPlaceGrade = new JButton("Place Grade");
        btnPlaceGrade.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int r = tblDeliverable.getSelectedRow();
        		if(r > -1)
        		{
            		String sAID = tblDeliverable.getValueAt(r, 0).toString();
            		String sSID = tblDeliverable.getValueAt(r, 1).toString();
            		String sName = tblDeliverable.getValueAt(r, 2).toString() + ", " + tblDeliverable.getValueAt(r, 3).toString();
            		String sActivityName = "";
            		try
            		{
            			ActivityDAO adao = new ActivityDAO();
            			Activity a = adao.getActivity(Integer.parseInt(sAID));
            			sActivityName = a.getActivityName();
            		}
					catch (SQLException sqle)
					{
						sqle.printStackTrace();
					}
            		catch (IOException ioe)
            		{
            			ioe.printStackTrace();
            		}
            		String grade = (String)JOptionPane.showInputDialog(null, "Place a Grade for " + sSID + " - " + sName + " on " + sActivityName + "\'s the selected deliverable", "");
    				if(grade == null) { /* do nothing */ }
    				else if(grade.trim().isEmpty())
    				{
    					JOptionPane.showMessageDialog(null, "Nothing entered.", "Error", JOptionPane.ERROR_MESSAGE);
    				}
    				else
    				{
    					Float fGrade = 0f;
    					try
    					{
    						fGrade = Float.parseFloat(grade);
    						if(fGrade >= 0 && fGrade <= 100)
    						{
    							Deliverable d = ddao.getDeliverable(Integer.parseInt(sSID), Integer.parseInt(sAID));
    							float prevGrade = d.getGrade();
								ddao.changeGrade(Integer.parseInt(sSID), Integer.parseInt(sAID), fGrade);
								if(prevGrade ==	 -1f)
									JOptionPane.showMessageDialog(null, "Successfully placed a grade.", "Success", JOptionPane.INFORMATION_MESSAGE);
								else
									JOptionPane.showMessageDialog(null, "Successfully edited a grade.", "Success", JOptionPane.INFORMATION_MESSAGE);
								switch(selectedMode)
								{
									case FILTER_STUDENT: manipulateDeliverables(ddao.getDeliverablesByStudent(selectedStudent)); break;
									case FILTER_ACTIVITY: manipulateDeliverables(ddao.getDeliverablesByActivity(selectedActivity)); break;
									case FILTER_ALL:
									default: refreshData(); break;
								}
			        	        tblDeliverable.getColumn(columnNames[0]).setMinWidth(32);
			        	        tblDeliverable.getColumn(columnNames[0]).setPreferredWidth(32);
			        	        tblDeliverable.getColumn(columnNames[1]).setMinWidth(24);
			        	        tblDeliverable.getColumn(columnNames[1]).setPreferredWidth(24);
			        	        tblDeliverable.getColumn(columnNames[4]).setMinWidth(16);
			        	        tblDeliverable.getColumn(columnNames[4]).setPreferredWidth(16);	
			        	        tblDeliverable.getColumn(columnNames[5]).setMinWidth(16);
			        	        tblDeliverable.getColumn(columnNames[5]).setPreferredWidth(16);
    						}
    						else
    							JOptionPane.showMessageDialog(null, "Grade input should be from 0 - 100.", "Error", JOptionPane.ERROR_MESSAGE);
    					}
    					catch (NumberFormatException nfe)
    					{
    				        JOptionPane.showMessageDialog(null, "Not a number!", "Error", JOptionPane.ERROR_MESSAGE);
    					}
    					catch (SQLException sqle)
    					{
    						sqle.printStackTrace();
    					}
                		catch (IOException ioe)
                		{
                			ioe.printStackTrace();
                		}
    				}
        		}
        		else
        		{
			        JOptionPane.showMessageDialog(null, "Please select a deliverable to proceed with the grade placement.", "Error", JOptionPane.ERROR_MESSAGE);        			
        		}
        	}
        });
        buttonContainer.add(btnPlaceGrade);
	}
	
	public void manipulateDeliverables(ArrayList<Deliverable> dArray) throws SQLException, IOException
	{
		lateList.clear();
    	Object[][] data = new Object[dArray.size()][7];
    	for(int s = 0; s < dArray.size(); s++)
    	{
    		Deliverable del = dArray.get(s);
    		ArrayList<Object> contents = new ArrayList<Object>();
    		contents.add(del.getActivityID());
    		contents.add(del.getStudentID());
    		StudentDAO sdao = new StudentDAO();
    		Student stud = sdao.getStudent(Integer.parseInt(contents.get(1).toString()));
    		contents.add(stud.getStudentLastName());
    		contents.add(stud.getStudentFirstName());
    		contents.add(stud.getStudentSection());
    		contents.add(del.getGrade());
    		contents.add(del.getDateSubmitted());
    		data[s] = contents.toArray();
    		lateList.add(ddao.isLate(Integer.parseInt(contents.get(1).toString()), Integer.parseInt(contents.get(0).toString())));
    	}
        deliverableModel = new DefaultTableModel(data, columnNames){
        	@Override
        	public boolean isCellEditable(int row, int column){return false;}
        };
        tblDeliverable.setModel(deliverableModel);
	}
	
	public void refreshData() throws SQLException, IOException
	{
		lateList.clear();
		ArrayList<Deliverable> dArray = new ArrayList<Deliverable>();
    	dArray = ddao.getDeliverables();
    	Object[][] data = new Object[dArray.size()][7];
    	for(int s = 0; s < dArray.size(); s++)
    	{
    		Deliverable del = dArray.get(s);
    		ArrayList<Object> contents = new ArrayList<Object>();
    		contents.add(del.getActivityID());
    		contents.add(del.getStudentID());
    		StudentDAO sdao = new StudentDAO();
    		Student stud = sdao.getStudent(Integer.parseInt(contents.get(1).toString()));
    		contents.add(stud.getStudentLastName());
    		contents.add(stud.getStudentFirstName());
    		contents.add(stud.getStudentSection());
    		contents.add(del.getGrade());
    		contents.add(del.getDateSubmitted());
    		data[s] = contents.toArray();
    		lateList.add(ddao.isLate(Integer.parseInt(contents.get(1).toString()), Integer.parseInt(contents.get(0).toString())));
    	}
        deliverableModel = new DefaultTableModel(data, columnNames){
        	@Override
        	public boolean isCellEditable(int row, int column){return false;}
        };
        tblDeliverable.setModel(deliverableModel);
	}
	
	public DeliverableList()
	{
		initialize();
	}
}
