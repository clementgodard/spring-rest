package com.wcs.bibliotheque.bibliotheque.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcs.bibliotheque.bibliotheque.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
	
}
