package service.cbrc.model;

import java.nio.file.Path;

public class TestCase 
{
	
	private Path tci;
	private Path tco;
	
	/**
	 * @param tci Test Case Input
	 * @param tco Test Case Output
	 */
	public TestCase(Path tci, Path tco) {
		this.tci = tci;
		this.tco = tco;
	}
	
	/**
	 * @return the Test Case Input
	 */
	public Path getTci() {
		return tci;
	}
	/**
	 * @param tci the Test Case Input to set
	 */
	public void setTci(Path tci) {
		this.tci = tci;
	}
	/**
	 * @return the Test Case Output
	 */
	public Path getTco() {
		return tco;
	}
	/**
	 * @param tco the Test Case Output to set
	 */
	public void setTco(Path tco) {
		this.tco = tco;
	}
	
}
