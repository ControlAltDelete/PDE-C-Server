package database.dao;

import database.objects.Activity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

/**
 * The Data Access Object that involves the querying and updating the <code>Activity</code> table.
 * 
 * <p>
 * This executes the commands of executing the query and updates of <code>Activity</code> table.
 * </p>
 * 
 * @author In Yong S. Lee
 */
public class ActivityDAO extends DAO{
	
	/**
	 * Adds <code>Activity</code> using its model representation to the <code>Activity</code> table in the database.
	 * @param amdl The <code>Activity</code> Model Representation to add.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 * @throws FileNotFoundException if the PDF file being uploaded is not found during the process
	 */
	public void addActivity (Activity amdl) throws SQLException, FileNotFoundException{
        //int activityID = amdl.getActivityID();
        String activityName = amdl.getActivityName();
    	String activityFile = amdl.getActivityFile();
    	Timestamp activityTimeStamp = amdl.getActivityTimeStamp();
    	Timestamp activityDeadline = amdl.getActivityDeadline();
    	String activityFilename = amdl.getActivityFilename();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Activity (ActivityName, ActivityFile, ActivityTimestamp, ActivityDeadline, ActivityFilename) values(?, ?, ?, ?, ?)");
        //preparedStatement.setInt(1, activityID);
        preparedStatement.setString(1, activityName);
        Blob blob = connection.createBlob();
        blob.setBytes(1, activityFile.getBytes());
        preparedStatement.setBlob(2, blob);
        preparedStatement.setTimestamp(3, activityTimeStamp);
        preparedStatement.setTimestamp(4, activityDeadline);
        preparedStatement.setString(5, activityFilename);
        update(preparedStatement);
        close(preparedStatement, connection);
    }

	/**
	 * Removes the <code>Activity</code> according to <code>activityID</code>.
	 * @param activityID the <code>Activity</code> ID to delete.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 */
    public void deleteActivity (int activityID) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from Activity where ActivityID = ?");
        preparedStatement.setInt(1, activityID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }
    
    /**
	 * Sets the <code>newDeadline</code> of submission of the <code>Activity</code> according to <code>activityID</code>.
	 * @param activityID the target <code>Activity</code> ID.
	 * @param newDeadline the <code>newDeadline</code> to issue for <code>ActivityDeadline</code>.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 */
    public void changeDate (int activityID, Timestamp newDeadline) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update Activity set ActivityDeadline = ?, ActivityTimestamp = ? where ActivityID = ?");
        preparedStatement.setTimestamp(1, newDeadline);
        preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setInt(3, activityID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }

    /**
	 * Sets the <code>newName</code> of the <code>Activity</code> according to <code>activityID</code>.
	 * @param activityID the target <code>Activity</code> ID.
	 * @param newName the <code>newName</code> to issue for <code>ActivityName</code>.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 */
    public void changeName (int activityID, String newName) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update Activity set ActivityName = ?, ActivityTimestamp = ? where ActivityID = ?");
        preparedStatement.setString(1, newName);
        preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setInt(3, activityID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }

    /**
	 * Sets the <code>newFile</code> of the <code>Activity</code> according to <code>activityID</code>.
	 * @param activityID the target <code>Activity</code> ID.
	 * @param newFile the <code>newFile</code> to issue for <code>ActivityFile</code>.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 * @throws FileNotFoundException if the PDF file being uploaded is not found during the process
	 */
    public void updateFile (int activityID, File newFile) throws SQLException, FileNotFoundException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update Activity set ActivityFile = ?, ActivityFileName = ?, ActivityTimestamp = ? where ActivityID = ?");
        preparedStatement.setBlob(1, new FileInputStream(newFile));
        preparedStatement.setString(2, newFile.getName());
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setInt(4, activityID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }
    
    /**
     * Retrieves the <code>Activity</code> according to <code>idNumber</code>.
     * @param idNumber the target <code>Activity</code> ID.
     * @return The <code>Activity</code> Model Representation according to <code>idNumber</code>.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the said PDF file cannot be read
     */
    public Activity getActivity (int idNumber) throws SQLException, IOException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Activity where ActivityID = ?");
        preparedStatement.setInt(1, idNumber);
        ResultSet resultSet = query(preparedStatement);
        Activity amdl = new Activity();
       
        while (resultSet.next()) {
            int activityID = resultSet.getInt("ActivityID");
            String activityName = resultSet.getString("ActivityName");
            String activityFilename = resultSet.getString("ActivityFilename");
        	Blob b = resultSet.getBlob("ActivityFile");
       
        	byte[] buffer = b.getBytes(1, (int)b.length());
        	String blobContents = new String(buffer);     	
            Timestamp activityTimeStamp = resultSet.getTimestamp("ActivityTimeStamp");
            Timestamp activityDeadline = resultSet.getTimestamp("ActivityDeadline");
            amdl.setActivityID(activityID);
            amdl.setActivityName(activityName);
            amdl.setActivityFile(blobContents);
            amdl.setActivityTimeStamp(activityTimeStamp);
            amdl.setActivityDeadline(activityDeadline);
            amdl.setActivityFilename(activityFilename);
            
        }
        
        close(preparedStatement, connection);
        return amdl;
    }

    /**
     * Retrieves the <code>Activity</code> according to <code>actName</code>.
     * @param actName the target <code>ActivityName</code>.
     * @return The <code>Activity</code> Model Representation according to <code>ActivityName</code>.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the said PDF file cannot be read
     */
    public Activity getActivity (String actName) throws SQLException, IOException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Activity where ActivityName = ?");
        preparedStatement.setString(1, actName);
        ResultSet resultSet = query(preparedStatement);
        Activity amdl = new Activity();
       
        while (resultSet.next()) {
            int activityID = resultSet.getInt("ActivityID");
            String activityName = resultSet.getString("ActivityName");
            String activityFilename = resultSet.getString("ActivityFilename");
        	Blob b = resultSet.getBlob("ActivityFile");
       
        	byte[] buffer = b.getBytes(1, (int)b.length());
        	String blobContents = new String(buffer);     	
            Timestamp activityTimeStamp = resultSet.getTimestamp("ActivityTimeStamp");
            Timestamp activityDeadline = resultSet.getTimestamp("ActivityDeadline");
            amdl.setActivityID(activityID);
            amdl.setActivityName(activityName);
            amdl.setActivityFile(blobContents);
            amdl.setActivityTimeStamp(activityTimeStamp);
            amdl.setActivityDeadline(activityDeadline);
            amdl.setActivityFilename(activityFilename);
            
        }
        
        close(preparedStatement, connection);
        return amdl;
    }

    /**
     * Retrieves the <code>Activity</code>(ies) from the database.
     * @return The <code>Activity</code>(ies) from the database.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the one of the PDF file(s) cannot be read
     */
    public ArrayList<Activity> getActivities () throws SQLException, IOException{ // not yet updated to current edit
        ArrayList<Activity> activities = new ArrayList<Activity>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Activity order by ActivityID");
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	Activity amdl = new Activity();
            int activityID = resultSet.getInt("ActivityID");
            String activityName = resultSet.getString("ActivityName");
            String activityFilename = resultSet.getString("ActivityFilename");
        	Blob b = resultSet.getBlob("ActivityFile");
        	InputStream binaryStream = b.getBinaryStream(0, b.length());
        	byte[] buffer = new byte[binaryStream.available()];
        	String blobContents = new String(buffer);
            binaryStream.read(buffer);
        	File activityFile = new File(activityFilename);
            Timestamp activityTimeStamp = resultSet.getTimestamp("ActivityTimeStamp");
            Timestamp activityDeadline = resultSet.getTimestamp("ActivityDeadline");
            amdl.setActivityID(activityID);
            amdl.setActivityName(activityName);
            amdl.setActivityFile(blobContents);
            amdl.setActivityTimeStamp(activityTimeStamp);
            amdl.setActivityDeadline(activityDeadline);
            amdl.setActivityFilename(activityFilename);
            activities.add(amdl);
        }
        close(preparedStatement, connection);
        return activities;
    }

    /**
     * Retrieves the total number of activities uploaded by the professor-in-charge.
     * @return the number of activities uploaded by the professor-in-charge.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     */
    public int getActivityCount() throws SQLException{
    	int result = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from Activity");
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	result = resultSet.getInt("count(*)");
        }
        return result;
    }
    
    /**
     * Retrieves the <code>Activity</code> names from the database.
     * @return The <code>Activity</code> names from the database.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     */
    public ArrayList<String> getActivityNames() throws SQLException
    {
      ArrayList<String> names = new ArrayList<String>();
      Connection connection = getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("select * from Activity order by ActivityID");
      ResultSet result = query(preparedStatement);
      
      while (result.next())
      {
    	names.add(result.getString("ActivityName"));
      }
      
      return names;
    }
}
