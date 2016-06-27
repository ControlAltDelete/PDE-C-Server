package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.dao.ActivityDAO;
import database.dao.DeliverableDAO;
import database.dao.StudentDAO;
import database.objects.Activity;
import database.objects.Deliverable;
import database.objects.Student;
import service.FileDecoder;

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
	    String type = clientSentence.substring(0, clientSentence.indexOf(","));
	    clientSentence = clientSentence.substring(clientSentence.indexOf(",") + 1);
		
		if(type.equals("activity"))
		{
			while(clientSentence.indexOf(",") != -1)
		    {
				String temp = clientSentence.substring(0, clientSentence.indexOf(","));
				info.add(temp);
				clientSentence = clientSentence.substring(clientSentence.indexOf(",") + 1);
			}
		    
		    info.add(clientSentence);
		    clientSentence = "";
		    FileDecoder fd = new FileDecoder();
		    //fd.convertToFile(info.get(3), info.get(5));
		    //Activity act = new Activity(Integer.parseInt(info.get(0)), info.get(1), info.get(2), new Timestamp(System.currentTimeMillis()), info.get(4), info.get(5));
		    ActivityDAO adao = new ActivityDAO();
		    //adao.addActivity(act);
		}
		else if(type.equals("deliverable"))
		{
			while(clientSentence.indexOf(",") != -1)
		    {
				String temp = clientSentence.substring(0, clientSentence.indexOf(","));
				info.add(temp);
				clientSentence = clientSentence.substring(clientSentence.indexOf(",") + 1);
			}
		    
		    info.add(clientSentence);
		    clientSentence = "";
		    FileDecoder fd = new FileDecoder();
		    fd.convertToFile(info.get(3), info.get(5));
		    //Deliverable del = new Deliverable(Integer.parseInt(info.get(0)), Integer.parseInt(info.get(1)), Integer.parseInt(info.get(2)), new File(System.getProperty("user.dir")+"/src/"+info.get(3)), new Timestamp(System.currentTimeMillis()), info.get(5), Float.parseFloat(info.get(6)));
		    DeliverableDAO ddao = new DeliverableDAO();
		    //ddao.addDeliverable(del);
		}
		else
		{
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
		}
		
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
