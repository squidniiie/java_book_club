package com.sydnie.login_registration.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sydnie.login_registration.models.Book;
import com.sydnie.login_registration.repositories.BookRepo;

@Service
public class BookService {
	@Autowired BookRepo bookRepo;
	
//	Create methods:
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}

// Read all:
	public List<Book> allBooks() {
		return bookRepo.findAll();
	}
	
// Read one:
	public Book findBook(Long id) {
		Optional<Book> optionalBook = bookRepo.findById(id);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
				return null;
		}
	}
// Update:
	public Book update(Book book) {
		return bookRepo.save(book);
	}
	
// Delete:
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}

}
