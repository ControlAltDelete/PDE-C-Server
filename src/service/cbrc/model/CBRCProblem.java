package service.cbrc.model;

import java.nio.file.Path;
import java.util.ArrayList;

public class CBRCProblem 
{

	private String problemName;
	private String problemDesc;
	private Path firstSolution;
	private ArrayList<TestCase> tc;
	
	public CBRCProblem()
	{
		
	}
	
	public CBRCProblem(String name, String desc, Path fs, ArrayList<TestCase> testcases)
	{
		this.problemName = name;
		this.problemDesc = desc;
		this.firstSolution = fs;
		this.tc = testcases;
	}
	
	/**
	 * @return the Problem Name
	 */
	public String getProblemName() 
	{
		return problemName;
	}
	/**
	 * @param problemName the Problem Name to set
	 */
	public void setProblemName(String problemName) 
	{
		this.problemName = problemName;
	}
	/**
	 * @return the Problem Description
	 */
	public String getProblemDesc() 
	{
		return problemDesc;
	}
	/**
	 * @param problemDesc the Problem Description to set
	 */
	public void setProblemDesc(String problemDesc) 
	{
		this.problemDesc = problemDesc;
	}
	/**
	 * @return the First Solution File
	 */
	public Path getFirstSolution() 
	{
		return firstSolution;
	}
	/**
	 * @param firstSolution the First Solution File to set
	 */
	public void setFirstSolution(Path firstSolution) 
	{
		this.firstSolution = firstSolution;
	}
	/**
	 * @return the test cases in the problem
	 */
	public ArrayList<TestCase> getTc() {
		return tc;
	}
	/**
	 * @param tc the tc to set
	 */
	public void setTc(ArrayList<TestCase> tc) {
		this.tc = tc;
	}

}
