package com.bl.bookservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.bookservice.dto.BookDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

//Map to a database table
@Entity
//Use to bundle features of getter and setter
@Data
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer bookID;

  private String bookName;
  private String authorName;
  private String bookDescription;
  private String bookImg;
  private Double price;
  private Integer quantity;
  public Book(BookDTO dto) {
		super();
		this.bookName = dto.getBookName();
		this.authorName = dto.getAuthorName();
		this.bookDescription = dto.getBookDescription();
		this.bookImg = dto.getBookImg();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
	}
	public Book(String bookName, BookDTO dto) {
		super();
		this.bookID = bookID;
		this.bookName = dto.getBookName();
		this.authorName = dto.getAuthorName();
		this.bookDescription = dto.getBookDescription();
		this.bookImg = dto.getBookImg();
		this.price = dto.getPrice();
		this.quantity = dto.getQuantity();
	}
	public Book() {
		super();
	}

	

}
