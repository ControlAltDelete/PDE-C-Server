package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import database.DatabaseFactory;
import service.ServerHandler;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;

/**
 * The popup frame of MySQL Login.
 * <p>
 *  This frame will be used to login using your MySQL settings.
 * </p>
 * 
 * @author In Yong S. Lee
 */
public class SimpleLogin {

	private JFrame frmLogin;
	private JTextField txtLogin;
	private JPasswordField txtPassword;

	/**
	 * Create the frame <code>SimpleLogin</code>.
	 */
	public SimpleLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try
		{
            // Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) 
		{
	       // handle exception
	    }
	    catch (ClassNotFoundException e) 
		{
	       // handle exception
	    }
	    catch (InstantiationException e) 
		{
	       // handle exception
	    }
	    catch (IllegalAccessException e) 
		{
	       // handle exception
	    }
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 428, 128);
		frmLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent arg0) 
			{
			  if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
			  {
					JTextField log = txtLogin;
					JPasswordField pwd = txtPassword;
					String strPwd = new String(pwd.getPassword());
					if(log.getText().trim().length() == 0 || strPwd.length() == 0)
					{
						JOptionPane.showMessageDialog(null, "Please fill in the required details to continue.", "Error", JOptionPane.ERROR_MESSAGE);
						if(log.getText().trim().length() == 0)
						{
							log.setBackground(Color.RED);
						}
						else
						{
							log.setBackground(Color.WHITE);
						}
						if(strPwd.length() == 0)
						{
							pwd.setBackground(Color.RED);
						}
						else
						{
							pwd.setBackground(Color.WHITE);
						}
					}
					else
					{
						Main.u = txtLogin.getText().trim();
						Main.p = strPwd;
						frmLogin.dispose();
						ServerHandler sHandler = new ServerHandler();
						sHandler.runServer();
						DatabaseFactory.getInstance(Main.u, Main.p);
						Main.getInstance().initialize();
					}
			  }
			}
		});
		
		txtLogin = new JTextField();
		txtLogin.setBounds(167, 11, 245, 20);
		frmLogin.getContentPane().add(txtLogin);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(347, 67, 65, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTextField log = txtLogin;
				JPasswordField pwd = txtPassword;
				String strPwd = new String(pwd.getPassword());
				if(log.getText().trim().length() == 0 || strPwd.length() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please fill in the required details to continue.", "Error", JOptionPane.ERROR_MESSAGE);
					if(log.getText().trim().length() == 0)
					{
						log.setBackground(Color.RED);
					}
					else
					{
						log.setBackground(Color.WHITE);
					}
					if(strPwd.length() == 0)
					{
						pwd.setBackground(Color.RED);
					}
					else
					{
						pwd.setBackground(Color.WHITE);
					}
				}
				else
				{
					Main.u = txtLogin.getText().trim();
					Main.p = strPwd;
					frmLogin.dispose();
					ServerHandler sHandler = new ServerHandler();
					sHandler.runServer();
					DatabaseFactory.getInstance(Main.u, Main.p);
					Main.getInstance().initialize();
				}
			}
		});
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(167, 36, 245, 20);
		frmLogin.getContentPane().add(txtPassword);
		
		JPanel labelPane = new JPanel();
		labelPane.setBounds(10, 11, 147, 45);
		frmLogin.getContentPane().add(labelPane);
		labelPane.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblLogin = new JLabel("MySQL User Name:");
		labelPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("MySQL Password:");
		labelPane.add(lblPassword);
		frmLogin.getContentPane().add(btnSubmit);
		frmLogin.getRootPane().setDefaultButton(btnSubmit);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setVisible(true);
	}

}
