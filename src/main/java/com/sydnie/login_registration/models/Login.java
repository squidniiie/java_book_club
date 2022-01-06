package com.sydnie.login_registration.models;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Login {
	
	@NotEmpty(message = "Please enter your email")
	@Email(message = "Please enter a valid email")
	private String email;
	
	@NotEmpty(message="Password required")
	@Size(min = 8, max= 60, message = "Password must be between 8 and 60 characters")
	private String password;
	
	
	
//Constructor:
	
	public Login() {}

// Getters and setters:

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
	
}
