package com.bl.bookservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bl.bookservice.dto.BookDTO;
import com.bl.bookservice.model.Book;
import com.bl.bookservice.service.IBookService;
@RestController
@RequestMapping("/bookdetails")
public class BookController {

	// Autowired IBookService to inject its dependency here
	@Autowired
	private IBookService bookService;

//	@Autowired
//	TokenManger tokenGen;
//	

	// Ability to call api to insert Book record
	@PostMapping("/insert")
	public ResponseEntity<BookDTO> insertBook( @Valid @RequestBody Book book) {
//		tokenGen.decodeToken(token);
		Book newBook = bookService.insertBook(book);
		BookDTO bookDto = bookService.convertEntityToDto(newBook);
		return new ResponseEntity<BookDTO>(bookDto, HttpStatus.CREATED);
	}

	// Ability to call api to retrieve all book records
	@GetMapping("/retrieveAllBooks")
	public ResponseEntity<BookDTO> getAllBookRecords() {
//		tokenGen.decodeToken(token);
		List<Book> newBook = bookService.getAllBookRecords();
		return new ResponseEntity(newBook, HttpStatus.OK);

	}

	// Ability to call api to update book record by name
	@PutMapping("/updateBook/{bookName}")
	public ResponseEntity<BookDTO> updateBookRecord(@PathVariable String bookName,
			@Valid @RequestBody BookDTO bookdto) {
//		tokenGen.decodeToken(token);
		Book newBook = bookService.updateBookRecord(bookName, bookdto);
		BookDTO bookDto = bookService.convertEntityToDto(newBook);
		return new ResponseEntity<BookDTO>(bookDto, HttpStatus.ACCEPTED);
	}

	// Ability to call api to retrieve record by book name
	@GetMapping("/retrieve/{bookName}")
	public ResponseEntity<BookDTO> getRecordByBookName( @PathVariable String bookName) {
//		tokenGen.decodeToken(token);
		Book newBook = bookService.getRecordByBookName(bookName);
		BookDTO bookDto = bookService.convertEntityToDto(newBook);
		return new ResponseEntity(bookDto, HttpStatus.OK);
	}

	// Ability to call api to delete book record by name
	@DeleteMapping("/deleteBook/{bookName}")
	public ResponseEntity<BookDTO> deleteBookRecord( @PathVariable String bookName) {
//		tokenGen.decodeToken(token);
		String newBook = bookService.deleteBookRecord(bookName);
		return new ResponseEntity(newBook, HttpStatus.ACCEPTED);
	}

	// Ability to call api to sort book records in ascending order
//	@GetMapping("/sortAsc")
//	public ResponseEntity<BookDTO> sortRecordAsc(@RequestHeader String token) {
//		
//		List<Book> newBook = bookService.sortRecordAsc(token);
//		return new ResponseEntity(newBook, HttpStatus.OK);
//	}

	// Ability to call api to sort book records in descending order
	@GetMapping("/sortDesc")
	public ResponseEntity<BookDTO> sortRecordDesc() {
//		tokenGen.decodeToken(token);
		List<Book> newBook = bookService.sortRecordDesc();
		return new ResponseEntity(newBook, HttpStatus.OK);
	}
	
	@GetMapping("/sortByBookNameLTH")
	public ResponseEntity<BookDTO> sortBooKNameLTH() {
//		tokenGen.decodeToken(token);
//		Book book = new Book();
//		String bookName = book.getBookName();
		List<Book> newBook = bookService.sortByBookNameLTH();
		return new ResponseEntity(newBook, HttpStatus.OK);
	}
	@GetMapping("/sortByBookNameHTL")
	public ResponseEntity<BookDTO> sortByBookNameHTL() {
//		tokenGen.decodeToken(token);
		List<Book> newBook = bookService.sortByBookNameHTL();
		return new ResponseEntity(newBook, HttpStatus.OK);
	}
	@GetMapping("/sortByNewArraival")
	public ResponseEntity<BookDTO> sortByNewArraival() {
//		tokenGen.decodeToken(token);
		List<Book> newBook = bookService.sortByNewestArrival();
		return new ResponseEntity(newBook, HttpStatus.OK);
	}
	

}
