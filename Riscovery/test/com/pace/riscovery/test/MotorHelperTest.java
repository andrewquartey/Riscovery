package com.pace.riscovery.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pace.riscovery.DBHelper;
import com.pace.riscovery.Motor;
import com.pace.riscovery.MotorHelper;

public class MotorHelperTest {
	
	@Before
	public void init() throws SQLException {
		DBHelper.getInstance().init();
		try(Connection con = DBHelper.getConnection();
				Statement stmt = con.createStatement()){
			stmt.execute("TRUNCATE Motor"); // This resets the TABLE Motor
		}
	}
	
	@After
	public void close(){
		DBHelper.getInstance().close();
	}
	
	@Test
	public void testMotors() throws SQLException{
		List<Motor> motors = MotorHelper.getInstance().getMotors();
		Assert.assertNotNull(motors);
		Assert.assertTrue(motors.isEmpty());
		
		try(Connection con = DBHelper.getConnection(); 
				Statement stmt = con.createStatement()) {
			for(int i=0; i<3; i++)
				stmt.execute("INSERT INTO Motor (insured, contact) VALUES ('Lobo"+i+"','0265324264')");
		}
		
		
		motors = MotorHelper.getInstance().getMotors();
		Assert.assertNotNull(motors);
		Assert.assertEquals(3, motors.size());
		
		for(int i=0; i<3; i++){
			Motor motor = motors.get(i);
			Assert.assertNotNull(motor);
			Assert.assertEquals("Lobo"+i, motor.getInsured());
			Assert.assertEquals(i+1, motor.getId());
			Assert.assertEquals("0265324264", motor.getContact());
		}			
	}
}
