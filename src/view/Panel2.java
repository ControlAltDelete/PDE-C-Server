package view;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

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

public class Panel2 extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public Panel2() {
        super(new GridLayout(1,0));
        
        String[] columnNames = {"Activity No.",
                                "ID Number",
                                "Last Name",
                                "Section",
                                "Grade",
                                "Date Submitted"
                                };
 
        Object[][] data = {
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"},
        {"1", "11220538", "Pua", "S12", "83", "August"}
        };
        
        DeliverableDAO ddao = new DeliverableDAO();
        ArrayList<Deliverable> dArray = new ArrayList<Deliverable>();
        try
        {
        	dArray = ddao.getDeliverables();
        	data = new Object[dArray.size()][5];
        	for(int s = 0; s < dArray.size(); s++)
        	{
        		Deliverable del = dArray.get(s);
        		ArrayList<Object> contents = new ArrayList<Object>();
        		contents.add(del.getDeliverableID());
        		contents.add(del.getStudentID());
        		StudentDAO sdao = new StudentDAO();
        		Student stud = sdao.getStudent(Integer.parseInt(contents.get(1).toString()));
        		contents.add(stud.getStudentLastName() + ", " + stud.getStudentFirstName());
        		contents.add(stud.getStudentSection());
        		contents.add(del.getDateSubmitted());
        		data[s] = contents.toArray();
        	}
        }
        catch (SQLException sqle)
        {
        	System.out.println("No connection to MySQL.");
        	data = new Object[1][5];
        }
        catch (IOException ioe)
        {
        	ioe.printStackTrace();
        	data = new Object[1][5];
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	data = new Object[1][5];
        }
 
        final JTable table = new JTable(data, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setEnabled(false);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
 
        //Add the scroll pane to this panel.
        add(scrollPane);

	}
}
