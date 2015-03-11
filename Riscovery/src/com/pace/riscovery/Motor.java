package com.pace.riscovery;

/**
 *  This Class is the abstract implementation of the motor table
 *  And only allows for saving of data. For loading from database see 
 *  MOtorHelper
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Motor {
	
	private  int id = -1;  // if an id is -1 it means entry does not exit so then we save rather than update
	private  String insured;
	private  String contact;
	private  String email;
	private  String postalAddress;
	private  String cover;
	private  String insurer;
	private  String policyNo;
	private  String commencementDate;
	private  String expiryDate;		//Remember to change datatype for all the other databases from DateTime to VARCHAR
	private  float premiumCharged;
	private  float paid;
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setInsured(String insured){
		this.insured = insured;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPostalAddress(String postalAddress){
		this.postalAddress = postalAddress;
	}

	public void setCover(String cover){
		this.cover = cover;
	}

	public void setInsurer(String insurer){
		this.insurer = insurer;
	}

	public void setPolicyNo(String policyNo){
		this.policyNo = policyNo;
	}

	public void setCommencmentDate(String commencementDate){
		this.commencementDate = commencementDate;
	}

	public void setExpiryDate(String expiryDate){
		this.expiryDate = expiryDate;
	}

	public void setPremiumCharged(float premiumCharged){
		this.premiumCharged = premiumCharged;
	}

	public void setPaid(float paid){
		this.paid = paid;
	}		

	public int getId(){
		return id;
	}

	public String getInsured(){
		return insured;
	}

	public String getContact(){
		return contact;
	}

	public String getEmail(){
		return email;
	}

	public String getPostalAddress(){
		return postalAddress;
	}

	public String getCover(){
		return cover;
	}

	public String getInsurer(){
		return insurer;
	}

	public String getPolicyNo(){
		return policyNo;
	}

	public String getCommencementDate(){
		return commencementDate;
	}

	public String getExpiryDate(){
		return expiryDate;
	}

 	public float getPremiumCharged(){
 		return premiumCharged;
 	}

 	public float getPaid(){
 		return paid;
 	}

 	public float getBalance(){
 		return (premiumCharged - paid);
 	}
 	
 	// This method both saves it it is the first time and updates if changes are to be made to existing entries
 	public void save() throws SQLException  {
 		try(Connection connection = DBHelper.getConnection()){
 			if(id == -1){
		 		String sql = "INSERT INTO Motor VALUES (default, ?,?,?,?,?,?,?,?,?,?,?)";								
					try(PreparedStatement pstmt = connection.prepareStatement(sql)){//, Statement.RETURN_GENERATED_KEYS)) {//generated id will be useful in this code
					
						pstmt.setString(1, insured);
						pstmt.setString(2, contact);
						pstmt.setString(3, email);
						pstmt.setString(4, postalAddress);
						pstmt.setString(5, cover);
						pstmt.setString(6, insurer);
						pstmt.setString(7, policyNo);
						pstmt.setString(8, commencementDate);
						pstmt.setString(9, expiryDate);
						pstmt.setFloat(10, premiumCharged);
						pstmt.setFloat(11, paid);
						pstmt.execute();
						System.out.println("inside save()");						
//						pstmt.close();
												
						// This code is generating keys
//						try(ResultSet rs = pstmt.getGeneratedKeys()) {
//							rs.next();
//							id = rs.getInt(1);							
//						}
				}
 			} 			
 			else {
 				//TODO: Update code goes here
 				String sql = "UPDATE motor SET  insured=? , contact=?, email=?, postalAddress=?, cover=?, insurer=?, policyNo=?," +
 						"commDate=?, expiryDate=?, premCharged=?, premPaid=? WHERE idMotor=?";
 				try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
					
					pstmt.setString(1, insured);
					pstmt.setString(2, contact);
					pstmt.setString(3, email);
					pstmt.setString(4, postalAddress);
					pstmt.setString(5, cover);
					pstmt.setString(6, insurer);
					pstmt.setString(7, policyNo);
					pstmt.setString(8, commencementDate);
					pstmt.setString(9, expiryDate);
					pstmt.setFloat(10, premiumCharged);
					pstmt.setFloat(11, paid);
					pstmt.setInt(12, id);
					pstmt.execute();
 				}
 			}
 		}
 	}

 	public void delete() throws SQLException {
 		//TODO: work on this next!!
 		try(Connection connection = DBHelper.getConnection()){
 			String sql = "DELETE FROM Motor WHERE idMotor=?"; 						
 			try(PreparedStatement pstmt = connection.prepareStatement(sql)) {
 				pstmt.setInt(1, id);
				pstmt.execute();				
 			}
// 			sql = "ALTER TABLE Motor AUTO_INCREMENT = 1";
//			try(PreparedStatement pstmt = connection.prepareStatement(sql)) {				
//				pstmt.execute();				
//			}
// 			String sql2 = "SELECT COUNT(*) FROM Motor";
//			try(PreparedStatement pstmt = connection.prepareStatement(sql2)) {	 				
//				pstmt.execute();
 		}
 	}
}