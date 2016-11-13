package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import api.component.PObject;

public class ObjectReceiver extends Thread
{
  private ServerSocket serverSocket;
  
  public ObjectReceiver(int port) throws IOException
  {
	serverSocket = new ServerSocket(port);
  }
  
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
  
  public static void main(String[] args)
  {
	int port = 2022;
	try
	{
	  Thread t = new ObjectReceiver(port);
	  t.start();
	}
	
	catch (Exception ex)
	{
	  ex.printStackTrace();
	}
  }
}
