import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Panel2 extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Panel2() {
		setLayout(null);
		
		JLabel lblSample = new JLabel("sample2");
		lblSample.setBounds(10, 8, 39, 14);
		add(lblSample);
		
		textField = new JTextField();
		textField.setBounds(59, 5, 98, 20);
		add(textField);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(100, 97, 109, 23);
		add(rdbtnNewRadioButton);

	}
}
