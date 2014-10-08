package com.pace.riscovery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateChecker {

	private static Date todayDate = new Date();
	private static Date newDate = new Date();
	
	public static String inputDate; //this is the String object that receives the date from the database.
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBHelper.getInstance().init();
		
		List motorList = MotorHelper.getInstance().getMotors();
		
		for(int i=0;;i++){
			if(i == motorList.size())
				i = 0;
			inputDate = ((Motor) motorList.get(i)).getExpiryDate();			
			
			try {
				newDate = new SimpleDateFormat("dd/MM/yy").parse(inputDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					
			System.out.println(dateFormatter.format(newDate));
//			System.out.println(dateFormatter.format(todayDate));
			
			if(newDate.before(todayDate)){
//				System.out.println("Time is up!");
			}
		}
		
		
		
		

	}

}
