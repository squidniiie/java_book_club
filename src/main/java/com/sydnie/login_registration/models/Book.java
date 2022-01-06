package com.sydnie.login_registration.models;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		 
	@NotNull
	@Size(min = 2, max = 60, message ="Title must be at least 2 characters.")
	private String title; 
	
	@NotNull
	@Size(min = 2, max = 60, message ="Author name must be at least 2 characters.")
	private String author;
	
	@NotNull
	@Size(min = 2, max = 300, message ="Please give a description.")
	private String description;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	    
//		Many to 1:
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User user;
	    
// Borrowed books- many to one:
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="borrow_id")
	    private User borrowUser;
	    
	    
// Constructor:
	    public Book() {}
	    
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
	    
	    
// Getters and Setters:

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}


		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
//	relational getter and setter:
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public User getBorrowUser() {
			return borrowUser;
		}

		public void setBorrowUser(User borrowUser) {
			this.borrowUser = borrowUser;
		}

}
