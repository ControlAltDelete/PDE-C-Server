package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import database.dao.ActivityDAO;
import database.dao.DeliverableDAO;
import database.dao.StudentDAO;
import database.objects.Activity;
import database.objects.Deliverable;
import database.objects.Student;
import service.CBRCIntegration;
import service.FileDecoder;
import service.FileManipulation;
import service.controller.CBRCControls;
import service.ui.CBRCMenu;
import view.Main;

public class Server implements Runnable
{
  public static final int PORT_NO = 2021;
  public final CBRCControls cbrctrls = new CBRCControls();
  private ServerSocket serverSocket;
  private DataOutputStream writer;
  
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
//		CBRCIntegration cbr = new CBRCIntegration();
//		Path filePath = Paths.get("C:\\SampleCodes\\test.c");
//		cbr.runCBRC("yo", filePath);
		System.out.println("Waiting for client ");
		checkIfFoldersExists();
		Socket server = serverSocket.accept();
		System.out.println("Just connected to " + server.getRemoteSocketAddress());
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(server.getInputStream()));
		writer = new DataOutputStream(server.getOutputStream());
	 
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
		    fd.convertToFile(info.get(2), info.get(5));
//		    Activity act = new Activity(Integer.parseInt(info.get(0)), info.get(1), new File(System.getProperty("user.dir")+"/src/"+info.get(5)), 
//		    		new Timestamp(System.currentTimeMillis()), new Date(System.currentTimeMillis()), info.get(5));
//		    ActivityDAO adao = new ActivityDAO();
//		    adao.addActivity(act);
		}
		
		else if(type.equals("get"))
		{
		  if (clientSentence.equals("Activity"))
		  {
			System.out.println("A");
			this.sendActivity();
		  }
		  
		  else if (clientSentence.contains("ActivityFiles"))
		  {
			int idNum = Integer.parseInt((clientSentence.substring(clientSentence.lastIndexOf(",") + 1).trim()));
			
			this.downloadActivity(idNum);
		  }
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
		    
		    if (!Files.exists(Paths.get(System.getProperty("user.dir")+"/resource/receivedFiles/")))
		    {
		      System.out.println("hey");
		      Files.createDirectories(Paths.get(System.getProperty("user.dir")+"/resource/receivedFiles/"));
		    }
		    
		    Deliverable del = new Deliverable(Integer.parseInt(info.get(0)), Integer.parseInt(info.get(1)), 
		    		Integer.parseInt(info.get(2)), new File(System.getProperty("user.dir")+"/resource/receivedFiles/"+info.get(5)), 
		    		new Timestamp(System.currentTimeMillis()), info.get(5), Float.parseFloat(info.get(6)));
		    DeliverableDAO ddao = new DeliverableDAO();
		    ddao.addDeliverable(del);
		    if(Main.getInstance().isCBRCStatus())
		    {
		    	CBRCMenu cbrc = CBRCMenu.getInstance();
		    	if(cbrc.isFeedOnGoing())
		    	{
			    	String feedback = cbrctrls.feedSourceCode(cbrc.getStudents(), cbrc.getBuilder(), Paths.get(del.getDeliverableSourceCode().toURI()), cbrc.getProb().getTc(), del.getStudentID());
				    downloadFeedback(feedback);
		    	}
		    }
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
  
  private void checkIfFoldersExists()
  {
	if (!Files.exists(Paths.get("resource/receivedFiles/")))
    {
      System.out.println("hey");
      
      try
	  {
		Files.createDirectories(Paths.get("resource/receivedFiles/"));
	  } 
      catch (IOException e)
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    }
	
	if (!Files.exists(Paths.get("resource/activityFile/")))
    {
      System.out.println("hey");
      
      try
	  {
		Files.createDirectories(Paths.get("resource/activityFile/"));
	  } 
      catch (IOException e)
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
    }
  }

  private void sendActivity() throws SQLException
  {
    FileManipulation fm = new FileManipulation();
	ActivityDAO adao = new ActivityDAO();
		
	ArrayList<String> activityNames = adao.getActivityNames();
	
	File activityFile;
	activityFile = new File("resource/activityFile/activityEntries.txt");

		// file streamer here
	try
	{
	  FileWriter fw;
	  fw = new FileWriter(activityFile);
	  BufferedWriter out = new BufferedWriter(fw);
	  
	  for(int s = 0; s < activityNames.size(); s++)
	  {
		out.write(activityNames.get(s) + "\n");
	  }
			
	  out.flush();
   	  out.close();
	}
	
	catch(IOException io)
	{
	  io.printStackTrace();
	}
		
	try
	{
		 
	  writer.writeUTF(fm.convertToBinary(activityFile));
	  writer.flush();
	}
		
	catch (Exception ex)
	{
	  ex.printStackTrace();
	}
  }
  
  private void downloadFeedback(String content) throws SQLException, IOException
  {
	try
	{
	  writer.writeBytes(content);
	  writer.flush();
	}
	
	catch (Exception ex)
	{
	  ex.printStackTrace();
	}
	
  }
  
  private void downloadActivity(int activityId) throws SQLException, IOException
  {
	ActivityDAO adao = new ActivityDAO();
	Activity act =  new Activity();
	
	act = adao.getActivity(activityId);
	
	try
	{
	  writer.writeBytes(act.getActivityFile());
	  writer.flush();
	}
	
	catch (Exception ex)
	{
	  ex.printStackTrace();
	}
	
  }
}
