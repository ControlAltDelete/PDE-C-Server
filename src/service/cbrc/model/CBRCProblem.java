package service.cbrc.model;

import java.nio.file.Path;
import java.util.ArrayList;

public class CBRCProblem 
{

	private String problemName;
	private String problemDesc;
	private Path firstSolution;
	private ArrayList<TestCase> tc;

	/**
	 * Creates a blank representation for <code>CBRCProblem</code> model.
	 */
	public CBRCProblem()
	{
		
	}
	
	/**
	 * Creates a representation that contains the information for <code>CBRCProblem</code> model.
	 * @param name The Problem Name
	 * @param desc The Problem Description
	 * @param fs The First Solution to the problem
	 * @param testcases The <code>TestCase</code>(s)
	 */
	public CBRCProblem(String name, String desc, Path fs, ArrayList<TestCase> testcases)
	{
		this.problemName = name;
		this.problemDesc = desc;
		this.firstSolution = fs;
		this.tc = testcases;
	}
	
	/**
	 * Gets the <code>problemName</code> property.
	 * @return the Problem Name
	 */
	public String getProblemName() 
	{
		return problemName;
	}
	/**
	 * Sets the <code>problemName</code> to its preferred value.
	 * @param problemName the Problem Name to set
	 */
	public void setProblemName(String problemName) 
	{
		this.problemName = problemName;
	}
	/**
	 * Gets the <code>problemDesc</code> property.
	 * @return the Problem Description
	 */
	public String getProblemDesc() 
	{
		return problemDesc;
	}
	/**
	 * Sets the <code>problemDesc</code> to its preferred value.
	 * @param problemDesc the Problem Description to set
	 */
	public void setProblemDesc(String problemDesc) 
	{
		this.problemDesc = problemDesc;
	}
	/**
	 * Gets the <code>firstSolution</code> property.
	 * @return the First Solution File
	 */
	public Path getFirstSolution() 
	{
		return firstSolution;
	}
	/**
	 * Sets the <code>firstSolution</code> to its preferred value.
	 * @param firstSolution the First Solution File to set
	 */
	public void setFirstSolution(Path firstSolution) 
	{
		this.firstSolution = firstSolution;
	}
	/**
	 * Gets the <code>tc</code> property.
	 * @return the test cases in the problem
	 */
	public ArrayList<TestCase> getTc() {
		return tc;
	}
	/**
	 * Sets the <code>tc</code> to its preferred value.
	 * @param tc the tc to set
	 */
	public void setTc(ArrayList<TestCase> tc) {
		this.tc = tc;
	}

}
