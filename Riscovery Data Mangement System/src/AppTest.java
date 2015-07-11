import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainFrame;


public class AppTest {

	public static void main(String[] args) {
		// Windows Default look and feel. This affects all classes linked to the application.
				try {
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}
				
		MainFrame frame = new MainFrame();
		
	}

}
