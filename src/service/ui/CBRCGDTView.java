package service.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.cbrc.temp.Driver;

import controller.fileops.FileSave;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class CBRCGDTView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CBRCGDTView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CBRCGDTView() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel pnlGDTForm = new JPanel();
		frame.getContentPane().add(pnlGDTForm, BorderLayout.CENTER);
		pnlGDTForm.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtGDTForm = new JTextArea();
		txtGDTForm.setEditable(false);
		pnlGDTForm.add(txtGDTForm, BorderLayout.CENTER);
		
		JPanel pnlController = new JPanel();
		frame.getContentPane().add(pnlController, BorderLayout.SOUTH);
		pnlController.setLayout(new BorderLayout(0, 0));
		
		JButton btnExportGdt = new JButton("Export GDT");
		btnExportGdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				FileNameExtensionFilter gdtFilter = new FileNameExtensionFilter(
						"Text Files (.txt)", "txt");
				jfc.setFileFilter(gdtFilter);
				int res = jfc.showSaveDialog(frame);
				if(res == JFileChooser.APPROVE_OPTION)
				{
					Path path = Paths.get(jfc.getSelectedFile().getAbsolutePath());
					FileSave fs = new FileSave();
					fs.writeFile(path, txtGDTForm.toString());
					System.out.println("Saved GDT to " + path.toString());
				}
			}
		});
		pnlController.add(btnExportGdt, BorderLayout.WEST);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		pnlController.add(btnClose, BorderLayout.EAST);
		
		JPanel pnlControllerCtr = new JPanel();
		FlowLayout fl_pnlControllerCtr = (FlowLayout) pnlControllerCtr.getLayout();
		fl_pnlControllerCtr.setVgap(0);
		fl_pnlControllerCtr.setHgap(0);
		pnlController.add(pnlControllerCtr, BorderLayout.CENTER);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				StringBuilder sb = Driver.showGDT(cgdt);
//				txtGDTForm.setText(sb.toString());
			}
		});
		pnlControllerCtr.add(btnRefresh);
		String currentOS = System.getProperty("os.name").toLowerCase();
	  	if (currentOS.indexOf("win") >= 0)
	  	{
	  		txtGDTForm.setFont(new Font("Consolas", Font.PLAIN, 13));
	  	}
	  	else if (currentOS.indexOf("mac") >= 0)
	  	{
	  		txtGDTForm.setFont(new Font("Monaco", Font.PLAIN, 13));
	  	}
	}

}
