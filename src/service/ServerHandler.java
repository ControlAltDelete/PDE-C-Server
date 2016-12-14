package service;

import server.Server;

/**
 * Handles the running of server.
 * @author Alexander John D. Jose
 */
public class ServerHandler
{
	/**
	 * Runs the server in a separate <code>Thread</code>.
	 */
  public void runServer()
  {
	try
	{
	  Thread server = new Thread(new Server(Server.PORT_NO));
	  server.start();
	}
	
	catch (Exception ex)
	{
	  ex.printStackTrace();
	}
	
  }
}
