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

import database.dao.StudentDAO;
import database.objects.Student;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Panel1 extends JPanel {
	private JTextField textField;
	private Object[][] data;

	/**
	 * Create the panel.
	 */
	public Panel1() {
        
        String[] columnNames = 
        	{
	            "ID Number",
	            "First Name",
	            "Last Name",
	            "Section"
            };
        
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
    	setLayout(new BorderLayout(0, 0));
 
        final JTable table = new JTable(data, columnNames);
        table.setEnabled(false);
        table.setAutoCreateRowSorter(true);   //enables clicking the row for sort
        table.setPreferredScrollableViewportSize(new Dimension(1000, 70));
        table.setFillsViewportHeight(true);
 
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setEnabled(false);
        splitPane.setResizeWeight(1.0);
        splitPane.setOneTouchExpandable(true);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        //Add the scroll pane to this panel.
        splitPane.setTopComponent(scrollPane);
        
        JPanel buttonContainer = new JPanel();
        splitPane.setBottomComponent(buttonContainer);
        
        JButton Add = new JButton("Add");
        Add.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
				addStudentFr b = new addStudentFr();
				b.showFrame();
        	}
        });
        buttonContainer.add(Add);
        
        JButton Delete = new JButton("Delete");
        buttonContainer.add(Delete);
        
        add(splitPane);
        
	}

}