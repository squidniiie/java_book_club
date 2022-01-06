package com.sydnie.login_registration.models;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Username is required!")
	@Size(min = 2, max = 30, message = "Username must be between 2 to 30 characters.")
	private String userName;
	
	@NotEmpty(message="Email address is required")
	@Email(message= "Please enter a valid email address")
	private String email;
	
	@NotEmpty(message= "Password required")
	@Size(min = 8, max = 60, message = "Password must be between 8 and 60 characters.")
	private String password;
	
	@Transient
	@NotEmpty(message= "Confirm your password")
	@Size(min = 8, max = 60, message = "Confirm password must be between 8 and 60 characters.")
	private String confirm;
	

//	1 to Many:
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Book> book;

// 1 to many - borrowed book:
	@OneToMany(mappedBy = "borrowUser", fetch = FetchType.LAZY)
	private List<Book> borrowBooks;
	
// Constructor:
	public User() {}
	
// GETTERS and SETTERS: 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	
// Relational getters and setters:
	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public List<Book> getBorrowBooks() {
		return borrowBooks;
	}

	public void setBorrowBooks(List<Book> borrowBooks) {
		this.borrowBooks = borrowBooks;
	}
	

	
	
	
	

}
