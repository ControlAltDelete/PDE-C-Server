package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Creates an abstract and factory representation of the database.
 * 
 * @author In Yong S. Lee
 */
public abstract class DatabaseFactory 
{
	
	private static DatabaseFactory db = null;
	
	/**
	 * Gets the instance of <code>DatabaseFactory</code>, if it exists. Otherwise, it will create a new instance of <code>DatabaseFactory</code>.
	 * @return the <code>MySQLConnection</code>'s instance without <code>username</code> and <code>password</code>
	 */
	public static DatabaseFactory getInstance() 
	{
		if(db == null) 
		{
			db = new MySQLConnection();
		}
		
		return db;
	}

	/**
	 * Gets the instance of <code>DatabaseFactory</code>, if it exists. Otherwise, it will create a new instance of <code>DatabaseFactory</code>.
	 * @param u the <code>username</code> to use
	 * @param p the <code>password</code> to use
	 * @return the <code>MySQLConnection</code>'s instance with <code>username</code> and <code>password</code>.
	 */
	public static DatabaseFactory getInstance(String u, String p)
	{

		if(db == null) 
		{
			db = new MySQLConnection(u, p);
		}
		
		return db;
		
	}

	/**
	 * Gets the current connection of the database.
	 */
	public abstract Connection getConnection();

	/**
	 * Tests the current connection of the Database.
	 * @param u the <code>username</code> to use
	 * @param p the <code>password</code> to use
	 */
	public abstract Connection testConnection(String u, String p);

	/**
	 * Closes the current <code>Connection</code> of the database.
	 * @param connection the <code>Connection</code> to close
	 */
	public void closeConnection(Connection connection)
	{
		try 
		{
			connection.close();
		}
		catch (Exception e) {
			
		}
	}

	/**
	 * Closes the current <code>ResultSet</code> of the database.
	 * @param resultSet the <code>ResultSet</code> to close
	 */
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

	/**
	 * Closes the current <code>Statement</code> of the database.
	 * @param statement the <code>Statement</code> to close
	 */
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
