package com.lcwd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.entity.Book;
import com.lcwd.repository.BookRepository;

@Service
public class BookService  {
	@Autowired
	private BookRepository bookRepository;

	//	private static List<Book> list = new ArrayList<>();
	//	static {
	//		list.add(new Book(12, "xc", "dffg"));
	//		list.add(new Book(13, "xcf", "ghdffg"));
	//		list.add(new Book(14, "xgc", "dfhffg"));
	//	}
	public List<Book> getAllBooks(){
		List<Book> list = (List<Book>) this.bookRepository.findAll();
		return list;
	}

	public Book getBookById(int id) {
		Book book = null;
		try {
			//	book  = list.stream().filter(e->e.getId()==id).findFirst().get();
			book = bookRepository.findById(id);
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return book;
	}


	public Book addBook(Book book) {
		Book savedbook = bookRepository.save(book);
		return savedbook;
	}

	public void deleteBook(int id) {

		//		list.stream().filter(book->{
		//			if(book.getId()!=id) {
		//				return true;
		//			}else {
		//				return false;
		//			}
		//		}).collect(Collectors.toList());

//		list = list.stream().filter(book->book.getId()!=id).collect(Collectors.toList());
		bookRepository.deleteById(id);
	}

	public void updateBook(Book book, int id) {
//
//		list = list.stream().map(b->{
//			if(b.getId()==id) {
//				b.setTitle(book.getTitle());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
//	}
		book.setId(id);
		bookRepository.save(book);
	}
}
