package com.wcs.bibliotheque.bibliotheque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcs.bibliotheque.bibliotheque.entities.Book;
import com.wcs.bibliotheque.bibliotheque.repositories.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	public BookRepository bookDao;
	
	public BookController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/")
	public @ResponseBody List<Book> getAll() {
		return bookDao.findAll();
	}
	
	@GetMapping("/{id}")
	public @ResponseBody Book getById(@PathVariable Long id) {
		return this.bookDao.findById(id).get();
	}
	
	@PostMapping("/")
	public @ResponseBody Book add(
			@RequestParam String author, 
			@RequestParam String title, 
			@RequestParam String description) {
		
		Book book = new Book();
		book.setAuthor(author);
		book.setDescription(description);
		book.setTitle(title);
		
		this.bookDao.save(book);
		
		return book;
	}
	
	@GetMapping("/search")
	public @ResponseBody List<Book> find(
			@RequestParam Map<String, String> q
			) {
		String searchTerm = q.get("query");
		return this.bookDao.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody boolean delete(@PathVariable Long id) {
		this.bookDao.deleteById(id);
		return true;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody Book update(@PathVariable Long id, @RequestBody Book livre) {
		
		Book book = this.bookDao.findById(id).get();
		
		if (livre.getAuthor() != null) 
			book.setAuthor(livre.getAuthor());
		
		if (livre.getDescription() != null)
			book.setDescription(livre.getDescription());
		
		if (livre.getTitle() != null)
			book.setTitle(livre.getTitle());
		
		
		return this.bookDao.save(book);
	}
	
//	@GetMapping("/add")
//	public @ResponseBody Book add(
//			@RequestParam Book book) {
//		
//		this.bookDao.save(book);
//		
//		return book;
//	}

}
