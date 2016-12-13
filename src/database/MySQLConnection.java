package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates a database for MySQL.
 * 
 * <p>
 *  All MySQL queries will be directed here, where <code>DAO</code> will be used.
 *  As such, this class is used to represent the current database to be used, which is Classroom. 
 * </p>
 * 
 * @author In Yong S. Lee
 */
public class MySQLConnection extends DatabaseFactory
{

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/Classroom?autoReconnect=true&useSSL=false";
    private String username;
    private String password;

    /**
	 * Creates an instance of <code>MySQLConnection</code> with the <code>username</code> and <code>password</code>.
	 * @param u the <code>username</code> to use
	 * @param p the <code>password</code> to use
	 */
    public MySQLConnection(String u, String p)
    {
       this.username = u;
       this.password = p;
    }
    
    /**
	 * Creates an instance of <code>MySQLConnection</code> without the <code>username</code> and <code>password</code>.
	 */
    public MySQLConnection()
    {
    	
    }
    
    /**
	 * Gets the current connection of the MySQL.
	 */
    @Override
    public Connection getConnection()
    {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return null;
    }

	/**
	 * Tests the current connection of MySQL.
	 * @param u the <code>username</code> to use
	 * @param p the <code>password</code> to use
	 */
    @Override
    public Connection testConnection(String u, String p)
    {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, u, p);
            return connection;
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return null;
    }

    /**
     * Sets the <code>username</code> of <code>MySQLConnection</code>.
     * @param username the <code>username</code> to use
     */
	public void setUsername(String username)
	{
		this.username = username;
	}

    /**
     * Sets the <code>password</code> of <code>MySQLConnection</code>.
     * @param password the <code>password</code> to use
     */
	public void setPassword(String password)
	{
		this.password = password;
	}
}

