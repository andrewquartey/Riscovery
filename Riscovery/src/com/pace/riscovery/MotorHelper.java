package com.pace.riscovery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * This is a singleton helper class that creates a list of Motors
 * And stores them in an arrayLIst
 * @author Andy
 *
 */
public class MotorHelper {
	
	private static MotorHelper INSTANCE = new MotorHelper(); // the first thing to do in creating a singleton class 
	
	public static MotorHelper getInstance(){
		return INSTANCE;
	}
	
	private MotorHelper(){    // the 2nd part of creating a singleton class
		
	}
	
	// returns a list of Motor Objects
	public List<Motor> getMotors() {
		final List<Motor> motors = new ArrayList<Motor>();
		
		final String sql = "SELECT * FROM Motor ORDER BY idMotor";
		
		try(Connection conn = DBHelper.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery()) {
			
			while(rs.next()){
				Motor m = new Motor();
				m.setId(rs.getInt("idMotor"));
				m.setInsured(rs.getString("insured"));
				m.setContact(rs.getString("contact"));
//				System.out.println(m.getId());								
				m.setEmail(rs.getString("email"));
				// uncmommeting these statments doesn' produce output in Main why? It was bcos the strings in the parenthesis didnt march the column names in the database
				m.setPostalAddress(rs.getString("PostalAddress"));
				m.setCover(rs.getString("Cover"));
				m.setInsurer(rs.getString("Insurer"));
				m.setPolicyNo(rs.getString("PolicyNo"));
				m.setCommencmentDate(rs.getString("CommDate"));
				m.setExpiryDate(rs.getString("ExpiryDate"));
				m.setPremiumCharged(Float.parseFloat(rs.getString("PremCharged")));
				m.setPaid(Float.parseFloat(rs.getString("PremPaid")));
				motors.add(m);  // this adds the Motor object m to motors list
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return motors;
	}
	
//	public String getName(){
//		return "Motor";
//	}
}
