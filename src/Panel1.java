import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Panel1 extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Panel1() {
		setLayout(null);
		
		
		JLabel lblSample = new JLabel("sample1");
		lblSample.setBounds(197, 71, 39, 14);
		add(lblSample);
		
		textField = new JTextField();
		textField.setBounds(43, 184, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		

	}

}
