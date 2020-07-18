package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur extends Utilisateur {
	
	
    private String address;
	private String	phoneNumber; 

}
