package service;

import server.Server;

public class ServerHandler
{
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
