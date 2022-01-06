package com.sydnie.login_registration.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sydnie.login_registration.models.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{
	List<Book> findAll();


}
