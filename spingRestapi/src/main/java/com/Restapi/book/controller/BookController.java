package com.Restapi.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Restapi.book.entities.Book;
import com.Restapi.book.services.BookService;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	// @RequestMapping(value="/books",method=RequestMethod.GET)
	// @ResponseBody

	// get ALL Book Handler
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBook() {

		/*
		 * Book b=new Book(); b.setBid(1); b.setBname("Java Frameowrks");
		 * b.setAuthor("Durgesh Tiwari");
		 */

		List<Book> list = bookService.getAllBook();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	// get Single book Hnadler
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book b = bookService.getBookById(id);
		if (b == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(b));
	}

	// Create Book Hnadler
	@PostMapping("/books")

	public ResponseEntity<Book> addBook(@RequestBody Book b1) {
		Book b = null;
		try {
			b = this.bookService.addBook(b1);
			return ResponseEntity.of(Optional.of(b));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	// Delete book handler:
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> delBook(@PathVariable("id") int id) {
		try {
			this.bookService.delBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// Update Book Handler
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updatBook(@RequestBody Book book, @PathVariable("id") int id) {
		try {
			this.bookService.updateBook(book, id);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}