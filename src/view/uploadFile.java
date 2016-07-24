package view;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

public class uploadFile extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
    JFileChooser fc;
    JButton b, b1;
    JTextField tf;
    FileInputStream in;
    Socket s;
    DataOutputStream dout;
    DataInputStream din;
    int i;
    File pdf;
	/**
	 * Create the panel.
	 */
	public uploadFile() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(129, 50, 116, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Activity Name");
		lblNewLabel.setBounds(39, 53, 80, 14);
		add(lblNewLabel);
		
		JButton btnChooseFile = new JButton("Choose File");
		btnChooseFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    try {
				        if (e.getSource() == b) {
				            int x = fc.showOpenDialog(null);
				            if (x == JFileChooser.APPROVE_OPTION) {
				                pdf = fc.getSelectedFile();
				                tf.setText(pdf.getAbsolutePath());
				                b1.setEnabled(true);
				            } else {
				                pdf = null;
				                tf.setText(null);
				                b1.setEnabled(false);
				            }
				        }
				      //  if (e.getSource() == b1) {
				          //  send();
				       // }
				    } catch (Exception ex) {
				    }
				}
		});

		btnChooseFile.setBounds(129, 81, 116, 23);
		add(btnChooseFile);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 23, 116, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblActivityNo = new JLabel("Activity No.");
		lblActivityNo.setBounds(39, 28, 80, 14);
		add(lblActivityNo);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(129, 106, 116, 23);
		add(btnSend);

	}
}
