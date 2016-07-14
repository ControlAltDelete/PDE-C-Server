package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

import com.cbrc.ast.utils.StudentListRecoveryUtility;
import com.cbrc.db.utils.DerbyUtils;
import com.cbrc.gdt.builder.CASTCodeAnnotator;
import com.cbrc.gdt.builder.CASTGDTBuilder;
import com.cbrc.gdt.builder.CASTGDTBuilderImperfectMatcher;
import com.cbrc.gdt.builder.CASTGDTStudentTracker;
import com.cbrc.nodes.TranslationUnitNode;
import com.cbrc.nodes.gdt.PlanGDTNode;
import com.cbrc.temp.Driver;

import database.dao.ActivityDAO;
import database.objects.Activity;

public class CBRCIntegration 
{
	public final static String MENU_EXIT = "1";
	public final static String MENU_REG_STUDENT = "2";
	public final static String MENU_SUBMIT_CODE = "3";
	public final static String MENU_ASK_HELP = "4";
	public static final String MENU_PRINT_GDT = "5";
	public static final String MENU_ADD_CASE = "6";
	public static final String SCALE_EXP = "7";
	
	public static final String DEFAULT_PATH = "C:\\SampleCodes\\";
	
	public void runCBRC(String activityName, Path filePath)
	{
		
		try 
		{
			String path = "";
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try 
			{
				Boolean valid = false;
				System.out.println("Please define goal/problem.\n");
				String goalName = activityName;
				
				String goalDescript = activityName + " description.";
				
				int newGoalKey = DerbyUtils.addNewGoal(goalName, goalDescript);
				
				CASTGDTStudentTracker students = new CASTGDTStudentTracker();
				CASTGDTBuilder builder = new CASTGDTBuilder(newGoalKey, goalDescript);
				
				builder.setDebug(true);
				System.out.println("Problem defined!\n");
				
//				valid = false;
//				String recover = null;
//				while (valid != true) {
//					System.out.println("Would you like to recover student list? Y/N: ");
//					recover = Driver.getInput(br);
//					if (recover.equals("Y") || recover.equals("N")) valid = true;
//					else System.out.println("Invalid input!\n");
//				}
//				
//				if (recover.equals("Y")) {
//					StudentListRecoveryUtility slru =  new StudentListRecoveryUtility();
//					System.out.println("Input goalID: ");
//					String goalID = Driver.getInput(br);
//					students = slru.recoverStudents(Integer.parseInt(goalID), newGoalKey);
//				}
				
				
//				System.out.println("Input base path (null if default): ");
//				String tempPath = Driver.getInput(br);
//				
//				if (tempPath.equals("null")) path = DEFAULT_PATH;
//				else path = tempPath;
//				
//				System.out.println("Input filename of first solution: ");
//				String firstSolutionFileName = Driver.getInput(br);
				
				File file1 = new File(filePath.toString());
				CASTCodeAnnotator codeAnnotator = new CASTCodeAnnotator(file1);
				codeAnnotator.annotateCode();
				builder.processFirstCode(codeAnnotator.getHeadNode(), "");
				System.out.println("First Solution Added!\n");
				
				ArrayList<File> tci = new ArrayList<File>();
				ArrayList<File> tco = new ArrayList<File>();
				File tc;
				
				String testCaseInputFileName = "";
				while (!testCaseInputFileName.equals("null")) {
					System.out.println("Input filename of test case input: (input null if no more test case inputs) ");
					testCaseInputFileName = Driver.getInput(br);

					if (!testCaseInputFileName.equals("null")) {
						tc = new File(path + "TestCases\\" + testCaseInputFileName);
						tci.add(tc);
					}
				}
				
				String testCaseOutputFileName = "";
				while (!testCaseOutputFileName.equals("null")) {
					System.out.println("Input filename of test case expected output: (input null if no more test case inputs) ");
					testCaseOutputFileName = Driver.getInput(br);

					if (!testCaseOutputFileName.equals("null")) {
						tc = new File(path + "TestCases\\" + testCaseOutputFileName);
						tco.add(tc);
					}
				}
				
				
				Driver.printGDT(builder, path);
				
				String menuInput = "";
				while (!menuInput.equals(MENU_EXIT)) {
					menuInput = Driver.getMenuInput(br);
					
					if (menuInput.equals(MENU_REG_STUDENT)) {
						try {
							Driver.registerNewStudent(br, students, builder.getSuperGoal().getDBID());
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					else if (menuInput.equals(MENU_SUBMIT_CODE)) {
						try {
						    Driver.submitNewCode(br, students, builder, path, tci, tco);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if (menuInput.equals(MENU_ASK_HELP)) {
						try {
							Driver.askForHelp(br, students, builder, path);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if (menuInput.equals(MENU_EXIT)) {
						System.out.println("\n\nExiting System.");
					}
					else if (menuInput.equals(MENU_PRINT_GDT)) {
						Driver.printGDT(builder, path);
					}
					else if (menuInput.equals(MENU_ADD_CASE)) {
						Driver.addCase(br, builder, path);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				br.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String feedback0(){
		Random r = new Random();
		int randNum = r.nextInt(4);
		/*
		String[] feedbacks = {"Feedback Level: 0\n"
				+ "\n"
				+ "Here is the solution to further assist you in your programming issue:\n\n"
				+ "#include <stdio.h>\n"
				+ "int main()\n"
				+ "{\n"
				+ "\tint x, y, z;\n"
				+ "\tx = 4;\n"
				+ "\ty = 1;\n"
				+ "\tz = x * y;\n"
				+ "\n"
				+ "\twhile (z <= 496)\n"
				+ "\t{\n"
				+ "\t\tprintf(\"%d \", z);\n"
				+ "\t\ty++;\n"
				+ "\t\tz = x*y;\n"
				+ "\t}\n"
				+ "\tgetchar();\n"
				+ "\treturn 0;\n"
				+ "}\n",
				"Feedback Level: 1"
				+ "\n"
				+ "Remove the error found by the compiler as indicated above.",
				"Feedback Level: 2"
				+ "\n"
				+ "The getch() method in your code is not recognized by the compiler, or is missing a required header to be able to find the desired method to be executed.",
				"Feedback Level: 3"
				+ "\n"
				+ "The only way to solve this issue is to remove getch(); in your code in line 16, or replace it with a better alternative such as getchar();."};
		*/
		String[] feedbacks = {"Feedback Level: 1\n"
				+ "\n"
				+ "Wrong code. Can you identify the error?",
				"Feedback Level: 2\n"
				+ "\n"
				+ "These are the parts of your code that are potentially wrong:\n\n"
				+ "getch()",
				"Feedback Level: 3\n"
				+ "The correct solution has the following pattern but your code does not:\n\n"
				+ "- getch()",
				"Feedback Level: 4\n"
				+ "This is the correct code: \n\n"
				+ "#include <stdio.h>\n"
				+ "int main()\n"
				+ "{\n"
				+ "\tint x, y, z;\n"
				+ "\tx = 4;\n"
				+ "\ty = 1;\n"
				+ "\tz = x * y;\n"
				+ "\n"
				+ "\twhile (z <= 496)\n"
				+ "\t{\n"
				+ "\t\tprintf(\"%d \", z);\n"
				+ "\t\ty++;\n"
				+ "\t\tz = x*y;\n"
				+ "\t}\n"
				+ "\tgetchar();\n"
				+ "\treturn 0;\n"
				+ "}\n"};
		return feedbacks[randNum];
	}
}
