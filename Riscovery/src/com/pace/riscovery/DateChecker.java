package com.pace.riscovery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Calendar;

import javax.swing.JOptionPane;
import com.pace.riscovery.views.DueDates;

public class DateChecker {
	
	private static Date expiryDate; //= new Date();	
	public static String inputDate; //this is the String object that receives the date from the database.
	
	public void go() {
		// TODO Auto-generated method stub
//		DBHelper.getInstance().init();
		
		List<Motor> motorList = MotorHelper.getInstance().getMotors();
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
				
		for(int i=0; ;i++){
			if(i == motorList.size())
			//	i = 0;	
				break;
			try {
					inputDate = ((Motor) motorList.get(i)).getExpiryDate();
					expiryDate = new SimpleDateFormat("dd/MM/yy").parse(inputDate);
			} catch (IndexOutOfBoundsException ex){
				JOptionPane.showMessageDialog(null,
						"Database is empty");
				//if database is empty break out of the loop
				break;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e){
				System.out.println("Empty cell");  //use an option pane later.
				break;
			}			
			
			Date todayDate = new Date();						
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");					
			System.out.println("new = " + dateFormatter.format(expiryDate));
//			System.out.println("today = " + todayDate);
			
			if(expiryDate.before(todayDate)){
//				System.out.println("Time is up!");						
				// Pop up code
				JOptionPane.showMessageDialog(null,
					"PLEASE NOTE: Policy belonging to " +
					((Motor) motorList.get(i)).getInsured() + " has expired");		
				// add it to DueDates class
				DueDates.model.addElement(((Motor) motorList.get(i)).getInsured());
				// remove element from the list
				// motorList.remove(i);		//Removing element causes the loop to jump consecutive date in the list.		
			}
			
			//add code for 2 months 1 month & 2 weeks, 1 week before expiry date.
			//2months before
			Calendar twoMonths = Calendar.getInstance();
			twoMonths.setTime(expiryDate);
			twoMonths.add(Calendar.MONTH, -2);
			
			//1month before
			Calendar oneMonth = Calendar.getInstance();
			oneMonth.setTime(expiryDate);
			oneMonth.add(Calendar.MONTH, -1);
			
			//2weeks before
			Calendar twoWeeks = Calendar.getInstance();
			twoWeeks.setTime(expiryDate);
			twoWeeks.add(Calendar.WEEK_OF_YEAR, -2);
			
			//1week before
			Calendar oneWeek = Calendar.getInstance();
			oneWeek.setTime(expiryDate);
			oneWeek.add(Calendar.WEEK_OF_YEAR, -1);
			
			//1, 2 and 3 days before
			Calendar oneDay,twoDays, threeDays;
			oneDay = Calendar.getInstance();
			twoDays = Calendar.getInstance();
			threeDays = Calendar.getInstance();
			
			oneDay.setTime(expiryDate);
			twoDays.setTime(expiryDate);
			threeDays.setTime(expiryDate);
			
			oneDay.add(Calendar.DAY_OF_YEAR, -1);
			twoDays.add(Calendar.DAY_OF_YEAR, -2);
			threeDays.add(Calendar.DAY_OF_YEAR, -3);
			
			if(todayDate.after(twoMonths.getTime()) && todayDate.before(oneMonth.getTime())){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due in 2 months");
				
				Notification.getInstance().twoMonthsDisplay();
			}
			if(todayDate.after(oneMonth.getTime()) && todayDate.before(twoWeeks.getTime())){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due in a month");	
				Notification.getInstance().oneMonthDisplay();
			}
			if(todayDate.after(twoWeeks.getTime()) && todayDate.before(oneWeek.getTime())){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due in 2 weeks");
				Notification.getInstance().twoWeeksDisplay();
			}
			if(todayDate.after(oneWeek.getTime()) && todayDate.before(threeDays.getTime())){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due in 1 week");
				Notification.getInstance().oneWeekDisplay();
			}
			if(todayDate.after(threeDays.getTime()) && todayDate.before(twoDays.getTime())){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due in 3 days");
				Notification.getInstance().threeDaysDisplay();
			}
			if(todayDate.after(twoDays.getTime()) && todayDate.before(oneDay.getTime())){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due in 2 days");
				Notification.getInstance().twoDaysDisplay();
			}
			if(todayDate.after(oneDay.getTime()) && todayDate.before(expiryDate)){
				JOptionPane.showMessageDialog(null,
						"PLEASE NOTE: Policy belonging to " +
						((Motor) motorList.get(i)).getInsured() + " will be due tomorrow");	
				Notification.getInstance().oneDayDisplay();
			}			

			System.out.println("twoMonths" + twoMonths.getTime());
			System.out.println("oneMonth " + oneMonth.getTime());
			System.out.println("twoWeeks " + twoWeeks.getTime());
			System.out.println("oneWeek " + oneWeek.getTime());
			System.out.println("threeDays " + threeDays.getTime());
			System.out.println("twoDays " + twoDays.getTime());
			System.out.println("oneDay " + oneDay.getTime());
			System.out.println("\n\n");
			}
	}

}
