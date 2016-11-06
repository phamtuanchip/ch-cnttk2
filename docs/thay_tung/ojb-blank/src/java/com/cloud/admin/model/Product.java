package com.cloud.admin.model;

public class Product
{
/* Instance Properties */

private Double price;
private Integer stock;
private String name;

/* artificial property used as primary key */

private Integer id;

/* Getters and Setters */

public Product(String name, int stock, double price) {
	this.name = name;
	this.stock = stock;
	this.price = price;
}
 
}