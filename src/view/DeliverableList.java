package view;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.dao.DeliverableDAO;
import database.dao.StudentDAO;
import database.objects.Deliverable;
import database.objects.Student;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JSplitPane;
import javax.swing.JButton;

public class DeliverableList extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public DeliverableList() {
        super(new GridLayout(1,0));
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
        
        
//        try
//        {
//            DeliverableDAO ddao = new DeliverableDAO();
//            ArrayList<Deliverable> dArray = new ArrayList<Deliverable>();
//        	dArray = ddao.getDeliverables();
//        	data = new Object[dArray.size()][5];
//        	for(int s = 0; s < dArray.size(); s++)
//        	{
//        		Deliverable del = dArray.get(s);
//        		ArrayList<Object> contents = new ArrayList<Object>();
//        		contents.add(del.getDeliverableID());
//        		contents.add(del.getStudentID());
//        		StudentDAO sdao = new StudentDAO();
//        		Student stud = sdao.getStudent(Integer.parseInt(contents.get(1).toString()));
//        		contents.add(stud.getStudentLastName() + ", " + stud.getStudentFirstName());
//        		contents.add(stud.getStudentSection());
//        		contents.add(del.getDateSubmitted());
//        		data[s] = contents.toArray();
//        	}
//        }
//        catch (SQLException sqle)
//        {
//        	System.out.println("No connection to MySQL.");
//        	data = new Object[1][5];
//        }
//        catch (IOException ioe)
//        {
//        	ioe.printStackTrace();
//        	data = new Object[1][5];
//        }
//        catch (Exception e)
//        {
//        	e.printStackTrace();
//        	data = new Object[1][5];
//        }
// 
        DefaultTableModel deliverables = new DefaultTableModel(data, columnNames);
        
        JSplitPane splitPane = new JSplitPane();
        add(splitPane);
        splitPane.setResizeWeight(1.0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        JPanel buttonContainer = new JPanel();
        JTable table_1 = new JTable(deliverables);
        table_1.setAutoCreateRowSorter(true);
        table_1.setTableHeader(null); 
        table_1.setRowSelectionAllowed(true);
        table_1.setShowGrid(false);
        table_1.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table_1.setFillsViewportHeight(true);
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table_1);
        //Add the scroll pane to this panel.
        splitPane.setLeftComponent(scrollPane);
        splitPane.setRightComponent(buttonContainer);
        
        JButton btnFilterByStudent = new JButton("Filter By Student");
        buttonContainer.add(btnFilterByStudent);
        
        JButton btnFilterByActivity = new JButton("Filter By Activity");
        buttonContainer.add(btnFilterByActivity);
        
        JButton btnViewSourceCode = new JButton("View Source Code");
        buttonContainer.add(btnViewSourceCode);
        
        JButton btnPlaceGrade = new JButton("Place Grade");
        buttonContainer.add(btnPlaceGrade);
	}
}
