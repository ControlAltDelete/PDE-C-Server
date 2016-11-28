package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DatabaseFactory 
{
	
	public static DatabaseFactory db = null;
	
	public static DatabaseFactory getInstance() 
	{
		if(db == null) 
		{
			db = new MySQLConnection();
		}
		
		return db;
	}

	public static DatabaseFactory getInstance(String u, String p)
	{

		if(db == null) 
		{
			db = new MySQLConnection(u, p);
		}
		
		return db;
		
	}

	public abstract Connection getConnection();

	public abstract Connection testConnection(String u, String p);

	public void closeConnection(Connection connection)
	{
		try 
		{
			connection.close();
		}
		catch (Exception e) {
			
		}
	}

	public void closeResultSet(ResultSet resultSet)
	{
		try
		{
			resultSet.close();
		}
		catch (Exception e)
		{
			
		}
	}

	public void closeStatement(Statement statement)
	{
		try
		{
			statement.close();
		}
		catch (Exception e)
		{
			
		}
	}
	
}
