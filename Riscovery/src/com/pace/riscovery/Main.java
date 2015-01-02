package com.pace.riscovery;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.pace.riscovery.Logic.ApplicationLogic;
import com.pace.riscovery.views.ApplicationView;
import com.pace.riscovery.views.DueDates;

public class Main {
	public static void main(String[] args) throws SQLException{
		
		DBHelper.getInstance().init();		
		// Windows Default look and feel. This affects all classes linked to the application.
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){				
				ApplicationLogic appLogic = new ApplicationLogic();				
				ApplicationView app = new ApplicationView();
				app.setLogic(appLogic);
				appLogic.setView(app);
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				app.setLocationRelativeTo(null);
				app.setVisible(true);
			}
		});
		
		
		SwingWorker<Void, Void> worker = 
				new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						// TODO Auto-generated method stub
//						Runnable n = new NotifyRunnable();
//						Thread myThread = new Thread(n);
//						myThread.start();
						Notification n = new Notification();
						for(;;){
							if(!DueDates.model.isEmpty()){
								n.display();
								Thread.sleep(1000*10); //change this later to 1000*60*60*3
							}
						}						
					}
			
		};
		
		worker.execute();		
//		List<Motor> m = MotorHelper.getInstance().getMotors();
//		DBHelper.getInstance().close();		
//		System.out.println(m);		
//		Connection connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testDB?user=root");
//		Statement stmt =  connect.createStatement();//		
//		stmt.executeQuery("SELECT * FROM Motor");
//		for(Motor m1 : m){
//			System.out.println(m1.getId() + " " + m1.getInsured() + " " +
//						m1.getContact());						
//		}		
//		connect.close();
	}	
}