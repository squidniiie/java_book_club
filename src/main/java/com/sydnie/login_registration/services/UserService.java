package com.sydnie.login_registration.services;
import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.sydnie.login_registration.models.Login;
import com.sydnie.login_registration.models.User;
import com.sydnie.login_registration.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

//CREATE METHOD:
	public User register(User newUser, BindingResult result) {
       if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
           result.rejectValue("email", "Unique", "This email is already in use!");
       }
       if(!newUser.getPassword().equals(newUser.getConfirm())) {
           result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
       }
       if(result.hasErrors()) {
           return null;
       } else {
           String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
           newUser.setPassword(hashed);
           return userRepo.save(newUser);
       }
	}
//VALIDATE USER:
       public User login(Login newLogin, BindingResult result) {
           if(result.hasErrors()) {
               return null;
           }
           Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
           if(!potentialUser.isPresent()) {
               result.rejectValue("email", "Unique", "Login error");
               return null;
           }
           User user = potentialUser.get();
           if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
           	System.out.println("inside password error");
               result.rejectValue("password", "Matches", "Login error");
           }
           if(result.hasErrors()) {
               return null;
           } else {
               return user;
           }
       }
       
//     READ ONE
	     public User findOne(Long id) {
	     	Optional<User> optionalUser = userRepo.findById(id);
	     	if (optionalUser.isPresent()) {
	     		return optionalUser.get();
	     	} else {
	     		return null;
	     	}
	     }
	     
//	 	Retrieve all:
	 	public List<User> allUsers() {
	 		return userRepo.findAll();
	 	}
   }

