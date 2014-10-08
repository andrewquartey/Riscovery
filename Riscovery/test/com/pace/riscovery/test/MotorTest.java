package com.pace.riscovery.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pace.riscovery.DBHelper;
import com.pace.riscovery.Motor;

/**
 * Test for Motor class
 * @author Andy
 *
 */
public class MotorTest {
	
	@Before
	public void init() throws SQLException{
		DBHelper.getInstance().init();
		//we empty the table before we carry out the test
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
	public void testSave() throws SQLException {
		Motor m = new Motor();
		m.setInsured("Andy");
		m.setContact("0265324264");
		Assert.assertEquals(-1, m.getId());
		m.save();	
		                                                                                                                                                                                                                                                                                 
//		Motor m = new Motor();
		m.setInsured("Bendo");
		m.setContact("0245778778");	
		m.save();
		
				
		try(Connection con = DBHelper.getConnection();
				Statement stmt = con.createStatement()){
			try(ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Motor")) {
				//checks that rs.next() is always true 
				Assert.assertTrue("Count should return at leas one row",rs.next());
				//since  the tests adds one row we check to see if there no. of rows is indeed one
				Assert.assertEquals(1, rs.getInt(1));
				// checks that when we run rs.next() again, it will be false since there is only one row
				Assert.assertFalse("Count should not return more than one row", rs.next());
			}			
			try(ResultSet rs = stmt.executeQuery("SELECT * FROM Motor")) {
				Assert.assertTrue("Select should return at least one row", rs.next());
				
				Assert.assertEquals("Andy", rs.getString("insured"));
				
				Assert.assertFalse("Select should not return more than one row", rs.next());
			}
			
		}
	}
}
