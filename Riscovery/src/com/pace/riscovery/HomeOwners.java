package com.pace.riscovery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class HomeOwners {
	private long id;
	private  String insured;
	private  String contact;
	private  String email;
	private  String postalAddress;
	private  String cover;
	private  String insurer;
	private  String policyNo;
	private  String commencementDate;
	private  String expiryDate;
	private  float premiumCharged;
	private  float paid;
	private  float balance;


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
		

	public long getId(){
		return id;
	}

	public String getInusred(){
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

 	public void save() throws SQLException  {
 		String sql = "INSERT INTO Motor VALUES (default, ?,?,?,?,?,?,?,?,?,?,?)";
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testDB?user=root");
			PreparedStatement pstmt = connection.prepareStatement(sql)){
					
			
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
			pstmt.executeUpdate();
			System.out.println("inside save()");
			
			pstmt.close();
		}
 	}

}
