package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseFactory;

/**
 * The superclass that represents the general Data Access Object.
 * 
 * <p>
 * This executes the general commands of executing the query and updates of the current database being used, which is MySQL.
 * </p>
 * 
 * @author In Yong S. Lee
 */
public class DAO
{
	
	private DatabaseFactory dbfactory = DatabaseFactory.getInstance();
    
    public Connection getConnection()
    {
        Connection connection = dbfactory.getConnection();
        return connection;
    }
    
    //public abstract Object getSingle();
    //public abstract ArrayList getMultiple();
    
    public void update(PreparedStatement preparedStatement) throws SQLException
    {
        preparedStatement.executeUpdate();
    }
    
    public ResultSet query(PreparedStatement preparedStatement) throws SQLException
    {
        return preparedStatement.executeQuery();
    }
    
    public void close(PreparedStatement preparedStatement, Connection connection)
    {
        dbfactory.closeStatement(preparedStatement);
        dbfactory.closeConnection(connection);
    }
    

}
