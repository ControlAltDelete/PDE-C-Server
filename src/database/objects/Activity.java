package database.objects;

import java.sql.Timestamp;

public class Activity 
{

	private int activityID;
	private String activityName;
	private String activityFile;
	private Timestamp activityTimeStamp;
	private Timestamp activitDeadline;
	private String activityFilename;
	
	public Activity() 
	{
		
	}

	public Activity(int activityID, String activityName, String activityFile, Timestamp activityTimeStamp,
			Timestamp activitDeadline, String activityFilename) 
	{
		this.activityID = activityID;
		this.activityName = activityName;
		this.activityFile = activityFile;
		this.activityTimeStamp = activityTimeStamp;
		this.activitDeadline = activitDeadline;
		this.activityFilename = activityFilename;
	}
	
	public int getActivityID() 
	{
		return activityID;
	}
	
	public void setActivityID(int activityID) 
	{
		this.activityID = activityID;
	}
	
	public String getActivityName() 
	{
		return activityName;
	}
	
	public void setActivityName(String activityName) 
	{
		this.activityName = activityName;
	}
	
	public String getActivityFile()
	{
		return activityFile;
	}
	
	public void setActivityFile(String activityFile) 
	{
		this.activityFile = activityFile;
	}
	
	public Timestamp getActivityTimeStamp() 
	{
		return activityTimeStamp;
	}
	
	public void setActivityTimeStamp(Timestamp activityTimeStamp) 
	{
		this.activityTimeStamp = activityTimeStamp;
	}
	
	public Timestamp getActivityDeadline() 
	{
		return activitDeadline;
	}
	
	public void setActivityDeadline(Timestamp activitDeadline) 
	{
		this.activitDeadline = activitDeadline;
	}
	
	public String getActivityFilename() {
		return activityFilename;
	}
	
	public void setActivityFilename(String activityFilename) 
	{
		this.activityFilename = activityFilename;
	}
}
