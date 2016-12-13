package service.cbrc.model;

import java.nio.file.Path;

/**
 * Handles the encapsulation and representation of <code>TestCase</code> model.
 * 
 * @author In Yong S. Lee
 */
public class TestCase 
{
	
	private Path tci;
	private Path tco;
	
	/**
	 * Creates a representation that contains the information for <code>TestCase</code> model.
	 * @param tci Test Case Input
	 * @param tco Test Case Output
	 */
	public TestCase(Path tci, Path tco) {
		this.tci = tci;
		this.tco = tco;
	}
	
	/**
	 * Gets the <code>tci</code> property.
	 * @return the Test Case Input
	 */
	public Path getTci() {
		return tci;
	}
	/**
	 * Sets the <code>tci</code> to its preferred value.
	 * @param tci the Test Case Input to set
	 */
	public void setTci(Path tci) {
		this.tci = tci;
	}
	/**
	 * Gets the <code>tco</code> property.
	 * @return the Test Case Output
	 */
	public Path getTco() {
		return tco;
	}
	/**
	 * Sets the <code>tco</code> to its preferred value.
	 * @param tco the Test Case Output to set
	 */
	public void setTco(Path tco) {
		this.tco = tco;
	}
	
}
