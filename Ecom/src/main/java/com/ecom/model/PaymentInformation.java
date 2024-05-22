package com.ecom.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentInformation {
	
	
	@Column(name="cardholder_name")
	private String cardholdername;
	
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	@Column(name="cvv")
	private String cvv;
	
	
	
	
	
	

}
