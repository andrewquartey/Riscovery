package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENT_TABLE")
public class Insured {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //I may change type to INCREMENT.
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "CONTACT", nullable = false)
	private String contact;  //it maybe possible to use int instead
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
		
	@ElementCollection
	@JoinTable(
			name = "INSURER_POLICY_TABLE",
			joinColumns = @JoinColumn(name = "NAME")
			)
	private Set<Policy> policies = new HashSet<Policy>();	
	
	public Long getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setContact(String contact){
		this.contact = contact;
	}
	
	public String getContact(){
		return contact;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	//This method may be redundant
	public void setPolicies(Set<Policy> policies){
		this.policies = policies;
	}
	
	public Set<Policy> getPolicies(){
		return policies;
	}
	
	public void addPolicy(Policy policy){
		if(policy.getInsured()!= null){
			//Raise An Exception if there's a client with that policy		
			System.out.println("Do something here");
		}
		policies.add(policy);
	}
}
