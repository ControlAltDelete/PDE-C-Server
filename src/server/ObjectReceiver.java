package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import api.component.PObject;

/**
 * Facilitates the receiving of <code>PObject</code> from PDE-C
 * @author Alexander John D. Jose
 *
 */
public class ObjectReceiver extends Thread
{
  private ServerSocket serverSocket;
  
  /**
   * Constructor of <code>ObjectReceiver</code>
   * @param port the port number where the connection should happen
   * @throws IOException If the said object cannot be read.
   */
  public ObjectReceiver(int port) throws IOException
  {
	serverSocket = new ServerSocket(port);
  }
  
  /**
   * Runs the <code>ObjectReceiver</code>
   */
  public void run()
  {
	while(true)
	{
	  try
	  {
		Socket server = serverSocket.accept();
		System.out.println("connected to " + server.getRemoteSocketAddress().toString());
		ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
		PObject obj = (PObject) ois.readObject();
		System.out.println(obj.hashCode());
		ois.close();
		server.close();
	  }
		
	  catch(Exception ex)
	  {
	    ex.printStackTrace();
	  }
	}
  }
}
