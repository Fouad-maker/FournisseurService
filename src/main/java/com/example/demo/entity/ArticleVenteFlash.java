package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArticleVenteFlash extends Article {
	
	private  double prixArtVF;
	private int qteStockArtVF;
	
	
	
	
}


