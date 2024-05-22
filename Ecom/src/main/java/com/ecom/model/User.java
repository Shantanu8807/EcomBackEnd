package com.ecom.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String email;
	
	private String role;
	
	private String mobile;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	private List<Addres> address= new ArrayList<>();
	
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_information",joinColumns=@JoinColumn(name="user_id"))
	private List<PaymentInformation> paymentInformation=new ArrayList<>();
	
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> rating= new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews= new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	
	
	
	
	

}
