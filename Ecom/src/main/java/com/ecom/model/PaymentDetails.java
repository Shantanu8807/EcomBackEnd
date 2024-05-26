package com.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {
	
	private String paymentMethod;
	
	private String status;
	
	private String paymentId;
	
	private String razorPaymentLinkId;
	
	private String razorPaymentLinkReferenceId;
	
	private String razorPaymentLinkStatus;
	
	private String razorPaymentId;

}
