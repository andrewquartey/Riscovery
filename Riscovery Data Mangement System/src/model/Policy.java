package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Policy {
	
	private String policyNumber;
	private String policyType;
	private String cover;
	private Date commencementDate;
	private Date expiryDate;
	private float premiumCharged;
	private float premiumPaid;
	private float balance;
	private Insured insured;
	
	public void setPolicyNumber(String policyNumber){
		this.policyNumber = policyNumber;
	}
	
	@Column(name = "POLICY_NUMBER", nullable = false)
	public String getPolicyNumber(){
		return policyNumber;
	}
	
	public void setPolicyType(String policyType){
		this.policyType = policyType;
	}
	
	@Column(name = "POLICY_TYPE", nullable = false)
	public String getPolicyType(){
		return policyType;
	}
	
	public void setCover(String cover){
		this.cover = cover;
	}

	@Column(name = "COVER", nullable = false)
	public String getCover(){
		return cover;
	}
	
	public void setCommencementDate(Date commencementDate){
		this.commencementDate = commencementDate;
	}
	
	//make date a timestamp because of database
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMM_DATE", nullable = false)
	public Date getCommencementDate(){
		return commencementDate;
	}
	
	public void setExpiryDate(Date expiryDate){
		this.expiryDate = expiryDate;
	}
	
	//make date a timestamp because of database
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXP_DATE", nullable = false)
	public Date getExpiryDate(){
		return expiryDate;
	}
	
	public void setPremiumCharged(float premiumCharged){
		this.premiumCharged = premiumCharged;
	}
	
	@Column(name = "PREM_CHARGED", nullable = false)
	public float getPremiumCharged(){
		return premiumCharged;
	}
	
	public void setPremiumPaid(float premiumPaid){
		this.premiumPaid = premiumPaid;
	}
	
	@Column(name = "PREM_PAID", nullable = false)
	public float getPremiumPaid(){
		return premiumPaid;
	}
	
	@org.hibernate.annotations.Formula("PREM_CHARGED - PREM_PAID")
	@Column(name = "BALANCE", nullable = false)
	public float getBalance(){
		return this.balance;
	}
		
	//for searching purposes
	@org.hibernate.annotations.Parent
	public Insured getInsured(){
		return insured;
	}
}






