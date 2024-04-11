package com.lcwd.repository;

import org.springframework.data.repository.CrudRepository;

import com.lcwd.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	public Book findById(int id);
	
	
	
}
