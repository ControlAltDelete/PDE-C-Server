package view;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import database.dao.StudentDAO;
import database.objects.Student;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ListSelectionModel;

public class StudentList extends JPanel {
	public static StudentList studentInstance = null;
	private JTextField textField;
	private Object[][] data;
	private DefaultTableModel studentModel;
	private JTable tblStudent;
	final private String[] columnNames = 
	{
	    "ID Number",
	    "First Name",
	    "Last Name",
	    "Section"
	};

	public static StudentList getInstance(){
		if(studentInstance == null)
		{
			studentInstance = new StudentList();
		}
		return studentInstance;
	}
	
	private void initialize()
	{
        try
        {
            StudentDAO sdao = new StudentDAO();
            ArrayList<Student> sArray = new ArrayList<Student>();
        	sArray = sdao.getStudents();
        	data = new Object[sArray.size()][4];
        	for(int s = 0; s < sArray.size(); s++)
        	{
        		Student stud = sArray.get(s);
        		ArrayList<Object> contents = new ArrayList<Object>();
        		contents.add(stud.getStudentID());
        		contents.add(stud.getStudentFirstName());
        		contents.add(stud.getStudentLastName());
        		contents.add(stud.getStudentSection());
        		data[s] = contents.toArray();
        	}
        }
        catch (SQLException sqle)
        {
			JOptionPane.showMessageDialog(null, "No Connection to SQL!", "Error", JOptionPane.ERROR_MESSAGE);
        	data = new Object[1][4];
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	data = new Object[1][4];
        }
        studentModel = new DefaultTableModel(data, columnNames){
        	
        	@Override
        	public boolean isCellEditable(int row, int column){return false;}
        	
        };
    	setLayout(new BorderLayout(0, 0));
 
        tblStudent = new JTable(studentModel);
        tblStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblStudent.getTableHeader().setReorderingAllowed(false);
        tblStudent.setAutoCreateRowSorter(true);   //enables clicking the row for sort
        tblStudent.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        tblStudent.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(tblStudent);
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(1.0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        //Add the scroll pane to this panel.
        splitPane.setTopComponent(scrollPane);
        
        JPanel buttonContainer = new JPanel();
        splitPane.setBottomComponent(buttonContainer);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
				addStudentFr b = new addStudentFr();
				b.showFrame();
        	}
        });
        
        JButton btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int r = tblStudent.getSelectedRow();
        		if(r > -1)
	        		{
	        		String sSID = tblStudent.getValueAt(r, 0).toString();
					StudentDAO sdao = new StudentDAO();
	        		try {
						new StudentProfile(sdao.getStudent(Integer.parseInt(sSID)));
					} catch (NumberFormatException nfe) {
						// TODO Auto-generated catch block
						nfe.printStackTrace();
					} catch (IOException ioe) {
						// TODO Auto-generated catch block
						ioe.printStackTrace();
					} catch (SQLException sqle) {
						// TODO Auto-generated catch block
						sqle.printStackTrace();
					}
        		}
        		else
        		{
			        JOptionPane.showMessageDialog(null, "Please select a student to view.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        buttonContainer.add(btnView);
        buttonContainer.add(btnAdd);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int r = tblStudent.getSelectedRow();
        		if(r > -1)
        		{
            		String sSID = tblStudent.getValueAt(r, 0).toString();
            		String sName = tblStudent.getValueAt(r, 2).toString() + ", " + tblStudent.getValueAt(r, 1).toString();
        			int ans = JOptionPane.showConfirmDialog(null, 
        				    "Are you sure you want to delete " + sSID + " - " + sName + "?",
        				    "Confirm",
        				    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        			if(ans == JOptionPane.YES_OPTION)
        			{
        				try
        				{
        					StudentDAO sdao = new StudentDAO();
        					sdao.deleteStudent(Integer.parseInt(sSID));
        			        refreshData();
        			        JOptionPane.showMessageDialog(null, "Successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
        				}
        				catch(SQLException sqle)
        				{
        					sqle.printStackTrace();
        				}
        			}
        		}
        		else
        		{
			        JOptionPane.showMessageDialog(null, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        buttonContainer.add(btnDelete);
        add(splitPane);
	}
	
	/**
	 * Create the panel.
	 */
	public StudentList()
	{
		initialize();
	}
	
	public void refreshData() throws SQLException
	{
		StudentDAO sdao = new StudentDAO();
        ArrayList<Student> sArray = new ArrayList<Student>();
    	sArray = sdao.getStudents();
    	data = new Object[sArray.size()][4];
    	for(int s = 0; s < sArray.size(); s++)
    	{
    		Student stud = sArray.get(s);
    		ArrayList<Object> contents = new ArrayList<Object>();
    		contents.add(stud.getStudentID());
    		contents.add(stud.getStudentFirstName());
    		contents.add(stud.getStudentLastName());
    		contents.add(stud.getStudentSection());
    		data[s] = contents.toArray();
    	}

        studentModel = new DefaultTableModel(data, columnNames){
        	@Override
        	public boolean isCellEditable(int row, int column){return false;}
        };
        tblStudent.setModel(studentModel);
	}

}