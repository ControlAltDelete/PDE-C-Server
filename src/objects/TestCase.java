package objects;

import java.io.File;

public class TestCase {
	
	/**
	 * @param tci Test Case Input
	 * @param tco Test Case Output
	 */
	public TestCase(File tci, File tco) {
		this.tci = tci;
		this.tco = tco;
	}

	private File tci;
	private File tco;
	
	/**
	 * @return the test case input
	 */
	public File getTci() {
		return tci;
	}
	
	/**
	 * @param tci the test case input to set
	 */
	public void setTci(File tci) {
		this.tci = tci;
	}
	
	/**
	 * @return the test case output
	 */
	public File getTco() {
		return tco;
	}
	
	/**
	 * @param tco the test case output to set
	 */
	public void setTco(File tco) {
		this.tco = tco;
	}
		
	/**
	 * @return the test case input
	 */
	public String getTciAbsolutePath() {
		return tci.getAbsolutePath();
	}
	
	/**
	 * @return the test case output
	 */
	public String getTcoAbsolutePath() {
		return tco.getAbsolutePath();
	}
	
}
