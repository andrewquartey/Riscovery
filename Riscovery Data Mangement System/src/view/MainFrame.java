package view;
/*
 * This is the main UI frame for the app
 */
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {
	
	final private JSplitPane split = new JSplitPane();
	final private JMenuBar menuBar = new JMenuBar();
	
	public MainFrame(){
		initComponents();
		initMenuBar();
	}
	
	public void initComponents(){
		split.setLeftComponent(new ListPanel());
		split.setRightComponent(new RightPanel());
		
		add(split);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(300,300);		
	}
	
	public void initMenuBar(){
		setJMenuBar(menuBar);
		JMenu file = menuBar.add(new JMenu("File"));
		JMenu window = menuBar.add(new JMenu("Window"));
		JMenu help = menuBar.add(new JMenu("Help"));
				
	}

}
