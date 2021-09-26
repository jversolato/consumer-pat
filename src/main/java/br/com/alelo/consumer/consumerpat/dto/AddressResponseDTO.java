package br.com.alelo.consumer.consumerpat.dto;

import lombok.Data;

@Data
public class AddressResponseDTO {
	private Long id;
	private String addressStreet;
	private Integer addressNumber;
	private String city;
	private String country;
	private String postalCode;
}