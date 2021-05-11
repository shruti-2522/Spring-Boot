package com.Restapi.book.dao;

import org.springframework.data.repository.CrudRepository;

import com.Restapi.book.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	public Book findById(int id);

}
