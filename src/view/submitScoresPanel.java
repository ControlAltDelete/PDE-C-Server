package view;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import database.dao.ActivityDAO;
import database.dao.DeliverableDAO;
import database.dao.StudentDAO;
import database.objects.Activity;
import database.objects.Student;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class submitScoresPanel extends JPanel {
	private JTextField txtScore;
	private Object listData[];
	private JList<Object> list;
	private JLabel lblActivityNo;
	private JButton btnSubmit;
	private JComboBox<Object> activityList;

	/**
	 * Create the panel.
	 * 
	 * 
	 */
	public submitScoresPanel() {
		
		list = new JList<Object>();
		lblActivityNo = new JLabel("Activity No.");
		btnSubmit = new JButton("Submit");
		activityList = new JComboBox<Object>();
		
		try
		{
			StudentDAO sdao = new StudentDAO();
			ArrayList<Student> studs = sdao.getStudents(); // send back the received data, get all student list
			ArrayList<String> studLabel = new ArrayList<String>();
			for(int s = 0; s < studs.size(); s++)
			{
				studLabel.add(studs.get(s).getStudentID() + " - " + studs.get(s).getStudentLastName() + ", " + studs.get(s).getStudentFirstName());
			}
			listData = studLabel.toArray();
		}
		catch (SQLException sqle)
		{
			listData = new String[1];
			listData[0] = "No SQL Connection";
			list.setEnabled(false);
		}
		
		setLayout(null);
		list = new JList<Object>(listData);
		list.setBounds(10, 10, 194, 278);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setVisible(true);
		add(list);
		
		lblActivityNo.setBounds(214, 11, 87, 14);
		add(lblActivityNo);
		
		txtScore = new JTextField();
		txtScore.setBounds(311, 33, 103, 20);
		add(txtScore);
		txtScore.setColumns(10);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setBounds(214, 36, 87, 14);
		add(lblScore);
		
		activityList.setBounds(311, 8, 500, 20);
		
		try
		{
			ActivityDAO adao = new ActivityDAO();
			ArrayList<Activity> acts = adao.getActivities(); // send back the received data, get all activity list
			ArrayList<String> actLabel = new ArrayList<String>();
			for(int a = 0; a < acts.size(); a++)
				actLabel.add(acts.get(a).getActivityID() + " - " + acts.get(a).getActivityName());
			Object[] actItem = actLabel.toArray();
			if(actItem.length == 0)
			{
				activityList.addItem("No activities...");
				txtScore.setEnabled(false);
				activityList.setEnabled(false);
				btnSubmit.setEnabled(false);
			}
			else
			{
				for(int a = 0; a < actItem.length; a++)
					activityList.addItem(actItem[a]);
			}
		}
		catch (SQLException sqle)
		{
			activityList.addItem("No SQL Connection");
			txtScore.setEnabled(false);
			activityList.setEnabled(false);
			btnSubmit.setEnabled(false);
		}
		catch (IOException sqle)
		{
			System.out.println("File not found");
		}
		
		add(activityList);
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float grade = Float.parseFloat(txtScore.getText());
				String actName = activityList.getSelectedItem().toString();
				int actID = Integer.parseInt(actName.substring(0, actName.indexOf(" ")));
				String studName = list.getSelectedValue().toString();
				int studID = Integer.parseInt(studName.substring(0, studName.indexOf(" ")));
				DeliverableDAO ddao = new DeliverableDAO();
				try
				{
					ddao.changeGrade(studID, actID, grade);
				}
				catch (SQLException sqle)
				{
					System.out.println("No SQL Connection");
				}
			}
		});
		btnSubmit.setBounds(311, 64, 103, 23);
		add(btnSubmit);

	}
}
