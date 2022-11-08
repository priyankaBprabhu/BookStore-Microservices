package com.bl.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userID;
	@NotEmpty(message = "Name cant be empty")
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "User Name is Invalid")
	private String name;

	private long phoneNumber;
	@Email
	private String email;
	@NotEmpty(message = "Address can not be empty")
	private String address;
	@NotEmpty(message = "City can not be empty")
	private String city;

	private int pincode;
	private String landmark;
	@NotEmpty(message = "Password can not be empty")
	private String password;

//	made connection between book to user 
//	@OneToMany
//	private List<Book> cartBook = new LinkedList<>();
}
