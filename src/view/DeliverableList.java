package view;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class DeliverableList extends JPanel {
	public static DeliverableList deliverableInstance = null;
	private JTable tblDeliverable;
	private DeliverableDAO ddao = new DeliverableDAO();

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
        String[] columnNames = {"Activity No.",
                                "ID Number",
                                "Last Name",
                                "First Name",
                                "Section",
                                "Grade",
                                "Date Submitted"
                                };
 
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
        
        
        try
        {
            ArrayList<Deliverable> dArray = new ArrayList<Deliverable>();
        	dArray = ddao.getDeliverables();
        	data = new Object[dArray.size()][7];
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
        	}
        }
        catch (SQLException sqle)
        {
        	System.out.println("No connection to MySQL.");
        	data = new Object[1][7];
        }
        catch (IOException ioe)
        {
        	ioe.printStackTrace();
        	data = new Object[1][7];
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	data = new Object[1][7];
        }
 
        DefaultTableModel deliverables = new DefaultTableModel(data, columnNames){
        	
        	@Override
        	public boolean isCellEditable(int row, int column){return false;}
        	
        };
        
        JSplitPane splitPane = new JSplitPane();
        add(splitPane);
        splitPane.setResizeWeight(1.0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        JPanel buttonContainer = new JPanel();
        JTable tableDeliverable = new JTable(deliverables);
        tableDeliverable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDeliverable.setAutoCreateRowSorter(true);
        tableDeliverable.setTableHeader(null); 
        tableDeliverable.setRowSelectionAllowed(true);
        tableDeliverable.setShowGrid(false);
        tableDeliverable.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        tableDeliverable.setFillsViewportHeight(true);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(tableDeliverable);
        //Add the scroll pane to this panel.
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(buttonContainer);
        
        JButton btnFilterByStudent = new JButton("Filter By Student");
        btnFilterByStudent.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// DAO
        	}
        });
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Update Data
        	}
        });
        buttonContainer.add(btnRefresh);
        buttonContainer.add(btnFilterByStudent);
        
        JButton btnFilterByActivity = new JButton("Filter By Activity");
        btnFilterByActivity.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// DAO
        	}
        });
        buttonContainer.add(btnFilterByActivity);
        
        JButton btnViewSourceCode = new JButton("View Source Code");
        btnViewSourceCode.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// DAO
        	}
        });
        buttonContainer.add(btnViewSourceCode);
        
        JButton btnPlaceGrade = new JButton("Place Grade");
        btnPlaceGrade.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int r = tableDeliverable.getSelectedRow();
        		if(r > -1)
        		{
            		String sAID = tableDeliverable.getValueAt(r, 0).toString();
            		String sSID = tableDeliverable.getValueAt(r, 1).toString();
            		String sName = tableDeliverable.getValueAt(r, 2).toString() + ", " + tableDeliverable.getValueAt(r, 3).toString();
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
			        JOptionPane.showMessageDialog(null, "Please select a deliverable to proceed with the placement of grade.", "Error", JOptionPane.ERROR_MESSAGE);        			
        		}
        	}
        });
        buttonContainer.add(btnPlaceGrade);
	}
	
	public DeliverableList()
	{
		initialize();
	}
}
