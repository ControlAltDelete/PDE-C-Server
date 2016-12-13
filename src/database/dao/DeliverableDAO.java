package database.dao;

import database.objects.Deliverable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * The Data Access Object that involves the querying and updating the <code>Deliverable</code> table.
 * 
 * <p>
 * This executes the commands of executing the query and updates of <code>Deliverable</code> table.
 * </p>
 * 
 * @author In Yong S. Lee
 * @author Alexander John D. Jose.
 */
public class DeliverableDAO extends DAO{

	/**
	 * Adds <code>Deliverable</code> using its model representation to the <code>Deliverable</code> table in the database.
	 * @param dmdl The <code>Deliverable</code> Model Representation to add.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 * @throws FileNotFoundException if the source code being uploaded is not found during the process
	 */
	public void addDeliverable (Deliverable dmdl) throws SQLException, FileNotFoundException{
        //int deliverableID = dmdl.getDeliverableID();
    	int studentID = dmdl.getStudentID();
    	int activityID = dmdl.getActivityID();
    	File deliverableSourceCode = dmdl.getDeliverableSourceCode();
    	Timestamp dateSubmitted = dmdl.getDateSubmitted();
    	String deliverableSourceCodeFileName = dmdl.getDeliverableSourceCodeFileName();
    	float grade = dmdl.getGrade();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into Deliverable (StudentID, ActivityID, DeliverableSourceCode, DateSubmitted, DeliverableSourceCodeFileName, Grade) values(?, ?, ?, ?, ?, ?)");
        //preparedStatement.setInt(1, deliverableID);
        preparedStatement.setInt(1, studentID);
        preparedStatement.setInt(2, activityID);
        preparedStatement.setBlob(3, new FileInputStream(deliverableSourceCode));
        preparedStatement.setTimestamp(4, dateSubmitted);
        preparedStatement.setString(5, deliverableSourceCodeFileName);
        preparedStatement.setFloat(6, grade);
        update(preparedStatement);
        close(preparedStatement, connection);
    }
	
	public void changeGrade (int studentID, int activityID, int deliverableID, Float newGrade) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update Deliverable set Grade = ? where StudentID = ? AND ActivityID = ? AND DeliverableID = ?");
        preparedStatement.setFloat(1, newGrade);
        preparedStatement.setInt(2, studentID);
        preparedStatement.setInt(3, activityID);
        preparedStatement.setInt(4, deliverableID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }
	
	public void uploadNewSubmission (int studentID, int activityID, File newFile) throws SQLException, FileNotFoundException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("update Deliverable set DeliverableSourceCode = ?, DeliverableSourceCodeFileName = ?, DateSubmitted = ? where StudentID = ? AND ActivityID = ?");
        preparedStatement.setBlob(1, new FileInputStream(newFile));
        preparedStatement.setString(2, newFile.getName());
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        preparedStatement.setInt(4, studentID);
        preparedStatement.setInt(5, activityID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }

	/**
	 * Deletes the <code>Deliverable</code> according to <code>deliverableID</code>.
	 * @param deliverableID the <code>Deliverable</code> ID to delete.
	 * @throws SQLException if the connection fails or the querying of the table is refused
	 */
    public void deleteDeliverable (int deliverableID) throws SQLException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from Deliverable where DeliverableID = ?");
        preparedStatement.setInt(1, deliverableID);
        update(preparedStatement);
        close(preparedStatement, connection);
    }

    /**
     * Retrieves the <code>Deliverable</code> according to <code>sID</code> and <code>aID</code>.
     * @param sID the target <code>Student</code> ID.
     * @param aID the target <code>Activity</code> ID.
     * @return The <code>Deliverable</code> Model Representation according to <code>Student</code> ID and <code>Activity</code> ID.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the source code cannot be read
     */
    public Deliverable getDeliverable (int sID, int aID) throws SQLException, IOException{
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Deliverable where StudentID = ? AND ActivityID = ?");
        preparedStatement.setInt(1, sID);
        preparedStatement.setInt(2, aID);
        ResultSet resultSet = query(preparedStatement);
        Deliverable dmdl = new Deliverable();
        while (resultSet.next()) {
            int deliverableID = resultSet.getInt("DeliverableID");
        	int studentID = resultSet.getInt("StudentID");
        	int activityID = resultSet.getInt("ActivityID");
        	String deliverableSourceCodeFileName = resultSet.getString("DeliverableSourceCodeFileName");
        	Blob b = resultSet.getBlob("DeliverableSourceCode");
        	InputStream binaryStream = b.getBinaryStream(1, b.length());
        	byte[] buffer = new byte[binaryStream.available()];
            binaryStream.read(buffer);
        	File deliverableSourceCode = new File(deliverableSourceCodeFileName);
        	OutputStream outStream = new FileOutputStream(deliverableSourceCode);
        	outStream.write(buffer);
        	outStream.close();
        	Timestamp dateSubmitted = resultSet.getTimestamp("DateSubmitted");
        	float grade = resultSet.getFloat("Grade");
            dmdl.setDeliverableID(deliverableID);
            dmdl.setStudentID(studentID);
            dmdl.setActivityID(activityID);
            dmdl.setDeliverableSourceCode(deliverableSourceCode);
            dmdl.setDateSubmitted(dateSubmitted);
            dmdl.setDeliverableSourceCodeFileName(deliverableSourceCodeFileName);
            dmdl.setGrade(grade);
        }
        close(preparedStatement, connection);
        return dmdl;
    }

    /**
     * Retrieves the <code>Deliverable</code>s from the database.
     * @return The <code>Deliverable</code>s from the database.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the one of the source code(s) cannot be read
     */
    public ArrayList<Deliverable> getDeliverables () throws SQLException, IOException{
        ArrayList<Deliverable> deliverables = new ArrayList<Deliverable>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Deliverable order by DeliverableID");
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	Deliverable dmdl = new Deliverable();
        	int deliverableID = resultSet.getInt("DeliverableID");
        	int studentID = resultSet.getInt("StudentID");
        	int activityID = resultSet.getInt("ActivityID");
        	String deliverableSourceCodeFileName = resultSet.getString("DeliverableSourceCodeFileName");
        	Blob b = resultSet.getBlob("DeliverableSourceCode");
        	InputStream binaryStream = b.getBinaryStream(1, b.length());
        	byte[] buffer = new byte[binaryStream.available()];
            binaryStream.read(buffer);
        	File deliverableSourceCode = new File(deliverableSourceCodeFileName);
        	OutputStream outStream = new FileOutputStream(deliverableSourceCode);
        	outStream.write(buffer);
        	outStream.close();
        	Timestamp dateSubmitted = resultSet.getTimestamp("DateSubmitted");
        	float grade = resultSet.getFloat("Grade");
            dmdl.setDeliverableID(deliverableID);
            dmdl.setStudentID(studentID);
            dmdl.setActivityID(activityID);
            dmdl.setDeliverableSourceCode(deliverableSourceCode);
            dmdl.setDateSubmitted(dateSubmitted);
            dmdl.setDeliverableSourceCodeFileName(deliverableSourceCodeFileName);
            dmdl.setGrade(grade);
            dmdl.setDeliverableID(deliverableID);
            deliverables.add(dmdl);
        }
        close(preparedStatement, connection);
        return deliverables;
    }

    /**
     * Retrieves the <code>Deliverable</code>s according to <code>aID</code>.
     * @param aID the <code>ActivityID</code>.
     * @return the number of activities uploaded by the professor-in-charge.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the one of the source code(s) cannot be read
     */
    public ArrayList<Deliverable> getDeliverablesByActivity(int aID) throws SQLException, IOException{
        ArrayList<Deliverable> deliverables = new ArrayList<Deliverable>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Deliverable where ActivityID = ? order by DeliverableID");
        preparedStatement.setInt(1, aID);
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	Deliverable dmdl = new Deliverable();
        	int deliverableID = resultSet.getInt("DeliverableID");
        	int studentID = resultSet.getInt("StudentID");
        	int activityID = resultSet.getInt("ActivityID");
        	String deliverableSourceCodeFileName = resultSet.getString("DeliverableSourceCodeFileName");
        	Blob b = resultSet.getBlob("DeliverableSourceCode");
        	InputStream binaryStream = b.getBinaryStream(1, b.length());
        	byte[] buffer = new byte[binaryStream.available()];
            binaryStream.read(buffer);
        	File deliverableSourceCode = new File(deliverableSourceCodeFileName);
        	OutputStream outStream = new FileOutputStream(deliverableSourceCode);
        	outStream.write(buffer);
        	outStream.close();
        	Timestamp dateSubmitted = resultSet.getTimestamp("DateSubmitted");
        	float grade = resultSet.getFloat("Grade");
            dmdl.setDeliverableID(deliverableID);
            dmdl.setStudentID(studentID);
            dmdl.setActivityID(activityID);
            dmdl.setDeliverableSourceCode(deliverableSourceCode);
            dmdl.setDateSubmitted(dateSubmitted);
            dmdl.setDeliverableSourceCodeFileName(deliverableSourceCodeFileName);
            dmdl.setGrade(grade);
            dmdl.setDeliverableID(deliverableID);
            deliverables.add(dmdl);
        }
        close(preparedStatement, connection);
        return deliverables;
    }

    /**
     * Retrieves the <code>Deliverable</code>s according to <code>sID</code>.
     * @param sID the <code>StudentID</code>.
     * @return the number of activities uploaded by the professor-in-charge.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     * @throws IOException if the one of the source code(s) cannot be read
     */
    public ArrayList<Deliverable> getDeliverablesByStudent(int sID) throws SQLException, IOException{
        ArrayList<Deliverable> deliverables = new ArrayList<Deliverable>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from Deliverable where StudentID = ? order by DeliverableID");
        preparedStatement.setInt(1, sID);
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	Deliverable dmdl = new Deliverable();
        	int deliverableID = resultSet.getInt("DeliverableID");
        	int studentID = resultSet.getInt("StudentID");
        	int activityID = resultSet.getInt("ActivityID");
        	String deliverableSourceCodeFileName = resultSet.getString("DeliverableSourceCodeFileName");
        	Blob b = resultSet.getBlob("DeliverableSourceCode");
        	InputStream binaryStream = b.getBinaryStream(1, b.length());
        	byte[] buffer = new byte[binaryStream.available()];
            binaryStream.read(buffer);
        	File deliverableSourceCode = new File(deliverableSourceCodeFileName);
        	OutputStream outStream = new FileOutputStream(deliverableSourceCode);
        	outStream.write(buffer);
        	outStream.close();
        	Timestamp dateSubmitted = resultSet.getTimestamp("DateSubmitted");
        	float grade = resultSet.getFloat("Grade");
            dmdl.setDeliverableID(deliverableID);
            dmdl.setStudentID(studentID);
            dmdl.setActivityID(activityID);
            dmdl.setDeliverableSourceCode(deliverableSourceCode);
            dmdl.setDateSubmitted(dateSubmitted);
            dmdl.setDeliverableSourceCodeFileName(deliverableSourceCodeFileName);
            dmdl.setGrade(grade);
            dmdl.setDeliverableID(deliverableID);
            deliverables.add(dmdl);
        }
        close(preparedStatement, connection);
        return deliverables;
    }

    /**
     * Retrieves the total number of <code>Deliverable</code>s submitted by the student.
     * @param sID the <code>StudentID</code>.
     * @return the number of activities uploaded by the professor-in-charge.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     */
    public int getDeliverableCountByStudent(int sID) throws SQLException{
    	int result = 0;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select count(distinct ActivityID) as ActivitiesObtained from Deliverable where StudentID = ? order by DeliverableID");
        preparedStatement.setInt(1, sID);
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	result = resultSet.getInt("ActivitiesObtained");
        }
        return result;
    }
    
    /**
     * Determines if the said <code>studentID</code> submits past the deadline given the <code>activityID</code>.
     * @param studentID the target <code>Student</code>, to check the deliverable submitted by the student.
     * @param activityID the target <code>Activity</code>, to check its <code>ActivityDeadline</code> property.
     * @return <code>true</code> if late, <code>false</code> otherwise.
	 * @throws SQLException if the connection fails or the querying of the table is refused
     */
    public boolean isLate(int studentID, int activityID)throws SQLException
    {
    	Timestamp deliverableSubmitted = new Timestamp(System.currentTimeMillis()); // default
    	Timestamp activityDeadline = new Timestamp(System.currentTimeMillis()); // default
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select `activity`.ActivityDeadline, `deliverable`.DateSubmitted from Activity, Deliverable where `activity`.ActivityID = ? AND `deliverable`.StudentID = ? AND `deliverable`.ActivityID = ?");
        preparedStatement.setInt(1, activityID);
        preparedStatement.setInt(2, studentID);
        preparedStatement.setInt(3, activityID);
        ResultSet resultSet = query(preparedStatement);
        while (resultSet.next()) {
        	deliverableSubmitted = resultSet.getTimestamp(1);
        	activityDeadline = resultSet.getTimestamp(2);
        }
        close(preparedStatement, connection);
        return activityDeadline.after(deliverableSubmitted);
    }
    
}
