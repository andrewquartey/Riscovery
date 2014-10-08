package com.pace.riscovery;

import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
			
	public static void main(String[] args) throws SQLException{
		
		DBHelper.getInstance().init();
		
		// Windows Default look and feel
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
				Application app = new Application();
				app.setSize(400, 400);
				app.setLocationRelativeTo(null);
				app.setDefaultCloseOperation(Application.EXIT_ON_CLOSE);
				app.setVisible(true);
			}
		});
		
		
		
		
		
		
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
