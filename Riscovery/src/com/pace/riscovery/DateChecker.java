package com.pace.riscovery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.pace.riscovery.views.DueDates;

public class DateChecker {
	
	private static Date newDate; //= new Date();
	
	public static String inputDate; //this is the String object that receives the date from the database.
	
	public void go() {
		// TODO Auto-generated method stub
//		DBHelper.getInstance().init();
		
		List<Motor> motorList = MotorHelper.getInstance().getMotors();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
				
		for(int i=0; ;i++){
			if(i == motorList.size())
				i = 0;
			try {
					inputDate = ((Motor) motorList.get(i)).getExpiryDate();
					newDate = new SimpleDateFormat("dd/MM/yy").parse(inputDate);
			} catch (IndexOutOfBoundsException ex){
				JOptionPane.showMessageDialog(null,
						"Database is empty");
				//if database is empty break out of the loop
				break;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Date todayDate = new Date();						
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");					
			System.out.println("new = " + dateFormatter.format(newDate));
			System.out.println("today = " + todayDate);
			
			if(newDate.before(todayDate)){
//				System.out.println("Time is up!");						
				// Pop up code
				JOptionPane.showMessageDialog(null,
					"PLEASE NOTE: Policy belonging to " +
					((Motor) motorList.get(i)).getInsured() + " has expired");		
				// add it to DueDates class
				DueDates.model.addElement(((Motor) motorList.get(i)).getInsured());
				// remove element from the list
				motorList.remove(i);
			
				
			}
		}
	}

}
