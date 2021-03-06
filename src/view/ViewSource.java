package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridBagLayout;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.GridBagConstraints;

/**
 * The popup frame of <code>ViewSource</code>.
 * 
 * <p>
 *  Contains the source code given the selected deliverable made by the student in the given activity.
 * </p>
 * 
 * @author In Yong S. Lee
 */
public class ViewSource {

	private JFrame frame;
	private RSyntaxTextArea editorPane;
	
	/**
	 * Create the frame <code>ViewSource</code>.
	 */
	public ViewSource() {
		initialize();
	}

	/**
	 * Sets the <code>editorPane</code> to its respective contents of the source code.
	 * @param text the source code contents.
	 */
	public void setTextArea(StringBuilder text) {
		editorPane.setText(text.toString());
	}
	
	/**
	 * Sets the title to its respective title.
	 * @param title the <code>frame</code> <code>title</code> bar to set
	 */
	public void setTitle(String title) {
		frame.setTitle(title);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		editorPane = new RSyntaxTextArea();
		editorPane.setEditable(false);
		editorPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
		editorPane.setCodeFoldingEnabled(true);
		editorPane.setFont(new Font(editorPane.getFont().getFamily(), Font.PLAIN, 16));
		RTextScrollPane scrollPane = new RTextScrollPane(editorPane);
		Font monospace = new Font(editorPane.getFont().getFamily(), Font.PLAIN, 16);
		Gutter gut = scrollPane.getGutter();
		for(int i = 0; i < gut.getComponentCount(); i++)
		{
			gut.getComponent(i).setFont(monospace);
		}
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
	}

}
