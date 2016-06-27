package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import database.dao.StudentDAO;
import database.objects.Student;

public class Server extends Thread
{
  private ServerSocket serverSocket;
  
  public Server(int port) throws IOException
  {
	serverSocket = new ServerSocket(port);
  }
  
  public void run()
  {
	while(true)
	{
	  try
	  {
		System.out.println("Waiting for client ");
		Socket server = serverSocket.accept();
		System.out.println("Just connected to " + server.getRemoteSocketAddress());
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
	      
	    String clientSentence = inFromClient.readLine();
	    System.out.println("From client: "+clientSentence+"\n");
	    ArrayList<String> info = new ArrayList<String>();
	    
	    while(clientSentence.indexOf(",") != -1)
	    {
			String temp = clientSentence.substring(0, clientSentence.indexOf(","));
			info.add(temp);
			clientSentence = clientSentence.substring(clientSentence.indexOf(",") + 1);
		}
	    
	    info.add(clientSentence);
	    clientSentence = "";
	    
	    Student stu = new Student(Integer.parseInt(info.get(0)), info.get(1), info.get(2), info.get(3), info.get(4));
	    StudentDAO sdao = new StudentDAO();
	    sdao.addStudent(stu);
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
	int port = 2021;
	
	try
	{
	  Thread t = new Server(port);
	  t.start();
	}
	
	catch(IOException ex)
	{
	  ex.printStackTrace();
	}
  }
}
